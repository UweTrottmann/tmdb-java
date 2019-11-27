package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Network;
import com.uwetrottmann.tmdb2.entities.NetworkImages;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworksService {

    /**
     * Get the details of a network.
     *
     * @param id The Network TMDb id.
     */
    @GET("network/{id}")
    Call<Network> summary(
            @Path("id") Integer id
    );

    /**
     * Get the logos of a network.
     *
     * @param id The Network TMDb id.
     */
    @GET("network/{id}/images")
    Call<NetworkImages> images(
            @Path("id") Integer id
    );

}
