package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Timezones;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.HashMap;
import java.util.List;

public interface TimezonesService {

    /**
     * Get the list of supported timezones on TMDb.
     */
    @GET("timezones/list")
    Call<Timezones> timezones();
}
