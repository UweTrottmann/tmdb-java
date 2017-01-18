/*
 * Copyright 2015 Miguel Teixeira
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
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverService {

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and certifications.
     * @see <a href="https://developers.themoviedb.org/3/discover/movie-discover">Movie Discover</a>
     */
    @GET("discover/movie")
    Call<MovieResultsPage> discoverMovie(
            @Query("language") String language,
            @Query("region") String region,
            @Query("sort_by") SortBy sort_by,
            @Query("certification_country") String certification_country,
            @Query("certification") String certification,
            @Query("certification_lte") String certification_lte,
            @Query("include_adult") Boolean include_adult,
            @Query("include_video") Boolean include_video,
            @Query("page") Integer page,
            @Query("primary_release_year") Integer primary_release_year,
            @Query("primary_release_date.gte") TmdbDate primary_release_date_gte,
            @Query("primary_release_date.lte") TmdbDate primary_release_date_lte,
            @Query("release_date.gte") TmdbDate release_date_gte,
            @Query("release_date.lte") TmdbDate release_date_lte,
            @Query("vote_count.gte") Integer vote_count_gte,
            @Query("vote_count.lte") Integer vote_count_lte,
            @Query("vote_average.gte") Float vote_average_gte,
            @Query("vote_average.lte") Float vote_average_lte,
            @Query("with_cast") DiscoverFilter with_cast,
            @Query("with_crew") DiscoverFilter with_crew,
            @Query("with_companies") DiscoverFilter with_companies,
            @Query("with_genres") DiscoverFilter with_genres,
            @Query("with_keywords") DiscoverFilter with_keywords,
            @Query("with_people") DiscoverFilter with_people,
            @Query("year") Integer year,
            @Query("without_genres") DiscoverFilter without_genres,
            @Query("with_runtime.gte") Integer with_runtime_gte,
            @Query("with_runtime.lte") Integer with_runtime_lte,
            @Query("with_release_type") DiscoverFilter with_release_type,
            @Query("with_original_language") String with_original_language
    );

    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres, the network they aired
     * on and air dates.
     *
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     * @param firstAirDateYear <em>Optional.</em> Filter the results release dates to matches that include this value.
     * Expected value is a year.
     * @param voteCountGte <em>Optional.</em> Only include TV shows that are equal to, or have a vote count higher than
     * this value. Expected value is an integer.
     * @param voteAverageGte <em>Optional.</em> Only include TV shows that are equal to, or have a higher average rating
     * than this value. Expected value is a float.
     * @param withGenres <em>Optional.</em> Only include TV shows with the specified genres. Expected value is an
     * integer (the id of a genre). Multiple values can be specified.
     * @param withNetworks <em>Optional.</em> Filter TV shows to include a specific network. Expected value is an
     * integer (the id of a network).
     * @param firstAirDateGte <em>Optional.</em> The minimum release to include. Expected format is YYYY-MM-DD.
     * @param firstAirDateLte <em>Optional.</em> The maximum release to include. Expected format is YYYY-MM-DD.
     */
    @GET("discover/tv")
    Call<TvResultsPage> discoverTv(
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("first_air_date_year") String firstAirDateYear,
            @Query("vote_count.gte") Integer voteCountGte,
            @Query("vote_average.gte") Float voteAverageGte,
            @Query("with_genres") DiscoverFilter withGenres,
            @Query("with_networks") DiscoverFilter withNetworks,
            @Query("first_air_date.gte") TmdbDate firstAirDateGte,
            @Query("first_air_date.lte") TmdbDate firstAirDateLte
    );

}
