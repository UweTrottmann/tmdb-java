package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Review;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewsService {
	
	/**
     * The review method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("review/{review_id}")
    Call<Review> getDetails(
            @Path("review_id") String externalId
    );
}
