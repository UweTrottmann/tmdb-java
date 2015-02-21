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

import com.uwetrottmann.tmdb.entities.CollectionResultsPage;
import com.uwetrottmann.tmdb.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.PersonResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SearchService {

    /**
     * Search for companies by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("/search/company")
    CompanyResultsPage company(
            @Query("query") String query,
            @Query("page") Integer page
    );
    
    /**
     * Search for collections by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/search/collection")
    CollectionResultsPage collection(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language
    );
    
    /**
     * Search for keywords by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("/search/collection")
    KeywordResultsPage keyword(
            @Query("query") String query,
            @Query("page") Integer page
    );
    
    /**
     * Search for movies by title.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param includeAdult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param year <em>Optional.</em> Filter the results release dates to matches that include this value.
     * @param primaryReleaseYear <em>Optional.</em> Filter the results so that only the primary release dates have this
     * value.
     * @param searchType <em>Optional.</em> By default, the search type is 'phrase'. This is almost guaranteed the
     * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("/search/movie")
    MovieResultsPage movie(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("include_adult") Boolean includeAdult,
            @Query("year") Integer year,
            @Query("primary_release_year") Integer primaryReleaseYear,
            @Query("search_type") String searchType
    );
    
    /**
     * Search for people by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param includeAdult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param searchType <em>Optional.</em> By default, the search type is 'phrase'. This is almost guaranteed the
     * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("/search/person")
    PersonResultsPage person(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("include_adult") Boolean includeAdult,
            @Query("search_type") String searchType
    );

    /**
     * Search for TV shows by title.
     *
     * @param query CGI escaped string
     * @param page Minimum 1, maximum 1000.
     * @param language ISO 639-1 code.
     * @param firstAirDateYear Filter the results to only match shows that have an air date with this value.
     * @param searchType By default, the search type is 'phrase'. This is almost guaranteed the option you will want.
     * It's a great all purpose search type and by far the most tuned for every day querying. For those wanting more of
     * an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("/search/tv")
    TvResultsPage tv(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("first_air_date_year") Integer firstAirDateYear,
            @Query("search_type") String searchType
    );
}
