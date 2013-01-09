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

package com.uwetrottmann.tmdb.services;

import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TmdbApiBuilder;
import com.uwetrottmann.tmdb.TmdbApiService;
import com.uwetrottmann.tmdb.entities.ResultsPage;

public class SearchService extends TmdbApiService {

    /**
     * Search for movies by title.
     * 
     * @param query escaped string
     * @return Builder instance.
     */
    public MovieSearchBuilder movieSearch(String query) {
        return new MovieSearchBuilder(this, query);
    }

    public static final class MovieSearchBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/search/movie";

        private MovieSearchBuilder(SearchService service, String query) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);

            parameter(PARAMETER_QUERY, query);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public MovieSearchBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Toggle the inclusion of adult titles.
         * 
         * @param page Index of the page.
         */
        public MovieSearchBuilder inlcudeAdult(boolean includeAdult) {
            parameter(PARAMETER_INCLUDE_ADULT, includeAdult);
            return this;
        }

        /**
         * Filter results to only include this value.
         * 
         * @param page Index of the page.
         */
        public MovieSearchBuilder year(int year) {
            if (year > 0) {
                parameter(PARAMETER_YEAR, year);
            }
            return this;
        }
    }
}
