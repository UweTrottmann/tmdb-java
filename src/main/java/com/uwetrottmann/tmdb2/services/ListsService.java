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
import retrofit2.Call;
import retrofit2.http.*;

public interface ListsService {

    /**
     * Get the details of a list.
     *
     * @param listId A List TMDb id.(<b>String</b>/Integer).
     */
    @GET("list/{list_id}")
    Call<List> summary(
            @Path("list_id") String listId
    );

    /**
     * Get the details of a list.
     *
     * @param listId A List TMDb id.(String/<b>Integer</b>).
     */
    @GET("list/{list_id}")
    Call<List> summary(
            @Path("list_id") Integer listId
    );

    /**
     * Check if a movie is listed in a list.
     *
     * @param listId  A List TMDb id.(<b>String</b>/Integer).
     * @param movieId Movie Id.
     */
    @GET("list/{list_id}/item_status")
    Call<ListItemStatus> itemStatus(
            @Path("list_id") String listId,
            @Query("movie_id") Integer movieId);

    /**
     * Check if a movie is listed in a list.
     *
     * @param listId  A List TMDb id.(String/<b>Integer</b>).
     * @param movieId Movie Id.
     */
    @GET("list/{list_id}/item_status")
    Call<ListItemStatus> itemStatus(
            @Path("list_id") Integer listId,
            @Query("movie_id") Integer movieId);

    /**
     * Create a list.
     * <p>
     * <b>Requires an active Session.</b>
     */
    @POST("list")
    Call<ListCreateResponse> createList(
            @Body ListCreateRequest request
    );

    /**
     * Add movie to list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(<b>String</b>/Integer).
     * @param item   The Body to send. ({@link com.uwetrottmann.tmdb2.entities.ListOperation ListOperation})
     */
    @POST("list/{list_id}/add_item")
    Call<Status> addMovie(
            @Path("list_id") String listId,
            @Body ListOperation item
    );

    /**
     * Add movie to list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(String/<b>Integer</b>).
     * @param item   The Body to send. ({@link com.uwetrottmann.tmdb2.entities.ListOperation ListOperation})
     */
    @POST("list/{list_id}/add_item")
    Call<Status> addMovie(
            @Path("list_id") Integer listId,
            @Body ListOperation item
    );

    /**
     * Remove a movie from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(<b>String</b>/Integer).
     * @param item   The Body to send. ({@link com.uwetrottmann.tmdb2.entities.ListOperation ListOperation})
     */
    @POST("list/{list_id}/remove_item")
    Call<Status> removeMovie(
            @Path("list_id") String listId,
            @Body ListOperation item
    );

    /**
     * Remove a movie from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(String/<b>Integer</b>).
     * @param item   The Body to send. ({@link com.uwetrottmann.tmdb2.entities.ListOperation ListOperation})
     */
    @POST("list/{list_id}/remove_item")
    Call<Status> removeMovie(
            @Path("list_id") Integer listId,
            @Body ListOperation item
    );

    /**
     * Clear all of the items from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId  A List TMDb id.(<b>String</b>/Integer).
     * @param confirm Confirmation (Boolean).
     */
    @POST("list/{list_id}/clear")
    Call<Status> clear(
            @Path("list_id") String listId,
            @Query("confirm") Boolean confirm
    );

    /**
     * Clear all of the items from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId  A List TMDb id.(String/<b>Integer</b>).
     * @param confirm Confirmation (Boolean).
     */
    @POST("list/{list_id}/clear")
    Call<Status> clear(
            @Path("list_id") Integer listId,
            @Query("confirm") Boolean confirm
    );

    /**
     * Delete a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(<b>String</b>/Integer).
     */
    @DELETE("list/{list_id}")
    Call<Status> delete(
            @Path("list_id") String listId
    );


    /**
     * Delete a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A List TMDb id.(String/<b>Integer</b>).
     */
    @DELETE("list/{list_id}")
    Call<Status> delete(
            @Path("list_id") Integer listId
    );

}
