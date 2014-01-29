/*
 * Copyright 2013 Uwe Trottmann
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

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.AppendToResponse;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.ReleasesResult;
import com.uwetrottmann.tmdb.entities.ResultsPage;
import com.uwetrottmann.tmdb.entities.Trailers;

import java.util.List;

import retrofit.http.EncodedQuery;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MoviesService {

    /**
     * Get the cast and crew information for a specific movie id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/movie/{id}/credits")
    Credits credits(
            @Path("id") int tmdbId
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/movie/{id}")
    Movie summary(
            @Path("id") int tmdbId
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param tmdbId   TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/{id}")
    Movie summary(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param tmdbId   TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse extra requests to append to the result.
     */
    @GET("/movie/{id}")
    Movie summary(
            @Path("id") int tmdbId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the trailers for a specific movie id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/movie/{id}/trailers")
    Trailers trailers(
            @Path("id") int tmdbId
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number
     * of items this list will include is 100.
     */
    @GET("/movie/now_playing")
    ResultsPage nowPlaying();

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number
     * of items this list will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/now_playing")
    ResultsPage nowPlaying(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     */
    @GET("/movie/popular")
    ResultsPage popular();

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/popular")
    ResultsPage popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the similar movies for a specific movie id.
     */
    @GET("/movie/{id}/similar_movies")
    ResultsPage similarMovies(
            @Path("id") int tmdbId
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/{id}/similar_movies")
    ResultsPage similarMovies(
            @Path("id") int tmdbId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10
     * or more votes. This list refreshes every day.
     */
    @GET("/movie/top_rated")
    ResultsPage topRated();

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10
     * or more votes. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/top_rated")
    ResultsPage topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items
     * this list will include is 100.
     */
    @GET("/movie/upcoming")
    ResultsPage upcoming();

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items
     * this list will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/movie/upcoming")
    ResultsPage upcoming(
            @Query("page") Integer page,
            @Query("language") String language
    );

    @GET("/movie/{id}/releases")
    ReleasesResult releases(
            @Path("id") int tmdbId
    );

}
