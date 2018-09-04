package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CollectionsService {
    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     */
    @GET("collection/{collection_id}")
    Observable<Collection> summary(
            @Path("collection_id") int collectionId
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @GET("collection/{collection_id}")
    Observable<Collection> summary(
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
    Observable<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("collection/{collection_id}")
    Observable<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("collection/{collection_id}")
    Observable<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
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
    Observable<Collection> summary(
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
    Observable<Images> images(
            @Path("collection_id") int collectionId,
            @Query("language") String language
    );
}
