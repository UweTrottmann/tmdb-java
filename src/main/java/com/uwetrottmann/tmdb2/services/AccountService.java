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

import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.*;

public interface AccountService {

    /**
     * Get your account details.
     * <p>
     * <b>Requires an active Session.</b>
     */
    @GET("account")
    Call<Account> summary();

    /**
     * Get all of the lists created by an account. Will include private lists if you are the owner.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     */
    @GET("account/{account_id}/lists")
    Call<ListResultsPage> lists(
            @Path("account_id") Integer accountId
    );

    /**
     * Get the list of your favorite movies.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/favorite/movies")
    Call<MovieResultsPage> favoriteMovies(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Get the list of your favorite TV shows.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id..
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/favorite/tv")
    Call<TvResultsPage> favoriteTvShows(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Adds a movie or TV show as a favorite item.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param favorite  {@link FavoriteMedia FavoriteMedia} Object to define the MediaType and MediaId of the item you are adding on your favorite list.
     */
    @POST("account/{account_id}/favorite")
    Call<Status> favorite(
            @Path("account_id") Integer accountId,
            @Body FavoriteMedia favorite
    );

    /**
     * Get a list of all the movies you have rated.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/rates/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Get a list of all the TV shows you have rated.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/rates/tv")
    Call<TvResultsPage> ratedTvShows(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Get a list of all the TV episodes you have rated.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/rates/tv/episodes")
    Call<TvEpisodesResultsPage> ratedTvShowEpisodes(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Get a list of all the movies you have added to your watchlist.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/watchlist/movies")
    Call<MovieResultsPage> watchlistMovies(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Get a list of all the TV shows you have added to your watchlist.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param page      <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language  <em>Optional.</em> ISO 639-1 code.
     * @param sortBy    <em>Optional.</em> Sort the results. <b>Allowed Value(s):</b> created_at.asc, created_at.desc
     */
    @GET("account/{account_id}/watchlist/tv")
    Call<TvResultsPage> watchlistTvShows(
            @Path("account_id") Integer accountId,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("page") Integer page
    );

    /**
     * Adds a movie or TV show to your watchlist.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param accountId <em>Optional.</em> Account TMDb Id.
     * @param watchlist {@link WatchlistMedia WatchListMedia} Object to define the MediaType and MediaId of the item you are adding on your watchlist.
     */
    @POST("account/{account_id}/watchlist")
    Call<Status> watchlist(
            @Path("account_id") Integer accountId,
            @Body WatchlistMedia watchlist
    );
}
