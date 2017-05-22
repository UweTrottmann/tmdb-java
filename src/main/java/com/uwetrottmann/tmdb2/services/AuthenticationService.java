/*
 * Copyright 2017 Nikolas Mavropoylos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.GuestSession;
import com.uwetrottmann.tmdb2.entities.RequestToken;
import com.uwetrottmann.tmdb2.entities.Session;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthenticationService {

    /**
     * Requests authentication Token.
     */
    @GET("authentication/token/new")
    Call<RequestToken> requestToken();

    /**
     * Attempts to Login with a Request Token and Username/Password.
     *
     * @param username      Username of TMDb Account.
     * @param password      Password of TMDb Account.
     * @param request_token The Token you requested.
     */
    @GET("authentication/token/validate_with_login")
    Call<RequestToken> validateToken(
            @Query("username") String username,
            @Query("password") String password,
            @Query("request_token") String request_token
    );

    /**
     * Creates Season with the Request Token you validated with your username/password.
     *
     * @param request_token The Token you requested.
     */
    @GET("authentication/session/new")
    Call<Session> createSession(
            @Query("request_token") String request_token
    );

    /**
     * Creates Guest Season
     */
    @GET("authentication/guest_session/new")
    Call<GuestSession> createGuestSession();
}
