package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListCreateRequest;
import com.uwetrottmann.tmdb2.entities.ListCreateResponse;
import com.uwetrottmann.tmdb2.entities.ListItemStatus;
import com.uwetrottmann.tmdb2.entities.ListOperation;
import com.uwetrottmann.tmdb2.entities.Status;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListsService {

    /**
     * Get the details of a list.
     *
     * @param listId A BaseList TMDb id.(<b>String</b>/Integer).
     */
    @GET("list/{list_id}")
    Observable<List> summary(
            @Path("list_id") String listId
    );

    /**
     * Get the details of a list.
     *
     * @param listId A BaseList TMDb id.(String/<b>Integer</b>).
     */
    @GET("list/{list_id}")
    Observable<List> summary(
            @Path("list_id") Integer listId
    );

    /**
     * Check if a movie is listed in a list.
     *
     * @param listId  A BaseList TMDb id.(<b>String</b>/Integer).
     * @param movieId Movie Id.
     */
    @GET("list/{list_id}/item_status")
    Observable<ListItemStatus> itemStatus(
            @Path("list_id") String listId,
            @Query("movie_id") Integer movieId);

    /**
     * Check if a movie is listed in a list.
     *
     * @param listId  A BaseList TMDb id.(String/<b>Integer</b>).
     * @param movieId Movie Id.
     */
    @GET("list/{list_id}/item_status")
    Observable<ListItemStatus> itemStatus(
            @Path("list_id") Integer listId,
            @Query("movie_id") Integer movieId);

    /**
     * Create a list.
     * <p>
     * <b>Requires an active Session.</b>
     */
    @POST("list")
    Observable<ListCreateResponse> createList(
            @Body ListCreateRequest request
    );

    /**
     * Add movie to list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(<b>String</b>/Integer).
     * @param item   The Body to send. ({@link ListOperation ListOperation})
     */
    @POST("list/{list_id}/add_item")
    Observable<Status> addMovie(
            @Path("list_id") String listId,
            @Body ListOperation item
    );

    /**
     * Add movie to list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(String/<b>Integer</b>).
     * @param item   The Body to send. ({@link ListOperation ListOperation})
     */
    @POST("list/{list_id}/add_item")
    Observable<Status> addMovie(
            @Path("list_id") Integer listId,
            @Body ListOperation item
    );

    /**
     * Remove a movie from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(<b>String</b>/Integer).
     * @param item   The Body to send. ({@link ListOperation ListOperation})
     */
    @POST("list/{list_id}/remove_item")
    Observable<Status> removeMovie(
            @Path("list_id") String listId,
            @Body ListOperation item
    );

    /**
     * Remove a movie from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(String/<b>Integer</b>).
     * @param item   The Body to send. ({@link ListOperation ListOperation})
     */
    @POST("list/{list_id}/remove_item")
    Observable<Status> removeMovie(
            @Path("list_id") Integer listId,
            @Body ListOperation item
    );

    /**
     * Clear all of the items from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId  A BaseList TMDb id.(<b>String</b>/Integer).
     * @param confirm Confirmation (Boolean).
     */
    @POST("list/{list_id}/clear")
    Observable<Status> clear(
            @Path("list_id") String listId,
            @Query("confirm") Boolean confirm
    );

    /**
     * Clear all of the items from a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId  A BaseList TMDb id.(String/<b>Integer</b>).
     * @param confirm Confirmation (Boolean).
     */
    @POST("list/{list_id}/clear")
    Observable<Status> clear(
            @Path("list_id") Integer listId,
            @Query("confirm") Boolean confirm
    );

    /**
     * Delete a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(<b>String</b>/Integer).
     */
    @DELETE("list/{list_id}")
    Observable<Status> delete(
            @Path("list_id") String listId
    );


    /**
     * Delete a list.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param listId A BaseList TMDb id.(String/<b>Integer</b>).
     */
    @DELETE("list/{list_id}")
    Observable<Status> delete(
            @Path("list_id") Integer listId
    );

}
