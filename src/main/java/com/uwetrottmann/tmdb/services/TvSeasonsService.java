/*
 * Copyright 2014 Uwe Trottmann
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

import com.uwetrottmann.tmdb.entities.AppendToResponse;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import com.uwetrottmann.tmdb.entities.Images;
import com.uwetrottmann.tmdb.entities.TvSeason;
import com.uwetrottmann.tmdb.entities.Videos;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("/tv/{id}/season/{season_number}")
    TvSeason season(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );
    
    /**
     * Get the cast and crew credits for a TV season by season number.
     *
     * @param showId A themoviedb id.
     */
    @GET("/tv/{id}/season/{season_number}/credits")
    Credits credits(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber
    );
    
    /**
     * Get the external ids that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/season/{season_number}/external_ids")
    ExternalIds externalIds(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );
    
    /**
     * Get the images (posters) that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/season/{season_number}/images")
    Images images(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );
    
    /**
     * Get the videos that have been added to a TV season (trailers, teasers, etc...)
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/season/{season_number}/videos")
    Videos videos(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

}
