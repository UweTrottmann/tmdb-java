// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Review;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testReview;
import static com.uwetrottmann.tmdb2.assertions.ReviewAssertions.assertReviewDataIntegrity;

public class ReviewsServiceTest extends BaseTestCase {

    @Test
    public void test_getDetails() throws IOException {
        Call<Review> call = getUnauthenticatedInstance().reviewsService().getDetails(
                testReview.id
        );

        Review review = call.execute().body();

        assertReviewDataIntegrity(review);
    }
}
