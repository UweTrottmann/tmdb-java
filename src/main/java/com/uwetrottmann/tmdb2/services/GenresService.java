package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.TmdbLocale;
import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenresService {

    /**
     * Get the list of movie genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("genre/movie/list")
    Call<GenreResults> movie(
            @Query("language") String language
    );

    /**
     * Get the list of movie genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Call<GenreResults> movie(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of movie genres.
     */
    @GET("genre/movie/list")
    Call<GenreResults> movie();

    /**
     * Get the list of TV genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("genre/tv/list")
    Call<GenreResults> tv(
            @Query("language") String language
    );

    /**
     * Get the list of TV genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/tv/list")
    Call<GenreResults> tv(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of TV genres.
     */
    @GET("genre/tv/list")
    Call<GenreResults> tv();

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @Deprecated
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("language") String language,
            @Query("include_adult") Boolean include_adult,
            @Query("sort_by") SortBy sort_by
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("language") TmdbLocale language,
            @Query("include_adult") Boolean include_adult,
            @Query("sort_by") SortBy sort_by
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("language") TmdbLocale language,
            @Query("include_adult") Boolean include_adult
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("language") TmdbLocale language,
            @Query("sort_by") SortBy sort_by
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("include_adult") Boolean include_adult,
            @Query("sort_by") SortBy sort_by
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param sort_by       <em>Optional.</em> Sort the results. Allowed Values: created_at.asc, created_at.desc
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("sort_by") SortBy sort_by
    );

    /**
     * Get a list of movies by Genre id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param genreId       A Genre TMDb id.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     */
    @GET("genre/{genre_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("genre_id") int genreId,
            @Query("include_adult") Boolean include_adult
    );

}
