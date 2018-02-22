package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * {@link Interceptor} to add the API key query parameter and if available session information. As it modifies the URL
 * and may retry requests, ensure this is added as an application interceptor (never a network interceptor), otherwise
 * caching will be broken and requests will fail.
 */
public class TmdbInterceptor implements Interceptor {

    private final Tmdb tmdb;


    public TmdbInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Response intercept(@Nonnull Chain chain) throws IOException {
        return handleIntercept(chain, tmdb);
    }

    /**
     * If the host matches {@link Tmdb#API_HOST} adds a query parameter with the API key.
     */
    public static Response handleIntercept(Chain chain, Tmdb tmdb) throws IOException {
        Request request = chain.request();

        if (!Tmdb.API_HOST.equals(request.url().host())) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request);
        }

        // add (or replace) the API key query parameter
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, tmdb.apiKey());

        // check for paths requiring auth
        AuthenticationType type = null;
        List<String> pathSegments = request.url().pathSegments();
        if ((pathSegments.size() >= 2 && pathSegments.get(1).equals("account"))
                || pathSegments.get(pathSegments.size() - 1).equals("account_states")) {
            type = AuthenticationType.ACCOUNT;
        } else if (pathSegments.get(pathSegments.size() - 1).equals("rating")
                || !request.method().equals("GET")) {
            type = determineAuthenticationType(urlBuilder, tmdb);
        }

        addSessionToQuery(urlBuilder, type, tmdb);
        // TODO ut: why is the authentication query param removed?
        urlBuilder.removeAllEncodedQueryParameters("authentication");
        // TODO ut: why can't the authenticator determine the required strategy?
        // adds fragment with the desired authentication strategy (like '#account') to the URL,
        // so the authenticator will know how to proceed.
        if (type != null) {
            urlBuilder.fragment(type.toString());
        }

        Request.Builder builder = request.newBuilder();
        builder.url(urlBuilder.build());
        Response response = chain.proceed(builder.build());

        if (!response.isSuccessful()) {
            // re-try if the server indicates we should
            String retryHeader = response.header("Retry-After");
            if (retryHeader != null) {
                try {
                    Integer retry = Integer.parseInt(retryHeader);
                    Thread.sleep((int) ((retry + 0.5) * 1000));
                    // is fine because, unlike a network interceptor, an application interceptor can re-try requests
                    return handleIntercept(chain, tmdb);
                } catch (NumberFormatException | InterruptedException ignored) {
                }
            }
        }

        return response;
    }

    private static void addSessionToQuery(HttpUrl.Builder urlBuilder, AuthenticationType type, Tmdb tmdb) {
        if (type == AuthenticationType.GUEST) {
            urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        } else if (type == AuthenticationType.ACCOUNT) {
            urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.getSessionId());
        }
    }

    static AuthenticationType determineAuthenticationType(HttpUrl.Builder builder, Tmdb tmdb) {
        HttpUrl url = builder.build();
        String authParam = url.queryParameter("authentication");
        AuthenticationType type = AuthenticationType.get(url.fragment());

        if (type == null) {
            if (authParam != null) {
                if (authParam.equals("account")) {
                    type = AuthenticationType.ACCOUNT;
                } else {
                    type = AuthenticationType.GUEST;
                }
            } else {
                if (tmdb.hasAccountSession()) {
                    type = AuthenticationType.ACCOUNT;
                } else if (tmdb.hasGuestSession()) {
                    type = AuthenticationType.GUEST;
                } else {
                    type = null;
                }
            }
        }

        return type;
    }

}
