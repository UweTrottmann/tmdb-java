package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.TmdbLocale;
import com.uwetrottmann.tmdb2.entities.FindResults;
import com.uwetrottmann.tmdb2.enumerations.ExternalSource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FindService {

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") String externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") String language
    );

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") String externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") TmdbLocale language
    );

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     */
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") String externalId,
            @Query("external_source") ExternalSource source
    );

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") int externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") String language
    );

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") int externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") TmdbLocale language
    );

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source     <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     */
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") int externalId,
            @Query("external_source") ExternalSource source
    );

}
