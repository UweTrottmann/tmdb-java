package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.Timezones;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TimezonesService {

    /**
     * Get the list of supported timezones on TMDb.
     */
    @GET("timezones/list")
    Observable<Timezones> timezones();
}
