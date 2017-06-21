package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.GuestSession;
import com.uwetrottmann.tmdb2.entities.RequestToken;
import com.uwetrottmann.tmdb2.entities.Session;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import com.uwetrottmann.tmdb2.exceptions.TmdbAuthenticationFailedException;
import com.uwetrottmann.tmdb2.services.AuthenticationService;
import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.io.IOException;

public class TmdbAuthenticator implements Authenticator {

    private final Tmdb tmdb;

    public TmdbAuthenticator(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        return handleRequest(response, tmdb);
    }


    public static Request handleRequest(Response response, Tmdb tmdb) throws IOException {
        if (response.request().url().pathSegments().get(0).equals(Tmdb.PATH_AUTHENTICATION)) {
            return null;
        }

        if (responseCount(response) >= 2) {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        HttpUrl.Builder urlBuilder = response.request().url().newBuilder();

        AuthenticationType type = TmdbInterceptor.determineAuthenticationType(urlBuilder, tmdb);

        if (tmdb.hasAccountSession() && type == AuthenticationType.ACCOUNT) {
            if (tmdb.username == null || tmdb.password == null) {
                throw new TmdbAuthenticationFailedException(26, "You must provide a username and password.");
            }
            acquireAccountSession(tmdb);
            urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.sessionId);
        } else if (tmdb.hasGuestSession() && type == AuthenticationType.GUEST) {
            acquireGuestSession(tmdb);
            urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.guestSessionId);
        } else {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        return response.request().newBuilder().url(urlBuilder.build()).build();
    }


    public static void acquireAccountSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);

        RequestToken token = authService.requestToken().execute().body();

        token = authService.validateToken(tmdb.username, tmdb.password, token.request_token).execute().body();

        Session session = authService.createSession(token.request_token).execute().body();

        tmdb.sessionId = session.session_id;
        tmdb.isLoggedIn = true;

    }

    public static void acquireGuestSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);
        GuestSession session = authService.createGuestSession().execute().body();

        tmdb.guestSessionId = session.guest_session_id;
        tmdb.isLoggedIn = true;

    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
