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

import com.uwetrottmann.tmdb.entities.ResultsPage;

import retrofit.http.GET;
import retrofit.http.Query;

public interface SearchService {

    /**
     * Search for movies by title.
     *
     * @param query CGI escaped string
     */
    @GET("/search/movie")
    ResultsPage movie(
            @Query("query") String query
    );

    /**
     * Search for movies by title.
     *
     * @param query              CGI escaped string
     * @param page               <em>Optional.</em> Minimum value is 1, expected value is an
     *                           integer.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     * @param includeAdult       <em>Optional.</em> Toggle the inclusion of adult titles. Expected
     *                           value is: true or false
     * @param year               <em>Optional.</em> Filter the results release dates to matches that
     *                           include this value.
     * @param primaryReleaseYear <em>Optional.</em> Filter the results so that only the primary
     *                           release dates have this value.
     * @param searchType         <em>Optional.</em> By default, the search type is 'phrase'. This is
     *                           almost guaranteed the option you will want. It's a great all
     *                           purpose search type and by far the most tuned for every day
     *                           querying. For those wanting more of an "autocomplete" type search,
     *                           set this option to 'ngram'.
     */
    @GET("/search/movie")
    ResultsPage movie(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("include_adult") Boolean includeAdult,
            @Query("year") Integer year,
            @Query("primary_release_year") Integer primaryReleaseYear,
            @Query("search_type") String searchType
    );

}
