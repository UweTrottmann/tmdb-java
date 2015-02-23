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

import java.util.Date;

import com.uwetrottmann.tmdb.entities.AppendToDiscoverResponse;
import com.uwetrottmann.tmdb.entities.FindResults;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import com.uwetrottmann.tmdb.enumerations.SortBy;

import retrofit.http.GET;
import retrofit.http.Query;

public interface DiscoverService {

    /**
     * Discover movies by different types of data like average rating, number of votes and genres.
     *
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles.
     * Expected value is a boolean, true or false. Default is false.
     * @param include_video <em>Optional.</em> Toggle the inclusion of items marked as a video.
     * Expected value is a boolean, true or false. Default is true.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param primary_release_year <em>Optional.</em> Filter the results so that only the primary release date
     * year has this value. Expected value is a year.
     * @param primary_release_date.gte <em>Optional.</em> Filter by the primary release date and only include
     * those which are greater than or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param primary_release_date.lte <em>Optional.</em> Filter by the primary release date and only include
     * those which are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param release_date.gte <em>Optional.</em> Filter by all available release dates and only include those
     * which are greater or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param release_date.lte <em>Optional.</em> Filter by all available release dates and only include those
     * which are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param sort_by <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     * vote_count.asc, vote_count.desc
     * @param vote_count.gte <em>Optional.</em> Filter movies by their vote count and only include movies that
     * have a vote count that is equal to or lower than the specified value.
     * @param vote_count.lte <em>Optional.</em> Filter movies by their vote count and only include movies that
     * have a vote count that is equal to or lower than the specified value. Expected value is an integer.
     * @param vote_average.gte <em>Optional.</em> Filter movies by their vote average and only include those that
     * have an average rating that is equal to or higher than the specified value. Expected value is a float.
     * @param vote_average.lte <em>Optional.</em> Filter movies by their vote average and only include those that
     * have an average rating that is equal to or lower than the specified value. Expected value is a float.
     * @param with_cast <em>Optional.</em> Only include movies that have this person id added as a cast member.
     * Expected value is an integer (the id of a person).
     * @param with_crew <em>Optional.</em> Only include movies that have this person id added as a crew member.
     * Expected value is an integer (the id of a person).
     * @param with_companies <em>Optional.</em> Filter movies to include a specific company. Expected value is
     * an integer (the id of a company).
     * @param with_genres <em>Optional.</em> Only include movies with the specified genres. Expected value is
     * an integer (the id of a genre). Multiple values can be specified.
     * @param with_keywords <em>Optional.</em> Only include movies with the specified genres. Expected value is
     * an integer (the id of a genre). Multiple values can be specified.
     * @param with_people <em>Optional.</em> Only include movies that have these person id's added as a cast or
     * crew member. Expected value is an integer (the id or ids of a person).
     * @param year <em>Optional.</em> Filter the results by all available release dates that have the specified
     * value added as a year. Expected value is an integer (year).
     * 
     * @return
     */
    @GET("/discover/movie")
    FindResults discoverMovie(
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo,
            @Query("language") String language,
            @Query("page") Integer page,
            @Query("primary_release_year") String primaryReleaseYear,
            @Query("primary_release_date.gte") Date primaryReleaseYearGte,
            @Query("primary_release_date.lte") Date primaryReleaseYearLte,
            @Query("release_date.gte") Date releaseDateGte,
            @Query("release_date.lte") Date releaseDateLte,
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
     * Discover movies by different types of data like average rating, number of votes and genres.
     *
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles.
     * Expected value is a boolean, true or false. Default is false.
     * @param include_video <em>Optional.</em> Toggle the inclusion of items marked as a video.
     * Expected value is a boolean, true or false. Default is true.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param primary_release_year <em>Optional.</em> Filter the results so that only the primary release date
     * year has this value. Expected value is a year.
     * @param primary_release_date.gte <em>Optional.</em> Filter by the primary release date and only include
     * those which are greater than or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param primary_release_date.lte <em>Optional.</em> Filter by the primary release date and only include
     * those which are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param release_date.gte <em>Optional.</em> Filter by all available release dates and only include those
     * which are greater or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param release_date.lte <em>Optional.</em> Filter by all available release dates and only include those
     * which are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * @param sort_by <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     * vote_count.asc, vote_count.desc
     * @param with_cast <em>Optional.</em> Only include movies that have this person id added as a cast member.
     * Expected value is an integer (the id of a person).
     * @param with_crew <em>Optional.</em> Only include movies that have this person id added as a crew member.
     * Expected value is an integer (the id of a person).
     * @param with_companies <em>Optional.</em> Filter movies to include a specific company. Expected value is
     * an integer (the id of a company).
     * @param with_genres <em>Optional.</em> Only include movies with the specified genres. Expected value is
     * an integer (the id of a genre). Multiple values can be specified.
     * @param with_keywords <em>Optional.</em> Only include movies with the specified genres. Expected value is
     * an integer (the id of a genre). Multiple values can be specified.
     * @param with_people <em>Optional.</em> Only include movies that have these person id's added as a cast or
     * crew member. Expected value is an integer (the id or ids of a person).
     * @param year <em>Optional.</em> Filter the results by all available release dates that have the specified
     * value added as a year. Expected value is an integer (year).
     * 
     * @return
     */
    @GET("/discover/movie")
    MovieResultsPage discoverMovie(
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo,
            @Query("language") String language,
            @Query("page") Integer page,
            @Query("primary_release_year") String primaryReleaseYear,
            @Query("primary_release_date.gte") Date primaryReleaseYearGte,
            @Query("primary_release_date.lte") Date primaryReleaseYearLte,
            @Query("release_date.gte") Date releaseDateGte,
            @Query("release_date.lte") Date releaseDateLte,
            @Query("sort_by") SortBy sortBy,
            @Query("with_cast") AppendToDiscoverResponse withCast,
            @Query("with_crew") AppendToDiscoverResponse withCrew,
            @Query("with_companies") AppendToDiscoverResponse withCompanies,
            @Query("with_genres") AppendToDiscoverResponse withGenres,
            @Query("with_keywords") AppendToDiscoverResponse withKeywords,
            @Query("with_people") AppendToDiscoverResponse withPeople,
            @Query("year") Integer year
    );
    
    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres,
     * the network they aired on and air dates.
     *
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sort_by <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     * vote_count.asc, vote_count.desc
     * @param first_air_date_year <em>Optional.</em> Filter the results release dates to matches that include
     * this value. Expected value is a year.
     * @param vote_count.gte <em>Optional.</em> Only include TV shows that are equal to, or have a vote count
     * higher than this value. Expected value is an integer.
     * @param vote_average.gte <em>Optional.</em> Only include TV shows that are equal to, or have a higher
     * average rating than this value. Expected value is a float.
     * @param with_genres <em>Optional.</em> Only include TV shows with the specified genres. Expected value
     * is an integer (the id of a genre). Multiple values can be specified.
     * @param with_networks <em>Optional.</em> Filter TV shows to include a specific network.
     * Expected value is an integer (the id of a network).
     * @param first_air_date.gte <em>Optional.</em> The minimum release to include. Expected format is YYYY-MM-DD.
     * @param first_air_date.lte <em>Optional.</em> The maximum release to include. Expected format is YYYY-MM-DD.
     * 
     * @return
     */
    @GET("/discover/tv")
    MovieResultsPage discoverTv(
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("first_air_date_year") String firstAirDateYear,
            @Query("vote_count.gte") Integer voteCountGte,
            @Query("vote_average.gte") Float voteAverageGte,
            @Query("with_genres") AppendToDiscoverResponse withGenres,
            @Query("with_networks") AppendToDiscoverResponse withNetworks,
            @Query("first_air_date.gte") Date firstAirDateGte,
            @Query("first_air_date.lte") Date firstAirDateLte
    );
    
    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres,
     * the network they aired on and air dates.
     *
     * @param page <em>Optional.</em> Minimum 1, maximum 1000.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sort_by <em>Optional.</em> Available options are: popularity.asc, popularity.desc, release_date.asc,
     * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     * vote_count.asc, vote_count.desc
     * @param first_air_date_year <em>Optional.</em> Filter the results release dates to matches that include
     * this value. Expected value is a year.
     * @param with_genres <em>Optional.</em> Only include TV shows with the specified genres. Expected value
     * is an integer (the id of a genre). Multiple values can be specified.
     * @param with_networks <em>Optional.</em> Filter TV shows to include a specific network.
     * Expected value is an integer (the id of a network).
     * @param first_air_date.gte <em>Optional.</em> The minimum release to include. Expected format is YYYY-MM-DD.
     * @param first_air_date.lte <em>Optional.</em> The maximum release to include. Expected format is YYYY-MM-DD.
     * 
     * @return
     */
    @GET("/discover/tv")
    TvResultsPage discoverTv(
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("first_air_date_year") String firstAirDateYear,
            @Query("with_genres") AppendToDiscoverResponse withGenres,
            @Query("with_networks") AppendToDiscoverResponse withNetworks,
            @Query("first_air_date.gte") Date firstAirDateGte,
            @Query("first_air_date.lte") Date firstAirDateLte
    );

}
