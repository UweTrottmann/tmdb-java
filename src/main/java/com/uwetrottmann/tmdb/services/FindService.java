package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.FindResults;
import com.uwetrottmann.tmdb.enumerations.ExternalSource;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface FindService {

    /**
     * The find method makes it easy to search for objects in our database by an external id.
     * For instance, an IMDB ID. This will search all objects (movies, TV shows and people)
     * and return the results in a single response. TV season and TV episode searches
     * will be supported shortly.
     *
     * @param externalId
     * @param source
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @return
     */
    @GET("/find/{id}")
    FindResults find(
            @Path("id") String externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") String language
    );

}
