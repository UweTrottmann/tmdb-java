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

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenreService {

    /**
     * Get the list of movie genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Call<GenreResults> movie(
            @Query("language") String language
    );

    /**
     * Get the list of TV genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/tv/list")
    Call<GenreResults> tv(
            @Query("language") String language
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> moviesById(
            @Path("genre_id") Integer genreId,
            @Query("language") String language,
            @Query("include_adult") Boolean include_adult,
            @Query("sort_by") SortBy sort_by
    );

}
