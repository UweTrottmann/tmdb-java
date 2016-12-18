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

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonCredits;
import com.uwetrottmann.tmdb2.entities.PersonIds;
import com.uwetrottmann.tmdb2.entities.PersonImages;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TaggedImagesResultsPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeopleService {

    /**
     * Get the general person information for a specific id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("person/{id}")
    Call<Person> summary(
            @Path("id") int tmdbId
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{id}/movie_credits")
    Call<PersonCredits> movieCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{id}/tv_credits")
    Call<PersonCredits> tvCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{id}/combined_credits")
    Call<PersonCredits> combinedCredits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the external ids for a specific person id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("person/{id}/external_ids")
    Call<PersonIds> externalIds(
            @Path("id") int tmdbId
    );

    /**
     * Get the images for a specific person id.
     */
    @GET("person/{id}/images")
    Call<PersonImages> images(
            @Path("id") int tmdbId
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param tmdbId TMDb id.
     * @param page <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("id") int tmdbId, @Query("page") Integer page, @Query("language") String language
    );

    /**
     * Get the list of popular people on The Movie Database. This list refreshes every day.
     */
    @GET("person/popular")
    Call<PersonResultsPage> popular(
            @Query("page") Integer page
    );

    /**
     * Get the latest person id.
     */
    @GET("person/latest")
    Call<Person> latest();

}
