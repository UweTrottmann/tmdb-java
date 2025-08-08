// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Credit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CreditsService {
    /**
     * Get the basic company information for a specific company id.
     *
     * @param credit_id The Credit ID provided by a Movie/TV Show about an Actor or Crew Member.
     */
    @GET("credit/{id}")
    Call<Credit> credit(
            @Path("id") String credit_id
    );
}
