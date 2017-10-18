package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GuestSessionService {

    /**
     * Get the rated movies for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @Deprecated
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("id") String session_id,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated movies for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated movies for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the rated movies for a guest session.
     *
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("id") String session_id,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated movies for a guest session.
     */
    @GET("guest_session/{id}/rated/movies")
    Call<MovieResultsPage> ratedMovies(
            @Path("id") String session_id

    );

    /**
     * Get the rated TV shows for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @Deprecated
    @GET("guest_session/{id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
            @Path("id") String session_id,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV shows for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV shows for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("guest_session/{id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the rated TV shows for a guest session.
     *
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
            @Path("id") String session_id,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV shows for a guest session.
     */
    @GET("guest_session/{id}/rated/tv")
    Call<TvShowResultsPage> ratedTvShows(
            @Path("id") String session_id
    );

    /**
     * Get the rated TV episodes for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @Deprecated
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvEpisodes(
            @Path("id") String session_id,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV episodes for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvEpisodes(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV episodes for a guest session.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvEpisodes(
            @Path("id") String session_id,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the rated TV episodes for a guest session.
     *
     * @param sortBy   <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvEpisodes(
            @Path("id") String session_id,
            @Query("sort_by") SortBy sortBy
    );

    /**
     * Get the rated TV episodes for a guest session.
     */
    @GET("guest_session/{id}/rated/tv/episodes")
    Call<TvEpisodeResultsPage> ratedTvEpisodes(
            @Path("id") String session_id
    );
}
