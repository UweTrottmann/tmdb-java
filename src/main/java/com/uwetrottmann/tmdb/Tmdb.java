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

package com.uwetrottmann.tmdb;

import com.uwetrottmann.tmdb.services.ConfigurationService;
import com.uwetrottmann.tmdb.services.DiscoverService;
import com.uwetrottmann.tmdb.services.FindService;
import com.uwetrottmann.tmdb.services.MoviesService;
import com.uwetrottmann.tmdb.services.PersonService;
import com.uwetrottmann.tmdb.services.SearchService;
import com.uwetrottmann.tmdb.services.TvEpisodesService;
import com.uwetrottmann.tmdb.services.TvSeasonsService;
import com.uwetrottmann.tmdb.services.TvService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 * <p>
 * Create an instance of this class, {@link #setApiKey(String)} and then call any of the service methods.
 * <p>
 * The service methods take care of constructing the required {@link retrofit.RestAdapter} and creating the service. You
 * can customize the {@link retrofit.RestAdapter} by overriding {@link #newRestAdapterBuilder()} and setting e.g.
 * your own HTTP client instance or thread executor.
 * <p>
 * Only one {@link retrofit.RestAdapter} instance is created upon the first and re-used for any consequent service
 * method call.
 */
public class Tmdb {

    /**
     * Tmdb API URL.
     */
    public static final String API_URL = "https://api.themoviedb.org/3";

    /**
     * API key query parameter name.
     */
    public static final String PARAM_API_KEY = "api_key";

    private String apiKey;
    private boolean isDebug;
    private RestAdapter restAdapter;

    /**
     * Create a new manager instance.
     */
    public Tmdb() {
    }

    /**
     * Set the TMDB API key.
     * <p>
     * The next service method call will trigger a rebuild of the {@link retrofit.RestAdapter}. If you have cached any
     * service instances, get a new one from its service method.
     *
     * @param value Your TMDB API key.
     */
    public Tmdb setApiKey(String value) {
        this.apiKey = value;
        restAdapter = null;
        return this;
    }

    /**
     * Set the {@link retrofit.RestAdapter} log level.
     *
     * @param isDebug If true, the log level is set to {@link retrofit.RestAdapter.LogLevel#FULL}.
     *                Otherwise {@link retrofit.RestAdapter.LogLevel#NONE}.
     */
    public Tmdb setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        if (restAdapter != null) {
            restAdapter.setLogLevel(isDebug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
        }
        return this;
    }

    /**
     * Create a new {@link retrofit.RestAdapter.Builder}. Override this to e.g. set your own client or executor.
     *
     * @return A {@link retrofit.RestAdapter.Builder} with no modifications.
     */
    protected RestAdapter.Builder newRestAdapterBuilder() {
        return new RestAdapter.Builder();
    }

    /**
     * Return the current {@link retrofit.RestAdapter} instance. If none exists (first call, API key changed),
     * builds a new one.
     * <p>
     * When building, sets the endpoint, a custom converter ({@link TmdbHelper#getGsonBuilder()})
     * and a {@link retrofit.RequestInterceptor} which adds the API key as query param.
     */
    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            RestAdapter.Builder builder = newRestAdapterBuilder();

            builder.setEndpoint(API_URL);
            builder.setConverter(new GsonConverter(TmdbHelper.getGsonBuilder().create()));
            builder.setRequestInterceptor(new RequestInterceptor() {
                public void intercept(RequestFacade requestFacade) {
                    requestFacade.addQueryParam(PARAM_API_KEY, apiKey);
                }
            });

            if (isDebug) {
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
            }

            restAdapter = builder.build();
        }

        return restAdapter;
    }

    public ConfigurationService configurationService() {
        return getRestAdapter().create(ConfigurationService.class);
    }

    public FindService findService() {
        return getRestAdapter().create(FindService.class);
    }

    public MoviesService moviesService() {
        return getRestAdapter().create(MoviesService.class);
    }

    public PersonService personService() {
        return getRestAdapter().create(PersonService.class);
    }

    public SearchService searchService() {
        return getRestAdapter().create(SearchService.class);
    }

    public TvService tvService() {
        return getRestAdapter().create(TvService.class);
    }

    public TvSeasonsService tvSeasonsService() {
        return getRestAdapter().create(TvSeasonsService.class);
    }
    
    public TvEpisodesService tvEpisodesService() {
        return getRestAdapter().create(TvEpisodesService.class);
    }
    
    public DiscoverService discoverService() {
        return getRestAdapter().create(DiscoverService.class);
    }

}
