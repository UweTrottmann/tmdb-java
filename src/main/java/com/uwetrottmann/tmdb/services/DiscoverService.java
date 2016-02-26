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

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.AppendToDiscoverResponse;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TmdbDate;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import com.uwetrottmann.tmdb.enumerations.SortBy;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.Date;

public interface DiscoverService {

    /**
     * Discover movies by different types of data like average rating, number of votes and genres.
     *
     * @param includeAdult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is a boolean, true or
     * false. Default is false.
     * @param includeVideo <em>Optional.</em> Toggle the inclusion of items marked as a video. Expected value is a
     * boolean, true or false. Default is true.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param primaryReleaseYear <em>Optional.</em> Filter the results so that only the primary release date year has
     * this value. Expected value is a year.
     * @param primaryReleaseYearGte <em>Optional.</em> Filter by the primary release date and only include those which
     * are greater than or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param primaryReleaseYearLte <em>Optional.</em> Filter by the primary release date and only include those which
     * are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param releaseDateGte <em>Optional.</em> Filter by all available release dates and only include those which are
     * greater or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param releaseDateLte <em>Optional.</em> Filter by all available release dates and only include those which are
     * less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param sortBy <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     * @param voteCountGte <em>Optional.</em> Filter movies by their vote count and only include movies that have a vote
     * count that is equal to or lower than the specified value.
     * @param voteCountLte <em>Optional.</em> Filter movies by their vote count and only include movies that have a vote
     * count that is equal to or lower than the specified value. Expected value is an integer.
     * @param voteAverageGte <em>Optional.</em> Filter movies by their vote average and only include those that have an
     * average rating that is equal to or higher than the specified value. Expected value is a float.
     * @param voteAverageLte <em>Optional.</em> Filter movies by their vote average and only include those that have an
     * average rating that is equal to or lower than the specified value. Expected value is a float.
     * @param withCast <em>Optional.</em> Only include movies that have this person id added as a cast member. Expected
     * value is an integer (the id of a person).
     * @param withCrew <em>Optional.</em> Only include movies that have this person id added as a crew member. Expected
     * value is an integer (the id of a person).
     * @param withCompanies <em>Optional.</em> Filter movies to include a specific company. Expected value is an integer
     * (the id of a company).
     * @param withGenres <em>Optional.</em> Only include movies with the specified genres. Expected value is an integer
     * (the id of a genre). Multiple values can be specified.
     * @param withKeywords <em>Optional.</em> Only include movies with the specified genres. Expected value is an
     * integer (the id of a genre). Multiple values can be specified.
     * @param withPeople <em>Optional.</em> Only include movies that have these person id's added as a cast or crew
     * member. Expected value is an integer (the id or ids of a person).
     * @param year <em>Optional.</em> Filter the results by all available release dates that have the specified value
     * added as a year. Expected value is an integer (year).
     */
    @GET("/discover/movie")
    MovieResultsPage discoverMovie(
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo,
            @Query("language") String language,
            @Query("page") Integer page,
            @Query("primary_release_year") String primaryReleaseYear,
            @Query("primary_release_date.gte") TmdbDate primaryReleaseYearGte,
            @Query("primary_release_date.lte") TmdbDate primaryReleaseYearLte,
            @Query("release_date.gte") TmdbDate releaseDateGte,
            @Query("release_date.lte") TmdbDate releaseDateLte,
            @Query("sort_by") SortBy sortBy,
            @Query("vote_count.gte") Integer voteCountGte,
            @Query("vote_count.lte") Integer voteCountLte,
            @Query("vote_average.gte") Float voteAverageGte,
            @Query("vote_average.lte") Float voteAverageLte,
            @Query("with_cast") AppendToDiscoverResponse withCast,
            @Query("with_crew") AppendToDiscoverResponse withCrew,
            @Query("with_companies") AppendToDiscoverResponse withCompanies,
            @Query("with_genres") AppendToDiscoverResponse withGenres,
            @Query("with_keywords") AppendToDiscoverResponse withKeywords,
            @Query("with_people") AppendToDiscoverResponse withPeople,
            @Query("year") Integer year
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
    @GET("/discover/tv")
    TvResultsPage discoverTv(
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("first_air_date_year") String firstAirDateYear,
            @Query("vote_count.gte") Integer voteCountGte,
            @Query("vote_average.gte") Float voteAverageGte,
            @Query("with_genres") AppendToDiscoverResponse withGenres,
            @Query("with_networks") AppendToDiscoverResponse withNetworks,
            @Query("first_air_date.gte") TmdbDate firstAirDateGte,
            @Query("first_air_date.lte") TmdbDate firstAirDateLte
    );

}
