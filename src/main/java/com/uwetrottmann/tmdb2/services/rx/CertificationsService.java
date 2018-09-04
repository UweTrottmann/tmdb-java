package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.Certifications;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface CertificationsService {
    /**
     * Get the Certifications for Movies.
     */
    @GET("certification/movie/list")
    Observable<Certifications> movie();

    /**
     * Get the Certifications for TV Shows.
     */
    @GET("certification/tv/list")
    Observable<Certifications> tv();
}
