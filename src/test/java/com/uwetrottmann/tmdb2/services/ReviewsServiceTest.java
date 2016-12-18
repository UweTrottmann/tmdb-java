package com.uwetrottmann.tmdb2.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.Review;

import retrofit2.Call;

public class ReviewsServiceTest extends BaseTestCase {
	
	@Test
	public void test_getDetails() throws IOException{
		Call<Review> call = getManager().reviewsService().getDetails(TestData.REVIEW_ID);
        Review review = call.execute().body();
        assertThat(review.author).isEqualTo(TestData.REVIEW_AUTHOR);
        assertThat(review.iso_639_1).isEqualTo(TestData.REVIEW_ISO_639_1);
        assertThat(review.media_id).isEqualTo(TestData.REVIEW_MEDIA_ID);
        assertThat(review.media_title).isEqualTo(TestData.REVIEW_MEDIA_TITLE);
        assertThat(review.media_type).isEqualTo(TestData.REVIEW_MEDIA_TYPE);
        assertThat(review.url).isEqualTo(TestData.REVIEW_URL);
	}
}
