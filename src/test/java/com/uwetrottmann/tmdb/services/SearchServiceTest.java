package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.BaseResultsPage;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    public void test_movieSearch() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie(TestData.MOVIE_TITLE);
        assertMovieResults(movieResults);
    }

    public void test_movieSearchWithNullParams() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie(TestData.MOVIE_TITLE, null, null,
                null, null, null, null);
        assertMovieResults(movieResults);
    }

    private void assertMovieResults(MovieResultsPage results) {
        assertThat(results.results).isNotEmpty();
        assertResultsPage(results);
    }

    public void test_tv() {
        TvResultsPage tvResults = getManager().searchService().tv(TestData.TVSHOW_TITLE, null, null, null, null);
        assertTvResults(tvResults);
    }

    private void assertTvResults(TvResultsPage tvResults) {
        assertThat(tvResults.results).isNotEmpty();
        assertThat(tvResults.results.get(0).name).isEqualTo(TestData.TVSHOW_TITLE);
        assertResultsPage(tvResults);
    }

    private void assertResultsPage(BaseResultsPage results) {
        assertThat(results.page).isPositive();
        assertThat(results.total_pages).isPositive();
        assertThat(results.total_results).isPositive();
    }

}
