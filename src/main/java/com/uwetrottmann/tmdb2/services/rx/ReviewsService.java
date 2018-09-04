package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.Review;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewsService {

    /**
     * Get a review by a Review Id.
     *
     * @param reviewId A TMDb Review id.
     */
    @GET("review/{review_id}")
    Observable<Review> getDetails(
            @Path("review_id") String reviewId
    );
}
