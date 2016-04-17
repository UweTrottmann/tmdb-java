/*
 * Copyright 2013 Uwe Trottmann
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

import com.uwetrottmann.tmdb2.services.CollectionService;
import com.uwetrottmann.tmdb2.services.ConfigurationService;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import com.uwetrottmann.tmdb2.services.FindService;
import com.uwetrottmann.tmdb2.services.GenreService;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.PeopleService;
import com.uwetrottmann.tmdb2.services.SearchService;
import com.uwetrottmann.tmdb2.services.TvEpisodesService;
import com.uwetrottmann.tmdb2.services.TvSeasonsService;
import com.uwetrottmann.tmdb2.services.TvService;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 *
 * <p>Create an instance of this class, {@link #setApiKey(String)} and then call any of the service methods.
 *
 * <p>The service methods take care of constructing the required {@link Retrofit} instance and creating the service. You
 * can customize by overriding {@link #retrofitBuilder()} and {@link #okHttpClientBuilder()}
 *
 * <p>Only one {@link Retrofit} instance is created upon the first and re-used for any consequent service method call.
 */
@SuppressWarnings("WeakerAccess")
public class Tmdb {

    /**
     * Tmdb API URL.
     */
    public static final String API_URL = "https://api.themoviedb.org/3/";

    /**
     * API key query parameter name.
     */
    public static final String PARAM_API_KEY = "api_key";

    private Retrofit retrofit;
    private HttpLoggingInterceptor logging;

    private boolean isDebug;
    private String apiKey;

    /**
     * Create a new manager instance.
     */
    public Tmdb() {
    }

    /**
     * Set the TMDB API key. <p> The next service method call will trigger a rebuild of the {@link Retrofit} instance.
     * If you have cached any service instances, get a new one from its service method.
     *
     * @param value Your TMDB API key.
     */
    public Tmdb setApiKey(String value) {
        this.apiKey = value;
        retrofit = null;
        return this;
    }

    /**
     * Set the default logging interceptors log level.
     *
     * @param isDebug If true, the log level is set to {@link HttpLoggingInterceptor.Level#BODY}. Otherwise {@link
     * HttpLoggingInterceptor.Level#NONE}.
     * @see #okHttpClientBuilder()
     */
    public Tmdb setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        if (logging != null) {
            logging.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        }
        return this;
    }

    /**
     * Creates a {@link Retrofit.Builder} that sets the base URL, adds a Gson converter and sets {@link
     * #okHttpClientBuilder()} as its client. <p> Override this to for example set your own call executor.
     *
     * @see #okHttpClientBuilder()
     */
    protected Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.getGsonBuilder().create()))
                .client(okHttpClientBuilder().build());
    }

    /**
     * Creates a {@link OkHttpClient.Builder} for usage with {@link #retrofitBuilder()}. Adds interceptors to add auth
     * headers and to log requests. <p> Override this to for example add your own interceptors.
     *
     * @see #retrofitBuilder()
     */
    protected OkHttpClient.Builder okHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl.Builder urlBuilder = request.url().newBuilder();
                urlBuilder.addEncodedQueryParameter(PARAM_API_KEY, apiKey);

                Request.Builder builder = request.newBuilder();
                builder.url(urlBuilder.build());

                return chain.proceed(builder.build());
            }
        });
        if (isDebug) {
            logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder;
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
}
