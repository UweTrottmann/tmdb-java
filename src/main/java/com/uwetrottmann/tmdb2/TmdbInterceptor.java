package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import com.uwetrottmann.tmdb2.exceptions.TmdbAuthenticationFailedException;
import com.uwetrottmann.tmdb2.exceptions.TmdbDuplicateEntryException;
import com.uwetrottmann.tmdb2.exceptions.TmdbInvalidAcceptHeaderException;
import com.uwetrottmann.tmdb2.exceptions.TmdbInvalidParametersException;
import com.uwetrottmann.tmdb2.exceptions.TmdbNotFoundException;
import com.uwetrottmann.tmdb2.exceptions.TmdbServiceErrorException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * {@link Interceptor} to add the API key query parameter. As it modifies the URL ensure this is added as regular
 * interceptor, otherwise caching will be broken.
 */
public class TmdbInterceptor implements Interceptor {

    private final Tmdb tmdb;


    public TmdbInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
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
        AuthenticationType type = null;

        // add (or replace) the API key query parameter
        HttpUrl.Builder urlBuilder = request.url().newBuilder();

        urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, tmdb.apiKey());

        if (request.url().pathSegments().get(1).equals("account") || request.url().pathSegments().get(request.url().pathSegments().size() - 1).equals("account_states")) {
            type = AuthenticationType.ACCOUNT;

        } else if (request.url().pathSegments().get(request.url().pathSegments().size() - 1).equals("rating") || !request.method().toLowerCase().equals("get")) {
            type = determineAuthenticationType(urlBuilder, tmdb);
        }

        addSessionToQuery(urlBuilder, type, tmdb);

        urlBuilder.removeAllEncodedQueryParameters("authentication");
        // adds fragment identifier on the link, with the desired authentication strategy, so authenticator will know how to proceed.
        if (type != null) {
            urlBuilder.fragment(type.toString());
        }

        Request.Builder builder = request.newBuilder();
        builder.url(urlBuilder.build());

        Response response = chain.proceed(builder.build());
        if (!response.isSuccessful()) {
            String retryAfter = response.header("Retry-After");
            if (retryAfter != null) {
                try {
                    Integer retry = Integer.parseInt(retryAfter);
                    Thread.sleep((int) ((retry + 0.5) * 1000));
                    response = chain.proceed(builder.build());
                } catch (Exception exc) {

                }
            }
        }
        handleErrors(response, tmdb);

        return response;
    }

    /**
     * @see <a href="https://www.themoviedb.org/documentation/api/status-codes">Status Codes</a>
     */
    private static void handleErrors(Response response, Tmdb tmdb) throws IOException {
        if (response.code() >= 200 && response.code() <= 299) {
            return;
        }

        Status obj = getErrorStatusObject(response.body(), tmdb);

        if (obj.status_code == 3 || obj.status_code == 14 || obj.status_code == 33) {
            return;
        }

        switch (obj.status_code) {
            case 2:
            case 4:
            case 9:
            case 11:
            case 15:
            case 16:
            case 24:
                throw new TmdbServiceErrorException(obj.status_code, obj.status_message);
            case 3:
            case 7:
            case 10:
            case 14:
            case 17:
            case 18:
            case 26:
            case 30:
            case 31:
            case 32:
            case 33:
                throw new TmdbAuthenticationFailedException(obj.status_code, obj.status_message);
            case 5:
            case 20:
            case 22:
            case 23:
            case 27:
            case 28:
                throw new TmdbInvalidParametersException(obj.status_code, obj.status_message);
            case 6:
            case 34:
                throw new TmdbNotFoundException(obj.status_code, obj.status_message);
            case 8:
                throw new TmdbDuplicateEntryException(obj.status_code, obj.status_message);
            case 19:
                throw new TmdbInvalidAcceptHeaderException(obj.status_code, obj.status_message);
        }
    }

    private static Status getErrorStatusObject(ResponseBody body, Tmdb tmdb) throws IOException {
        return (Status) tmdb.getRetrofit().responseBodyConverter(
                Status.class, Status.class.getAnnotations())
                .convert(body);
    }

    private static void addSessionToQuery(HttpUrl.Builder urlBuilder, AuthenticationType type, Tmdb tmdb) {
        if (type == AuthenticationType.GUEST) {
            urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        } else if (type == AuthenticationType.ACCOUNT) {
            urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.getSessionId());
        }
    }

    static AuthenticationType determineAuthenticationType(HttpUrl.Builder builder, Tmdb tmdb) {
        AuthenticationType type;
        HttpUrl url = builder.build();

        String authParam = url.queryParameter("authentication");


        type = AuthenticationType.get(url.fragment());

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
