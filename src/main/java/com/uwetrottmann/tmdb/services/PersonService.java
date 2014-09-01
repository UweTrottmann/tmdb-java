/*
 * Copyright 2014 Chris Banes
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

import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.PersonCredits;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface PersonService {

    /**
     * Get the general person information for a specific id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/person/{id}")
    Person summary(
            @Path("id") int tmdbId
    );

    /**
     * Async variant of {@link #summary(int)}
     */
    @GET("/person/{id}")
    void summary(
            @Path("id") int tmdbId,
            Callback<Person> callback
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/person/{id}/movie_credits")
    PersonCredits movieCredits(
            @Path("id") int tmdbId
    );

    /**
     * Async variant of {@link #movieCredits(int)}
     */
    @GET("/person/{id}/movie_credits")
    void movieCredits(
            @Path("id") int tmdbId,
            Callback<PersonCredits> callback
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param tmdbId   TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/person/{id}/movie_credits")
    PersonCredits movieCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Async variant of {@link #movieCredits(int, String)}
     */
    @GET("/person/{id}/movie_credits")
    void movieCredits(
            @Path("id") int tmdbId,
            @Query("language") String language,
            Callback<PersonCredits> callback
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/person/{id}/tv_credits")
    PersonCredits tvCredits(
            @Path("id") int tmdbId
    );

    /**
     * Async variant of {@link #tvCredits(int)}
     */
    @GET("/person/{id}/tv_credits")
    void tvCredits(
            @Path("id") int tmdbId,
            Callback<PersonCredits> callback
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param tmdbId   TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/person/{id}/tv_credits")
    PersonCredits tvCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Async variant of {@link #tvCredits(int, String)}
     */
    @GET("/person/{id}/tv_credits")
    void tvCredits(
            @Path("id") int tmdbId,
            @Query("language") String language,
            Callback<PersonCredits> callback
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("/person/{id}/combined_credits")
    PersonCredits combinedCredits(
            @Path("id") int tmdbId
    );

    /**
     * Async variant of {@link #combinedCredits(int)}
     */
    @GET("/person/{id}/combined_credits")
    void combinedCredits(
            @Path("id") int tmdbId,
            Callback<PersonCredits> callback
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param tmdbId   TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/person/{id}/combined_credits")
    PersonCredits combinedCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Async variant of {@link #combinedCredits(int, String)}
     */
    @GET("/person/{id}/combined_credits")
    void combinedCredits(
            @Path("id") int tmdbId,
            @Query("language") String language,
            Callback<PersonCredits> callback
    );

}
