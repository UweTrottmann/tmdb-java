package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Review;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewsService {
	
    @GET("review/{review_id}")
    Call<Review> getDetails(
            @Path("review_id") String externalId
    );
}
