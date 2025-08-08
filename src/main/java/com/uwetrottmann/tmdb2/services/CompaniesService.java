// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CompaniesService {
    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId A Company TMDb id.
     */
    @GET("company/{company_id}")
    Call<Company> summary(
            @Path("company_id") int companyId
    );

    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId        A Company TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     */
    @GET("company/{company_id}")
    Call<Company> summary(
            @Path("company_id") int companyId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId        A Company TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("company/{company_id}")
    Call<Company> summary(
            @Path("company_id") int companyId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the movies for a specific A Company TMDb id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param companyId A Company TMDb id.
     */
    @GET("company/{company_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("company_id") int companyId
    );
}

