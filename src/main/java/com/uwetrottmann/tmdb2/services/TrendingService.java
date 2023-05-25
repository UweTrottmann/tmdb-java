package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.TrendingResultsPage;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.TimeWindow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrendingService {

    /**
     * Get the trending movies, TV shows and people.
     *
     * @see <a href="https://developers.themoviedb.org/3/trending/get-trending">Documentation</a>
     */
    @GET("trending/all/{time_window}")
    Call<TrendingResultsPage> trendingAll(
            @Path("time_window") TimeWindow timeWindow
    );

    /**
     * Get the trending movies, TV shows or people.
     *
     * @see <a href="https://developers.themoviedb.org/3/trending/get-trending">Documentation</a>
     */
    @GET("trending/{media_type}/{time_window}")
    Call<TrendingResultsPage> trending(
            @Path("media_type") MediaType mediaType,
            @Path("time_window") TimeWindow timeWindow
    );
}