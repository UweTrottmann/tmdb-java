// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;

public class GuestSessionTest extends BaseTestCase {

    @Before
    public void turnOnGuestSession() {
        getAuthenticatedInstance().guestSession();
    }

    @After
    public void cleanUpGuestSession() {
        getAuthenticatedInstance().clearSessions();
    }

    @Test
    public void test_modify_rating_movie() throws IOException, InterruptedException {
        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().moviesService().addRating(testMovie.id, obj);
        Status status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isIn(1, 12);

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        MovieResultsPage movieResultsPage = getAuthenticatedInstance().guestSessionService().ratedMovies(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        ).execute().body();
        assertThat(movieResultsPage).isNotNull();
        assertThat(movieResultsPage.page).isNotNull();
        assertThat(movieResultsPage.total_pages).isNotNull();
        assertThat(movieResultsPage.total_results).isNotNull();
        assertThat(movieResultsPage.results).isNotNull();

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        call = getAuthenticatedInstance().moviesService().deleteRating(testMovie.id);
        status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_modify_rating_tvEpisode() throws IOException, InterruptedException {
        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().tvEpisodesService().addRating(testTvShow.id,
                testTvSeason.season_number, testTvEpisode.episode_number, obj);
        Status status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isIn(1, 12);

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        TvEpisodeResultsPage tvEpisodeResultsPage = getAuthenticatedInstance().guestSessionService().ratedTvEpisodes(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        ).execute().body();

        assertThat(tvEpisodeResultsPage).isNotNull();
        assertThat(tvEpisodeResultsPage.page).isNotNull();
        assertThat(tvEpisodeResultsPage.total_pages).isNotNull();
        assertThat(tvEpisodeResultsPage.total_results).isNotNull();
        assertThat(tvEpisodeResultsPage.results).isNotNull();

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        call = getAuthenticatedInstance().tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number);
        status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(13);
    }


    @Test
    public void test_modify_rating_tvShow() throws IOException, InterruptedException {
        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().tvService().addRating(testTvShow.id, obj);
        Status status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isIn(1, 12);

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        TvShowResultsPage tvShowResultsPage = getAuthenticatedInstance().guestSessionService().ratedTvShows(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        ).execute().body();

        assertThat(tvShowResultsPage).isNotNull();
        assertThat(tvShowResultsPage.page).isNotNull();
        assertThat(tvShowResultsPage.total_pages).isNotNull();
        assertThat(tvShowResultsPage.total_results).isNotNull();
        assertThat(tvShowResultsPage.results).isNotNull();

        // Wait until the next request to let the servers catch up
        Thread.sleep(1000);

        call = getAuthenticatedInstance().tvService().deleteRating(testTvShow.id);
        status = call.execute().body();
        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(13);
    }

}
