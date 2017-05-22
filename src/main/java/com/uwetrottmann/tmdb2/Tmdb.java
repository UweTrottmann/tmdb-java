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

import com.uwetrottmann.tmdb2.entities.GuestSession;
import com.uwetrottmann.tmdb2.entities.RequestToken;
import com.uwetrottmann.tmdb2.entities.Session;
import com.uwetrottmann.tmdb2.exceptions.TmdbException;
import com.uwetrottmann.tmdb2.exceptions.TmdbServiceErrorException;
import com.uwetrottmann.tmdb2.services.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 * <p>
 * <p>Create an instance of this class and then call any of the service methods.
 * <p>
 * <p>The service methods take care of constructing the required {@link Retrofit} instance and creating the service. It
 * is recommended you use your existing OkHttp client instance by overriding {@link #okHttpClient()}.
 * <p>
 * <p>Only one {@link Retrofit} instance is created upon the first and re-used for any consequent service method call.
 */
public class Tmdb {

    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";

    /**
     * API key query parameter name.
     */
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_SESSION_ID = "session_id";
    public static final String PARAM_GUEST_SESSION_ID = "guest_session_id";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private String apiKey;

    private String sessionId;
    private String guestSessionId;

    private Boolean isLoggedIn = false;
    private Boolean hasGuestSession = false;
    private Boolean hasAccountSession = false;


    /**
     * Create a new manager instance.
     *
     * @param apiKey Your TMDB API key.
     */
    public Tmdb(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Create a new authenticated manager instance for post/delete operations.
     *
     * @param apiKey Your TMDB API key.
     */
    public Tmdb(String apiKey, String username, String password) throws IOException, TmdbException {
        this.apiKey = apiKey;
        authenticate(username, password);
    }


    public void authenticateAnonymously() throws IOException, TmdbServiceErrorException {
        AuthenticationService authService = getRetrofit().create(AuthenticationService.class);
        GuestSession session = authService.createGuestSession().execute().body();

        guestSessionId = session.guest_session_id;
        isLoggedIn = true;
        hasGuestSession = true;
    }

    public void authenticate(String username, String password) throws IOException, TmdbException {
        AuthenticationService authService = getRetrofit().create(AuthenticationService.class);

        RequestToken token = authService.requestToken().execute().body();

        token = authService.validateToken(username, password, token.request_token).execute().body();

        Session session = authService.createSession(token.request_token).execute().body();

        sessionId = session.session_id;
        isLoggedIn = true;
        hasAccountSession = true;

    }

    public String getSessionId() {
        return sessionId;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Boolean hasGuestSession() {
        return hasGuestSession;
    }

    public Boolean hasAccountSession() {
        return hasAccountSession;
    }

    public void apiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiKey() {
        return apiKey;
    }

    /**
     * Creates a {@link Retrofit.Builder} that sets the base URL, adds a Gson converter and sets {@link #okHttpClient()}
     * as its client.
     *
     * @see #okHttpClient()
     */
    protected Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.getGsonBuilder().create()))
                .client(okHttpClient());
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see #setOkHttpClientDefaults(OkHttpClient.Builder)
     */
    protected synchronized OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            setOkHttpClientDefaults(builder);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TmdbInterceptor(this));
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, auth changed), builds a new one.
     * <p>When building, sets the base url and a custom client with an {@link Interceptor} which supplies authentication
     * data.
     */
    protected Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build();
        }
        return retrofit;
    }

    public ConfigurationService configurationService() {
        return getRetrofit().create(ConfigurationService.class);
    }

    public FindService findService() {
        return getRetrofit().create(FindService.class);
    }

    public MoviesService moviesService() {
        return getRetrofit().create(MoviesService.class);
    }

    public PeopleService personService() {
        return getRetrofit().create(PeopleService.class);
    }

    public SearchService searchService() {
        return getRetrofit().create(SearchService.class);
    }

    public TvService tvService() {
        return getRetrofit().create(TvService.class);
    }

    public TvSeasonsService tvSeasonsService() {
        return getRetrofit().create(TvSeasonsService.class);
    }

    public TvEpisodesService tvEpisodesService() {
        return getRetrofit().create(TvEpisodesService.class);
    }

    public DiscoverService discoverService() {
        return getRetrofit().create(DiscoverService.class);
    }

    public CollectionService collectionService() {
        return getRetrofit().create(CollectionService.class);
    }

    public GenreService genreService() {
        return getRetrofit().create(GenreService.class);
    }

    public ReviewsService reviewsService() {
        return getRetrofit().create(ReviewsService.class);
    }

    public DiscoverMovieBuilder discoverMovie() {
        return new DiscoverMovieBuilder(discoverService());
    }

    public DiscoverTvBuilder discoverTv() {
        return new DiscoverTvBuilder(discoverService());
    }
}
