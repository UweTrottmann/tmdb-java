package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Keyword;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface KeywordsService {
    /**
     * Get keyword by id.
     *
     * @param keywordId A BaseKeyword TMDb id.
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId
    );

    /**
     * Get keyword by id.
     *
     * @param keywordId        A BaseKeyword TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get keyword by id.
     *
     * @param keywordId        A BaseKeyword TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("keyword/{keyword_id}")
    Call<Keyword> summary(
            @Path("keyword_id") Integer keywordId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the movies that belong to a keyword.
     *
     * Is highly recommend using {@link DiscoverService#discoverMovie discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param keywordId A BaseKeyword TMDb id.
     */
    @GET("keyword/{keyword_id}/movies")
    Call<MovieResultsPage> movies(
            @Path("keyword_id") Integer keywordId
    );
}
