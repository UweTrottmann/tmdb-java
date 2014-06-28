package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.Credits;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvService {

    /**
     * Get the cast & crew information about a TV series. Just like the website,
     * we pull this information from the last season of the series.
     *
     * @param tmdbId
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @return
     */
    @GET("/tv/{id}/credits")
    Credits credits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

}
