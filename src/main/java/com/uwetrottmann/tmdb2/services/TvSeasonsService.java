package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{id}/season/{season_number}")
    Call<TvSeason> season(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the cast and crew credits for a TV season by season number.
     *
     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/credits")
    Call<Credits> credits(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber
    );

    /**
     * Get the external ids that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/external_ids")
    Call<ExternalIds> externalIds(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

    /**
     * Get the images (posters) that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/images")
    Call<Images> images(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

    /**
     * Get the videos that have been added to a TV season (trailers, teasers, etc...)
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/videos")
    Call<Videos> videos(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

}
