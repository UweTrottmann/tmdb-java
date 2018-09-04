package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testReview;
import static com.uwetrottmann.tmdb2.assertions.ReviewAssertions.assertReviewDataIntegrity;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Review;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class ReviewsServiceTest extends BaseTestCase {

    @Test
    public void test_getDetails() throws IOException {
        Observable<Review> call = getUnauthenticatedInstance().rx.reviewsService().getDetails(
                testReview.id
        );

        Review review = call.singleOrError().blockingGet();

        assertReviewDataIntegrity(review);
    }
}
