/*
 * Copyright 2015 Miguel Teixeira
 *
 * Modifications Copyright 2017 Nikolas Mavropoylos
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

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface TvEpisodesService {

    /**
     * Get the primary information about a TV episode by combination of a season and episode number.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param language            <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Call<TvEpisode> episode(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Query("language") String language
    );


    /**
     * Get the primary information about a TV episode by combination of a season and episode number.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param language            <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse    <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Call<TvEpisode> episode(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the primary information about a TV episode by combination of a season and episode number.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param language            <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse    <em>Optional.</em> extra requests to append to the result.
     * @param options             <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Call<TvEpisode> episode(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the changes for a TV episode. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param start_date          <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date            <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page                <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/credits")
    Call<TvEpisodeChanges> changes(
            @Path("id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the TV episode credits by combination of season and episode number.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/credits")
    Call<Credits> credits(
            @Path("id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber
    );

    /**
     * Get the external ids for a TV episode by combination of a season and episode number.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/external_ids")
    Call<ExternalIds> externalIds(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber
    );

    /**
     * Get the images (episode stills) for a TV episode by combination of a season and episode number. Since episode
     * stills don't have a language, this call will always return all images.
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/images")
    Call<Images> images(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber
    );

    /**
     * Get the videos that have been added to a TV episode (teasers, clips, etc...)
     *
     * @param tvShowId            A Tv Show TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param language            <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/videos")
    Call<Videos> videos(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Query("language") String language
    );

    /**
     * Rate a TV show.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tvShowId            TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     * @param body                <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    Call<Status> addRating(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber,
            @Body RatingObject body
    );

    /**
     * Remove your rating for a TV show.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tvShowId            TMDb id.
     * @param tvShowSeasonNumber  Season Number.
     * @param tvShowEpisodeNumber Episode Number.
     */
    @DELETE("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    Call<Status> deleteRating(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Path("episode_number") int tvShowEpisodeNumber
    );

}
