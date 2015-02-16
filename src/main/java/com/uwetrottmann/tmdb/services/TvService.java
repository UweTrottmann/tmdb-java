package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvService {

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/credits")
    Credits credits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/external_ids")
    ExternalIds externalIds(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

}
