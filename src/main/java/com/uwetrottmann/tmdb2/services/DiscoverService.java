/*
 * Copyright 2015 Miguel Teixeira
 *
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
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverService {

    /**
     * <p>Discover movies by different types of data like average rating, number of votes, genres and certifications.<br>
     * You can get a valid list of certifications from the /certifications method.</p>
     * <p>Discover also supports a nice list of sort options. See below for all of the available options.</p>
     * <p>Please note, when using certification \ certification.lte you must also specify certification_country.<br>
     * These two parameters work together in order to filter the results. You can only filter results with the countries we have added to our certifications list.</p>
     * <p>If you specify the region parameter, the regional release date will be used instead of the primary release date.<br>
     * The date returned will be the first date based on your query (ie. if a with_release_type is specified).<br>
     * It's important to note the order of the release types that are used.<br>
     * Specifying "2|3" would return the limited theatrical release date as opposed to "3|2" which would return the theatrical date.</p>
     *
     * @param language                 <em>Optional.</em> ISO 639-1 code.
     * @param region                   <em>Optional.</em> ISO 3166-1 code.
     * @param sort_by                  <em>Optional.</em> <b>Allowed Values:</b> , popularity.asc, popularity.desc, release_date.asc, release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc, original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     *                                 <b>Default:</b> popularity.desc
     * @param certification_country    <em>Optional.</em> Used in conjunction with the certification filter, use this to specify a country with a valid certification.
     * @param certification            <em>Optional.</em> Filter results with a valid certification from the 'certification_country' field.
     * @param certification_lte        <em>Optional.</em> Filter and only include movies that have a certification that is less than or equal to the specified value.
     * @param include_adult            <em>Optional.</em> A filter and include or exclude adult movies. Expected values: true/false.
     * @param include_video            <em>Optional.</em> A filter to include or exclude videos. Expected Values: true/false.
     * @param page                     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param primary_release_year     <em>Optional.</em> A filter to limit the results to a specific primary release year.
     * @param primary_release_date_gte <em>Optional.</em> Filter and only include movies that have a primary release date that is greater or equal to the specified value.
     * @param primary_release_date_lte <em>Optional.</em> Filter and only include movies that have a primary release date that is less than or equal to the specified value.
     * @param release_date_gte         <em>Optional.</em> Filter and only include movies that have a release date (looking at all release dates) that is greater or equal to the specified value.
     * @param release_date_lte         <em>Optional.</em> Filter and only include movies that have a release date (looking at all release dates) that is less than or equal to the specified value.
     * @param vote_count_gte           <em>Optional.</em> Filter and only include movies that have a vote count that is greater or equal to the specified value.
     * @param vote_count_lte           <em>Optional.</em> Filter and only include movies that have a vote count that is less than or equal to the specified value.
     * @param vote_average_gte         <em>Optional.</em> Filter and only include movies that have a rating that is greater or equal to the specified value.
     * @param vote_average_lte         <em>Optional.</em> Filter and only include movies that have a rating that is less than or equal to the specified value.
     * @param with_cast                <em>Optional.</em> Only include movies that have one of the ID's added as an actor.
     * @param with_crew                <em>Optional.</em> Only include movies that have one of the ID's added as a crew member.
     * @param with_companies           <em>Optional.</em> Only include movies that have one of the ID's added as a production company.
     * @param with_genres              <em>Optional.</em> Only include movies that have one of the ID's added as a genre.
     * @param with_keywords            <em>Optional.</em> Only include movies that have one of the ID's added as a keyword.
     * @param with_people              <em>Optional.</em> Only include movies that have one of the ID's added as a either a actor or a crew member.
     * @param year                     <em>Optional.</em> A filter to limit the results to a specific year (looking at all release dates).
     * @param without_genres           <em>Optional.</em> Genre ids that you want to exclude from the results.
     * @param with_runtime_gte         <em>Optional.</em> Filter and only include movies that have a runtime that is greater or equal to a value.
     * @param with_runtime_lte         <em>Optional.</em> Filter and only include movies that have a runtime that is less than or equal to a value.
     * @param with_release_type        <em>Optional.</em> These release types map to the same values found on the movie release date method.
     * @param with_original_language   <em>Optional.</em> ISO 639-1 string to filter results by their original language value.
     * @param without_keywords         <em>Optional.</em> Exclude items with certain keywords.
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
            @Query("with_original_language") String with_original_language,
            @Query("without_keywords") DiscoverFilter without_keywords
    );

    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres, the network they aired
     * on and air dates.
     *
     * @param language                     <em>Optional.</em> ISO 639-1 code.
     * @param sort_by                      <em>Optional.</em> <b>Allowed Values:</b> , popularity.asc, popularity.desc, release_date.asc, release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc, original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     *                                     <b>Default:</b> popularity.desc
     * @param air_date_gte                 <em>Optional.</em> Filter and only include TV shows that have a air date (by looking at all episodes) that is greater or equal to the specified value.
     * @param air_date_lte                 <em>Optional.</em> Filter and only include TV shows that have a air date (by looking at all episodes) that is less than or equal to the specified value.
     * @param first_air_date_gte           <em>Optional.</em> Filter and only include TV shows that have a original air date that is greater or equal to the specified value. Can be used in conjunction with the "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param first_air_date_lte           <em>Optional.</em> Filter and only include TV shows that have a original air date that is less than or equal to the specified value. Can be used in conjunction with the "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param first_air_date_year          <em>Optional.</em> Filter and only include TV shows that have a original air date year that equal to the specified value. Can be used in conjunction with the "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param page                         <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param timezone                     <em>Optional.</em> Used in conjunction with the air_date.gte/lte filter to calculate the proper UTC offset.
     * @param vote_average_gte             <em>Optional.</em> Filter and only include movies that have a rating that is greater or equal to the specified value.
     * @param vote_count_gte               <em>Optional.</em> Filter and only include movies that have a rating that is less than or equal to the specified value.
     * @param with_genres                  <em>Optional.</em> Genre ids that you want to include in the results.
     * @param with_networks                <em>Optional.</em> Network ids that you want to include in the results.
     * @param without_genres               <em>Optional.</em> Genre ids that you want to exclude from the results.
     * @param with_runtime_gte             <em>Optional.</em> Filter and only include movies that have a runtime that is greater or equal to a value.
     * @param with_runtime_lte             <em>Optional.</em> Filter and only include movies that have a runtime that is less than or equal to a value.
     * @param include_null_first_air_dates <em>Optional.</em> Use this filter to include TV shows that don't have an air date while using any of the "first_air_date" filters.
     * @param with_original_language       <em>Optional.</em> ISO 639-1 string to filter results by their original language value.
     * @param without_keywords             <em>Optional.</em> Exclude items with certain keywords.
     * @see <a href="https://developers.themoviedb.org/3/discover/tv-discover">TV Discover</a>
     */
    @GET("discover/tv")
    Call<TvShowResultsPage> discoverTv(
            @Query("language") String language,
            @Query("sort_by") SortBy sort_by,
            @Query("air_date.gte") TmdbDate air_date_gte,
            @Query("air_date.lte") TmdbDate air_date_lte,
            @Query("first_air_date.gte") TmdbDate first_air_date_gte,
            @Query("first_air_date.lte") TmdbDate first_air_date_lte,
            @Query("first_air_date_year") Integer first_air_date_year,
            @Query("page") Integer page,
            @Query("timezone") String timezone,
            @Query("vote_average.gte") Float vote_average_gte,
            @Query("vote_count.gte") Integer vote_count_gte,
            @Query("with_genres") DiscoverFilter with_genres,
            @Query("with_networks") DiscoverFilter with_networks,
            @Query("without_genres") DiscoverFilter without_genres,
            @Query("with_runtime.gte") Integer with_runtime_gte,
            @Query("with_runtime.lte") Integer with_runtime_lte,
            @Query("include_null_first_air_dates") Boolean include_null_first_air_dates,
            @Query("with_original_language") String with_original_language,
            @Query("without_keywords") DiscoverFilter without_keywords
    );

}
