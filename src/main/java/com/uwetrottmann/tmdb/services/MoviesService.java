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
import com.uwetrottmann.tmdb.entities.Casts;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.ResultsPage;
import com.uwetrottmann.tmdb.entities.Trailers;

public class MoviesService extends TmdbApiService {

    /**
     * Get the cast information for a specific movie id.
     * 
     * @param id TMDb id.
     * @return Builder instance.
     */
    public CastsBuilder casts(int id) {
        return new CastsBuilder(this, id);
    }

    /**
     * Get the basic movie information for a specific movie id.
     * 
     * @param id TMDb id.
     * @return Builder instance.
     */
    public SummaryBuilder summary(int id) {
        return new SummaryBuilder(this, id);
    }

    /**
     * Get the trailers for a specific movie id.
     * 
     * @param id TMDb id.
     * @return Builder instance.
     */
    public TrailerBuilder trailers(int id) {
        return new TrailerBuilder(this, id);
    }

    /**
     * Get the list of movies playing in theaters. This list refreshes every
     * day. The maximum number of items this list will include is 100.
     * 
     * @return Builder instance.
     */
    public NowPlayingBuilder nowPlaying() {
        return new NowPlayingBuilder(this);
    }

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes
     * every day.
     * 
     * @return Builder instance.
     */
    public PopularBuilder popular() {
        return new PopularBuilder(this);
    }

    /**
     * Get the similar movies for a specific movie id.
     * 
     * @return Builder instance.
     */
    public SimilarBuilder similarMovies(int id) {
        return new SimilarBuilder(this, id);
    }

    /**
     * Get the list of top rated movies. By default, this list will only include
     * movies that have 10 or more votes. This list refreshes every day.
     * 
     * @return Builder instance.
     */
    public TopRatedBuilder topRated() {
        return new TopRatedBuilder(this);
    }

    /**
     * Get the list of upcoming movies. This list refreshes every day. The
     * maximum number of items this list will include is 100.
     * 
     * @return Builder instance.
     */
    public UpcomingBuilder upcoming() {
        return new UpcomingBuilder(this);
    }

    public static final class CastsBuilder extends TmdbApiBuilder<Casts> {
        private static final String URI = "/movie/" + FIELD_ID + "/casts" + PARAMETER_API_KEY;

        private CastsBuilder(MoviesService service, int id) {
            super(service, new TypeToken<Casts>() {
            }, URI);

            field(FIELD_ID, id);
        }
    }

    public static final class SummaryBuilder extends TmdbApiBuilder<Movie> {
        private static final String URI = "/movie/" + FIELD_ID + PARAMETER_API_KEY + PARAMETER_LANGUAGE;

        private SummaryBuilder(MoviesService service, int id) {
            super(service, new TypeToken<Movie>() {
            }, URI);

            field(FIELD_ID, id);
        }

        /**
         * Set the language. Attention: will not default to English, but instead
         * will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public SummaryBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }
    }

    public static final class TrailerBuilder extends TmdbApiBuilder<Trailers> {
        private static final String URI = "/movie/" + FIELD_ID + "/trailers" + PARAMETER_API_KEY;

        private TrailerBuilder(MoviesService service, int id) {
            super(service, new TypeToken<Trailers>() {
            }, URI);

            field(FIELD_ID, id);
        }
    }

    public static final class NowPlayingBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/movie/now_playing" + PARAMETER_API_KEY + PARAMETER_PAGE
                + PARAMETER_LANGUAGE;

        private NowPlayingBuilder(MoviesService service) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public NowPlayingBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Set the page to return (optional). Values start at 1.
         * 
         * @param page Index of the page.
         */
        public NowPlayingBuilder page(int page) {
            parameter(PARAMETER_PAGE, page);
            return this;
        }
    }

    public static final class PopularBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/movie/popular" + PARAMETER_API_KEY + PARAMETER_PAGE
                + PARAMETER_LANGUAGE;

        private PopularBuilder(MoviesService service) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public PopularBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Set the page to return (optional). Values start at 1.
         * 
         * @param page Index of the page.
         */
        public PopularBuilder page(int page) {
            parameter(PARAMETER_PAGE, page);
            return this;
        }
    }

    public static final class SimilarBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/movie/" + FIELD_ID + "/similar_movies"
                + PARAMETER_API_KEY
                + PARAMETER_PAGE
                + PARAMETER_LANGUAGE;

        private SimilarBuilder(MoviesService service, int id) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);

            field(FIELD_ID, id);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public SimilarBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Set the page to return (optional). Values start at 1.
         * 
         * @param page Index of the page.
         */
        public SimilarBuilder page(int page) {
            parameter(PARAMETER_PAGE, page);
            return this;
        }
    }

    public static final class TopRatedBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/movie/top_rated" + PARAMETER_API_KEY + PARAMETER_PAGE
                + PARAMETER_LANGUAGE;

        private TopRatedBuilder(MoviesService service) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public TopRatedBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Set the page to return (optional). Values start at 1.
         * 
         * @param page Index of the page.
         */
        public TopRatedBuilder page(int page) {
            parameter(PARAMETER_PAGE, page);
            return this;
        }
    }

    public static final class UpcomingBuilder extends TmdbApiBuilder<ResultsPage> {
        private static final String URI = "/movie/upcoming" + PARAMETER_API_KEY + PARAMETER_PAGE
                + PARAMETER_LANGUAGE;

        private UpcomingBuilder(MoviesService service) {
            super(service, new TypeToken<ResultsPage>() {
            }, URI);
        }

        /**
         * Set the language (optional). Attention: will not default to English,
         * but instead will return empty field.
         * 
         * @param languageCode ISO 639-1 code.
         */
        public UpcomingBuilder language(String languageCode) {
            parameter(PARAMETER_LANGUAGE, languageCode);
            return this;
        }

        /**
         * Set the page to return (optional). Values start at 1.
         * 
         * @param page Index of the page.
         */
        public UpcomingBuilder page(int page) {
            parameter(PARAMETER_PAGE, page);
            return this;
        }
    }
}
