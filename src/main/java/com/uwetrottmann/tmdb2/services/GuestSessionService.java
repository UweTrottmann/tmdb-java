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

import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TvEpisodesResultsPage;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GuestSessionService {

    /**
     * Get the rated movies for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV shows for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv")
    Call<TvResultsPage> ratedTVShows(
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV episodes for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodesResultsPage> ratedTVEpisodes(
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );
}
