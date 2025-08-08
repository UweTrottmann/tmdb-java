// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.GuestSession;
import com.uwetrottmann.tmdb2.entities.RequestToken;
import com.uwetrottmann.tmdb2.entities.Session;
import com.uwetrottmann.tmdb2.exceptions.TmdbAuthenticationFailedException;
import com.uwetrottmann.tmdb2.services.AuthenticationService;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TmdbAuthenticator implements Authenticator {

    private final Tmdb tmdb;

    public TmdbAuthenticator(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        return handleRequest(response, tmdb);
    }

    @Nullable
    public static Request handleRequest(Response response, Tmdb tmdb) throws IOException {
        if (response.request().url().pathSegments().get(0).equals(Tmdb.PATH_AUTHENTICATION)) {
            return null;
        }

        if (responseCount(response) >= 2) {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        HttpUrl.Builder urlBuilder = response.request().url().newBuilder();

        // prefer account session if both are available
        if (tmdb.useAccountSession()) {
            if (tmdb.getUsername() == null || tmdb.getPassword() == null) {
                throw new TmdbAuthenticationFailedException(26, "You must provide a username and password.");
            }
            String session = acquireAccountSession(tmdb);
            if (session == null) {
                return null; // failed to retrieve session, give up
            }
            urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, session);
        } else if (tmdb.useGuestSession()) {
            String session = acquireGuestSession(tmdb);
            if (session == null) {
                return null; // failed to retrieve session, give up
            }
            urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        } else {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        return response.request().newBuilder().url(urlBuilder.build()).build();
    }

    @Nullable
    public static String acquireAccountSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);

        RequestToken token = authService.requestToken().execute().body();
        if (token == null) {
            return null;
        }

        token = authService.validateToken(tmdb.getUsername(), tmdb.getPassword(), token.request_token).execute().body();
        if (token == null) {
            return null;
        }

        Session session = authService.createSession(token.request_token).execute().body();
        if (session == null) {
            return null;
        }

        tmdb.setSessionId(session.session_id);
        return session.session_id;
    }

    @Nullable
    public static String acquireGuestSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);
        GuestSession session = authService.createGuestSession().execute().body();
        if (session == null) {
            return null;
        }

        tmdb.setGuestSessionId(session.guest_session_id);
        return session.guest_session_id;
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
