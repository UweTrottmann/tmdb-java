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
import com.uwetrottmann.tmdb.services.MoviesService;
import com.uwetrottmann.tmdb.services.SearchService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Class to manage service creation with default settings.
 */
public class Tmdb {

    /**
     * Tmdb API URL.
     */
    private static final String API_URL = "http://api.themoviedb.org/3";

    /**
     * API key query parameter name.
     */
    private static final String PARAM_API_KEY = "api_key";

    /**
     * API key.
     */
    private String mApiKey;

    /**
     * Whether to return more detailed log output.
     */
    private boolean mIsDebug;

    /**
     * Currently valid instance of RestAdapter.
     */
    private RestAdapter mRestAdapter;

    /**
     * Create a new manager instance.
     */
    public Tmdb() {
    }

    /**
     * Set default API key.
     *
     * @param value API key value.
     * @return Current instance for builder pattern.
     */
    public Tmdb setApiKey(String value) {
        this.mApiKey = value;
        mRestAdapter = null;
        return this;
    }

    public Tmdb setIsDebug(boolean isDebug) {
        mIsDebug = isDebug;
        mRestAdapter = null;
        return this;
    }

    /**
     * If no instance exists yet, builds a new {@link RestAdapter} using the currently set API key
     * and debug flag.
     */
    private RestAdapter buildRestAdapter() {
        if (mRestAdapter == null) {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setServer(API_URL)
                    .setConverter(new GsonConverter(TmdbHelper.getGsonBuilder().create()));

            // if available, send mUsername and password in header
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade requestFacade) {
                    requestFacade.addQueryParam(PARAM_API_KEY, mApiKey);
                }
            });

            if (mIsDebug) {
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
            }

            mRestAdapter = builder.build();
        }

        return mRestAdapter;
    }

    public MoviesService moviesService() {
        return buildRestAdapter().create(MoviesService.class);
    }

    public SearchService searchService() {
        return buildRestAdapter().create(SearchService.class);
    }

    public ConfigurationService configurationService() {
        return buildRestAdapter().create(ConfigurationService.class);
    }

}
