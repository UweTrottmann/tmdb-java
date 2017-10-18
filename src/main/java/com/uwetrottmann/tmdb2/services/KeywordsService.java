package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.utils.AppendToResponse;
import com.uwetrottmann.tmdb2.utils.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.Keyword;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.utils.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface KeywordsService {
    /**
     * Get keyword by id.
     *
     * @param keywordId A BaseKeyword TMDb id.
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId
    );

    /**
     * Get keyword by id.
     *
     * @param keywordId        A BaseKeyword TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get keyword by id.
     *
     * @param keywordId        A BaseKeyword TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the movies that belong to a keyword.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param keywordId A BaseKeyword TMDb id.
     */
    @GET("keyword/{keyword_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("keyword_id") Integer keywordId
    );

    /**
     * Get the movies that belong to a keyword.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param keywordId     A BaseKeyword TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     */
    @GET("keyword/{keyword_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("keyword_id") Integer keywordId,
            @Query("language") TmdbLocale language,
            @Query("include_adult") Boolean include_adult
    );

    /**
     * Get the movies that belong to a keyword.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param keywordId     A BaseKeyword TMDb id.
     * @param language      <em>Optional.</em> ISO 639-1 code.
     */
    @GET("keyword/{keyword_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("keyword_id") Integer keywordId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the movies that belong to a keyword.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     * String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param keywordId     A BaseKeyword TMDb id.
     * @param include_adult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     */
    @GET("keyword/{keyword_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("keyword_id") Integer keywordId,
            @Query("include_adult") Boolean include_adult
    );
}
