package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Jobs;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JobsService {

    /**
     * The the list of official jobs that are used on TMDb.
     */
    @GET("job/list")
    Call<Jobs> jobs();

}
