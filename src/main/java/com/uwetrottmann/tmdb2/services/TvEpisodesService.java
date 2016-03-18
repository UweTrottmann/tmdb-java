/*
 * Copyright 2015 Miguel Teixeira
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

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.Videos;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvEpisodesService {

    /**
     * Get the primary information about a TV episode by combination of a season and episode number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("/tv/{id}/season/{season_number}/episode/{episode_number}")
    TvEpisode episode(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Path("episode_number") int episodeNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );
    
    /**
     * Get the TV episode credits by combination of season and episode number.
     *
     * @param showId A themoviedb id.
     */
    @GET("/tv/{id}/season/{season_number}/episode/{episode_number}/credits")
    Credits credits(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Path("episode_number") int episodeNumber
    );
    
    /**
     * Get the external ids for a TV episode by combination of a season and episode number.
     *
     * @param showId A themoviedb id.
     */
    @GET("/tv/{id}/season/{season_number}/episode/{episode_number}/external_ids")
    ExternalIds externalIds(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Path("episode_number") int episodeNumber
    );
    
    /**
     * Get the images (episode stills) for a TV episode by combination of a season and episode number.
     * Since episode stills don't have a language, this call will always return all images.
     *
     * @param showId A themoviedb id.
     */
    @GET("/tv/{id}/season/{season_number}/episode/{episode_number}/images")
    Images images(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Path("episode_number") int episodeNumber
    );
    
    /**
     * Get the videos that have been added to a TV episode (teasers, clips, etc...)
     *
     * @param showId A themoviedb id.
     */
    @GET("/tv/{id}/season/{season_number}/episode/{episode_number}/videos")
    Videos videos(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Path("episode_number") int episodeNumber
    );

}
