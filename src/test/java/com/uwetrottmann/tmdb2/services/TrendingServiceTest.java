package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.assertions.TrendingAssertions.assertTrendingResultsPage;

import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.TimeWindow;
import java.io.IOException;

import org.junit.Test;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.TrendingResultsPage;

import retrofit2.Call;

public class TrendingServiceTest extends BaseTestCase {

    @Test
    public void test_trending_all() throws IOException {
        Call<TrendingResultsPage> call = getUnauthenticatedInstance().trendingService().trendingAll(TimeWindow.WEEK);
        TrendingResultsPage trendingResultsPage = call.execute().body();
        assertTrendingResultsPage(trendingResultsPage);
    }

    @Test
    public void test_trending_tv() throws IOException {
        Call<TrendingResultsPage> call =
                getUnauthenticatedInstance().trendingService().trending(MediaType.TV, TimeWindow.DAY);
        TrendingResultsPage trendingResultsPage = call.execute().body();
        assertTrendingResultsPage(trendingResultsPage);
    }
}
