/*
 * Modifications Copyright 2017 Nikolas Mavropoylos
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

package com.uwetrottmann.tmdb2;

import com.google.common.util.concurrent.RateLimiter;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.exceptions.*;
import okhttp3.*;

import java.io.IOException;

/**
 * {@link Interceptor} to add the API key query parameter. As it modifies the URL ensure this is added as regular
 * interceptor, otherwise caching will be broken.
 */
public class TmdbInterceptor implements Interceptor {
    private static final RateLimiter rateLimiter = RateLimiter.create(3.5);
    private Tmdb tmdb;

    public TmdbInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return handleIntercept(chain, tmdb.apiKey());
    }

    /**
     * If the host matches {@link Tmdb#API_HOST} adds a query parameter with the API key.
     *
     * @throws TmdbUnathorizedException          If user try to access a resource which requires authenticate, without a session.
     * @throws TmdbAuthenticaitonFailedException
     */
    public Response handleIntercept(Chain chain, String apiKey) throws IOException {
        Request request = chain.request();
        rateLimiter.acquire();
        if (!Tmdb.API_HOST.equals(request.url().host())) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request);
        }

        // add (or replace) the API key query parameter
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, apiKey);

        if (request.url().pathSegments().get(0).equals("account")) {
            if (tmdb.isLoggedIn() && tmdb.hasAccountSession()) {
                urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.getSessionId());
            } else {
                throw new TmdbUnathorizedException("You do not have permissions to access the service.");
            }
        } else if (request.url().pathSegments().get(0).equals("guest_session")) {
            if (tmdb.isLoggedIn() && tmdb.hasGuestSession()) {
                urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
            } else {
                throw new TmdbUnathorizedException("You do not have permissions to access the service.");
            }
        } else if (request.url().pathSegments().get(request.url().pathSegments().size()-1).equals("account_states")) {
            if (tmdb.isLoggedIn() && tmdb.hasGuestSession()) {
                urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
            } else {
                throw new TmdbUnathorizedException("You do not have permissions to access the service.");
            }
        } else if (!request.method().toLowerCase().equals("get")) {
            if (tmdb.isLoggedIn() && tmdb.hasAccountSession()) {
                urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.getSessionId());
            } else if (
                    tmdb.isLoggedIn()
                            && tmdb.hasGuestSession()
                            && request.url().pathSegments().get(request.url().pathSegments().size() - 1).equals("rating")
                    ) {
                urlBuilder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
            } else {
                throw new TmdbUnathorizedException("You do not have permissions to access the service.");
            }
        }

        Request.Builder builder = request.newBuilder();
        builder.url(urlBuilder.build());
        builder.header("Content-Type", "application/json;charset=utf-8");

        Response response = chain.proceed(builder.build());
        handleErrors(response);

        return response;
    }

    private void handleErrors(Response response) throws IOException {
        if (response.code() < 200 || response.code() > 299) {
            Status obj = getErrorStatusObject(response.body());
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
                    throw new TmdbAuthenticaitonFailedException(obj.status_code, obj.status_message);
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
    }

    private Status getErrorStatusObject(ResponseBody body) throws IOException {
        Status statusObject = (Status) tmdb.getRetrofit().responseBodyConverter(
                Status.class, Status.class.getAnnotations())
                .convert(body);
        return statusObject;
    }

}
