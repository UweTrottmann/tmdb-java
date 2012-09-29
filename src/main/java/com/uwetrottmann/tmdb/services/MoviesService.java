/*
 * Copyright 2012 Uwe Trottmann
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

package com.uwetrottmann.tmdb.services;

import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TmdbApiBuilder;
import com.uwetrottmann.tmdb.TmdbApiService;
import com.uwetrottmann.tmdb.entities.Movie;

public class MoviesService extends TmdbApiService {

    /**
     * Get the basic movie information for a specific movie id.
     * 
     * @return Builder instance.
     */
    public SummaryBuilder summary(Integer id) {
        return new SummaryBuilder(this, id);
    }

    public static final class SummaryBuilder extends TmdbApiBuilder<Movie> {
        private static final String URI = "/movie/" + FIELD_ID + FIELD_API_KEY;

        private SummaryBuilder(MoviesService service, Integer id) {
            super(service, new TypeToken<Movie>() {
            }, URI);

            field(FIELD_QUERY, id);
        }
    }
}
