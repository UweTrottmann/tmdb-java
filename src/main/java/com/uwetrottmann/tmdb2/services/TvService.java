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

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface TvService {

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}")
    Call<TvShow> tv(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId         A Tv Show TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{tv_id}")
    Call<TvShow> tv(
            @Path("tv_id") int tvShowId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tvShowId         A Tv Show TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("tv/{tv_id}")
    Call<TvShow> tv(
            @Path("tv_id") int tvShowId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Grab the following account states for a session:
     * <p>
     * * TV show rating<br>
     * * If it belongs to your watchlist<br>
     * * If it belongs to your favourite list<br>
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tvShowId TMDb id.
     */
    @GET("movie/{tv_id}/account_states")
    Call<AccountStates> accountStates(
            @Path("tv_id") int tvShowId
    );

    /**
     * Get the alternative titles for a specific show ID.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/alternative_titles")
    Call<TvAlternativeTitles> alternativeTitles(
            @Path("tv_id") int tvShowId
    );

    /**
     * Get the changes for a TV show. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     * <p>
     * TV show changes are different than movie changes in that there are some edits on seasons and episodes that will create a change entry at the show level.<br>
     * These can be found under the season and episode keys.<br>
     * These keys will contain a series_id and episode_id.<br>
     * You can use the season changes and episode changes methods to look these up individually.
     *
     * @param tvShowId   A Tv Show TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("tv/{tv_id}/changes")
    Call<TvShowChanges> changes(
            @Path("tv_id") int tvShowId,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/credits")
    Call<Credits> credits(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the content ratings for a specific TV show.
     *
     * @param tmbdId A Tmdb id
     */
    @GET("tv/{tv_id}/content_ratings")
    Call<TvContentRatings> content_ratings(
            @Path("tv_id") int tmbdId
    );

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/external_ids")
    Call<ExternalIds> externalIds(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the images (posters and backdrops) for a TV series.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/images")
    Call<Images> images(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the plot keywords for a specific TV show id.
     *
     * @param tvShowId A Tv Show TMDb id.
     */
    @GET("tv/{tv_id}/keywords")
    Call<TvKeywords> keywords(
            @Path("tv_id") int tvShowId
    );

    /**
     * Get the list of TV show recommendations for this item.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/recommendations")
    Call<TvResultsPage> recommendations(
            @Path("tv_id") int tvShowId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the similar TV shows for a specific tv id.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/similar")
    Call<TvResultsPage> similar(
            @Path("tv_id") int tvShowId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get a list of the translations that exist for a TV show.
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/translations")
    Call<Translations> translations(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the videos that have been added to a TV series (trailers, opening credits, etc...)
     *
     * @param tvShowId A Tv Show TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{tv_id}/videos")
    Call<Videos> videos(
            @Path("tv_id") int tvShowId,
            @Query("language") String language
    );

    /**
     * Get the latest TV show id.
     */
    @GET("tv/latest")
    Call<TvShow> latest();

    /**
     * Get the list of TV shows that are currently on the air. This query looks for any TV show that has an episode with
     * an air date in the next 7 days.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/on_the_air")
    Call<TvResultsPage> onTheAir(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of TV shows that air today. Without a specified timezone, this query defaults to EST (Eastern Time
     * UTC-05:00).
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/airing_today")
    Call<TvResultsPage> airingToday(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated TV shows. By default, this list will only include TV shows that have 2 or more votes.
     * This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/top_rated")
    Call<TvResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular TV shows. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/popular")
    Call<TvResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Rate a TV show.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tvShowId TMDb id.
     * @param body     <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("tv/{tv_id}/rating")
    Call<Status> addRating(
            @Path("tv_id") Integer tvShowId,
            @Body RatingObject body
    );

    /**
     * Remove your rating for a TV show.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param tvShowId TMDb id.
     */
    @DELETE("tv/{tv_id}/rating")
    Call<Status> deleteRating(
            @Path("tv_id") Integer tvShowId
    );

}
