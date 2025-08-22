// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;


import com.uwetrottmann.tmdb2.entities.WatchProvidersResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WatchProvidersService {

    /**
     * See <a href="https://developer.themoviedb.org/reference/watch-providers-movie-list">Movie Providers</a>.
     */
    @GET("watch/providers/movie")
    Call<WatchProvidersResults> movie(
            @Query("language") String language,
            @Query("watch_region") String watchRegion
    );

    /**
     * See <a href="https://developer.themoviedb.org/reference/watch-provider-tv-list">TV Providers</a>.
     */
    @GET("watch/providers/tv")
    Call<WatchProvidersResults> tv(
            @Query("language") String language,
            @Query("watch_region") String watchRegion
    );

}
