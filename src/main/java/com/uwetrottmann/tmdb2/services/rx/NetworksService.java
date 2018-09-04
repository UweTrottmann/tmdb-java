package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.Network;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworksService {

    /**
     * Get the details of a network.
     *
     * @param id The Network TMDb id.
     */
    @GET("network/{id}")
    Observable<Network> summary(
            @Path("id") Integer id
    );

}
