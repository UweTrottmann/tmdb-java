// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Account;
import com.uwetrottmann.tmdb2.entities.FavoriteMedia;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.entities.WatchlistMedia;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<TvShowResultsPage> favoriteTvShows(
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
    @GET("account/{account_id}/rated/movies")
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
    @GET("account/{account_id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
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
    @GET("account/{account_id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvShowEpisodes(
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
    Call<TvShowResultsPage> watchlistTvShows(
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
