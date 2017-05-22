/*
 * Copyright 2015 Manuel Laggner
 *
 * Modifications Copyright 2017 Nikolas Mavropoylos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface CollectionService {
    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the images (posters and backdrops) for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @GET("collection/{collection_id}/images")
    Call<Images> images(
            @Path("collection_id") int collectionId,
            @Query("language") String language
    );
}
