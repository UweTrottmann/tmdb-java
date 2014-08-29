package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.api.Assertions.assertThat;

public class SearchServiceAsyncTest extends BaseTestCase {

    private CountDownLatch lock = new CountDownLatch(1);
    private MovieResultsPage movieResultsPageResponse;
    private TvResultsPage tvResultsPageResponse;

    public void test_movieSearch() throws Exception {
        getManager().searchServiceAsync().movie("Fight Club", new Callback<MovieResultsPage>() {
            @Override
            public void success(MovieResultsPage movieResultsPage, Response response) {
                movieResultsPageResponse = movieResultsPage;
                lock.countDown();

            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertNotNull("Result was null.", movieResultsPageResponse);
        assertNotNull("Returned movie list was null.", movieResultsPageResponse.results);
        assertNotNull("Returned page number was null.", movieResultsPageResponse.page);
        assertNotNull("Total pages number was null.", movieResultsPageResponse.total_pages);
        assertNotNull("Total results number was null.", movieResultsPageResponse.total_results);

    }

    public void test_movieSearchWithNullParams() throws Exception {

        getManager().searchServiceAsync().movie("Fight Club", null, null, null, null, null, null, new Callback<MovieResultsPage>() {
            @Override
            public void success(MovieResultsPage movieResultsPage, Response response) {
                movieResultsPageResponse = movieResultsPage;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertNotNull("Result was null.", movieResultsPageResponse);
        assertNotNull("Returned movie list was null.", movieResultsPageResponse.results);
        assertNotNull("Returned page number was null.", movieResultsPageResponse.page);
        assertNotNull("Total pages number was null.", movieResultsPageResponse.total_pages);
        assertNotNull("Total results number was null.", movieResultsPageResponse.total_results);

    }

    public void test_tv() throws Exception {
        getManager().searchServiceAsync().tv("Breaking Bad", null, null, null, null, new Callback<TvResultsPage>() {
            @Override
            public void success(TvResultsPage tvResultsPage, Response response) {
                tvResultsPageResponse = tvResultsPage;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertThat(tvResultsPageResponse.results).isNotEmpty();
        assertThat(tvResultsPageResponse.results.get(0).name).isEqualTo("Breaking Bad");

    }

    private void lockAwait () {
        try {
            lock.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}