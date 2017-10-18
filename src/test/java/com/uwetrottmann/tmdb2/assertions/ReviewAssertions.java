package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.Review;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;

import static com.uwetrottmann.tmdb2.TestData.testReview;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewAssertions {
    public static void assertReview(Review review, Boolean extensive) {
        assertThat(review.author).isNotNull();
        assertThat(review.content).isNotNull();
        assertThat(review.id).isNotNull();
        assertThat(review.url).isNotNull();
        if (!extensive)
            return;

        assertThat(review.language).isNotNull();
        assertThat(review.media_id).isNotNull();
        assertThat(review.media_title).isNotNull();
        assertThat(review.media_type).isNotNull();
    }

    public static void assertReviewDataIntegrity(Review review) {
        assertReview(review, true);

        assertThat(review.media_type).isEqualTo(testReview.media_type);
        assertThat(review.media_title).isEqualTo(testReview.media_title);
        assertThat(review.media_id).isEqualTo(testReview.media_id);
        assertThat(review.language.getLanguageCode()).isEqualTo(testReview.language.getLanguageCode());
        assertThat(review.url).isEqualTo(testReview.url);
        assertThat(review.id).isEqualTo(testReview.id);
        assertThat(review.content).isEqualTo(testReview.content);
        assertThat(review.author).isEqualTo(testReview.author);

    }

    public static void assertReviews(ReviewResultsPage reviewResultsPage) {
        assertBaseResultsPage(reviewResultsPage);
        assertThat(reviewResultsPage.results).isNotNull();
        assertThat(reviewResultsPage.results).isNotEmpty();
        for (Review review : reviewResultsPage.results) {
            assertReview(review, false);
        }
    }
}
