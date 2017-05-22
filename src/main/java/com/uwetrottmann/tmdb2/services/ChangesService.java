/*
 * Copyright 2017 Nikolas Mavropoylos
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

import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChangesService {
    /**
     * Get a list of all of the movie ids that have been changed in the past 24 hours.
     * <p>
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and end_date query parameters.
     * 100 items are returned per page.
     *
     * @param start_date <em>Optional.</em> Filter results with a start date.
     * @param end_date   <em>Optional.</em> Filter results with an end date.
     */
    @GET("movie/changes")
    Call<Changes> movie(
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );

    /**
     * Get a list of all of the person ids that have been changed in the past 24 hours.
     * <p>
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and end_date query parameters.
     * 100 items are returned per page.
     *
     * @param start_date <em>Optional.</em> Filter results with a start date.
     * @param end_date   <em>Optional.</em> Filter results with an end date.
     */
    @GET("person/changes")
    Call<Changes> person(
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );

    /**
     * Get a list of all of the TV show ids that have been changed in the past 24 hours.
     * <p>
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and end_date query parameters.
     * 100 items are returned per page.
     *
     * @param start_date <em>Optional.</em> Filter results with a start date.
     * @param end_date   <em>Optional.</em> Filter results with an end date.
     */
    @GET("tv/changes")
    Call<Changes> tv(
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );
}
