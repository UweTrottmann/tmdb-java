package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.TrendingResultsPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrendingService {

    /**
     * Get the daily or weekly trending items
     *
     * @see <a href="https://developers.themoviedb.org/3/trending/get-trending">Documentation</a>
     */
    @GET("trending/{media_type}/{time_window}")
    Call<TrendingResultsPage> trending(
        @Path("media_type") String mediaType,
        @Path("time_window") String timeWindow
    );
}