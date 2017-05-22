/*
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
 *
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvSeasonChanges;
import com.uwetrottmann.tmdb2.entities.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}")
    Call<TvSeason> season(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language
    );

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse   <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{tv_id}/season/{season_number}")
    Call<TvSeason> season(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse   <em>Optional.</em> extra requests to append to the result.
     * @param options            <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("tv/{tv_id}/season/{season_number}")
    Call<TvSeason> season(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Grab the following account states for a session:
     * <p>
     * Returns all of the user ratings for the season's episodes.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tmdbId             A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     */
    @GET("tv/{tv_id}/season/{season_number}/account_states")
    Call<AccountStates> accountStates(
            @Path("tv_id") int tmdbId,
            @Path("season_number") int tvShowSeasonNumber
    );

    /**
     * Get the changes for a TV show. By default only the last 24 hours are returned.
     * <p>
     * Get the changes for a TV season. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param tmdbId             A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param start_date         <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date           <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page               <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("tv/{tv_id}/season/{season_number}/changes")
    Call<TvSeasonChanges> changes(
            @Path("tv_id") int tmdbId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the cast and crew credits for a TV season by season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     */
    @GET("tv/{tv_id}/season/{season_number}/credits")
    Call<Credits> credits(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber
    );

    /**
     * Get the external ids that we have stored for a TV season by season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}/external_ids")
    Call<ExternalIds> externalIds(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language
    );

    /**
     * Get the images (posters) that we have stored for a TV season by season number.
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}/images")
    Call<Images> images(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language
    );

    /**
     * Get the videos that have been added to a TV season (trailers, teasers, etc...)
     *
     * @param tvShowId           A Tv Show Season TMDb id.
     * @param tvShowSeasonNumber Season Number.
     * @param language           <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/season/{season_number}/videos")
    Call<Videos> videos(
            @Path("tv_id") int tvShowId,
            @Path("season_number") int tvShowSeasonNumber,
            @Query("language") String language
    );


}
