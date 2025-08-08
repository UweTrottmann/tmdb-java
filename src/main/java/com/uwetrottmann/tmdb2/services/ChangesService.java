// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.ChangeResultsPage;
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
    Call<ChangeResultsPage> movie(
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
    Call<ChangeResultsPage> person(
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
    Call<ChangeResultsPage> tv(
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );
}
