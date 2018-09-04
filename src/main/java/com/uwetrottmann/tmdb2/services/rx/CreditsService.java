package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.Credit;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CreditsService {
    /**
     * Get the basic company information for a specific company id.
     *
     * @param credit_id The Credit ID provided by a Movie/TV Show about an Actor or Crew Member.
     */
    @GET("credit/{id}")
    Observable<Credit> credit(
            @Path("id") String credit_id
    );
}
