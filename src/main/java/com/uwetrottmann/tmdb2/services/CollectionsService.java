package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.utils.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface CollectionsService {
    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @Deprecated
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
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic collection information for a specific collection id.
     *
     * @param collectionId     TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("collection/{collection_id}")
    Call<Collection> summary(
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
    Call<Collection> summary(
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
    @Deprecated
    @GET("collection/{collection_id}")
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") String language,
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
    Call<Collection> summary(
            @Path("collection_id") int collectionId,
            @Query("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the images (posters and backdrops) for a specific collection id.
     *
     * @param collectionId TMDb id.
     * @param language     <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("collection/{collection_id}/images")
    Call<Images> images(
            @Path("collection_id") int collectionId,
            @Query("language") String language
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
            @Query("language") TmdbLocale language
    );

    /**
     * Get the images (posters and backdrops) for a specific collection id.
     *
     * @param collectionId TMDb id.
     */
    @GET("collection/{collection_id}/images")
    Call<Images> images(
            @Path("collection_id") int collectionId
    );
}
