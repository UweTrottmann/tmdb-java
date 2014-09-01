package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.BaseResultsPage;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.text.ParseException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    private CountDownLatch lock = new CountDownLatch(1);
    private MovieResultsPage movieResults;
    private TvResultsPage tvResults;

    public void test_movieSearch() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie(TestData.MOVIE_TITLE);
        assertMovieResults(movieResults);
    }

    public void test_movieSearch_async() throws Exception {
        getManager().searchService().movie(TestData.MOVIE_TITLE, new Callback<MovieResultsPage>() {
            @Override
            public void success(MovieResultsPage movieResultsPage, Response response) {
                SearchServiceTest.this.movieResults = movieResultsPage;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();

        assertMovieResults(movieResults);
    }

    public void test_movieSearchWithNullParams() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie(TestData.MOVIE_TITLE, null, null,
                null, null, null, null);
        assertMovieResults(movieResults);
    }

    public void test_movieSearchWithNullParams_async() throws Exception {
        getManager().searchService().movie(TestData.MOVIE_TITLE, null, null, null, null, null, null,
                new Callback<MovieResultsPage>() {
                    @Override
                    public void success(MovieResultsPage movieResultsPage, Response response) {
                        SearchServiceTest.this.movieResults = movieResultsPage;
                        lock.countDown();
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {

                    }
                });

        lockAwait();

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

    public void test_tv_async() throws Exception {
        getManager().searchService().tv(TestData.TVSHOW_TITLE, null, null, null, null, new Callback<TvResultsPage>() {
            @Override
            public void success(TvResultsPage tvResultsPage, Response response) {
                SearchServiceTest.this.tvResults = tvResultsPage;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();

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

    private void lockAwait() {
        try {
            lock.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
