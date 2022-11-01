package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.assertions.TrendingAssertions.assertTrendingResultsPage;

import java.io.IOException;

import org.junit.Test;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.TrendingResultsPage;

import retrofit2.Call;

public class TrendingServiceTest extends BaseTestCase {

    @Test
    public void test_trending() throws IOException {
        Call<TrendingResultsPage> call = getUnauthenticatedInstance().trendingService().trending("all", "week");

		TrendingResultsPage trendingResultsPage = call.execute().body();

        assertTrendingResultsPage(trendingResultsPage);
    }
}
