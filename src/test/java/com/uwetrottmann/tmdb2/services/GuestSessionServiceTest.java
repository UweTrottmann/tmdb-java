package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.exceptions.TmdbServiceErrorException;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

public class GuestSessionServiceTest extends BaseTestCase {

    @Test
    public void test_rated_movies() throws IOException {
        Call<MovieResultsPage> call = getAuthenticatedInstance().guestSessionService().ratedMovies(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        MovieResultsPage movieResultsPage = call.execute().body();

        assertThat(movieResultsPage).isNotNull();
        assertThat(movieResultsPage.page).isNotNull();
        assertThat(movieResultsPage.total_pages).isNotNull();
        assertThat(movieResultsPage.total_results).isNotNull();
        assertThat(movieResultsPage.results).isNotNull();
    }

    @Test
    public void test_rated_tvShows() throws IOException {
        Call<TvShowResultsPage> call = getAuthenticatedInstance().guestSessionService().ratedTvShows(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        TvShowResultsPage tvShowResultsPage = call.execute().body();

        assertThat(tvShowResultsPage).isNotNull();
        assertThat(tvShowResultsPage.page).isNotNull();
        assertThat(tvShowResultsPage.total_pages).isNotNull();
        assertThat(tvShowResultsPage.total_results).isNotNull();
        assertThat(tvShowResultsPage.results).isNotNull();
    }

    @Test
    public void test_rated_tvEpisodes() throws IOException {
        Call<TvEpisodeResultsPage> call = getAuthenticatedInstance().guestSessionService().ratedTvEpisodes(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        try {
            //There are rare cases which for some reason during the testing, because of the number of requests we are making to the service
            //The service throws Internal Error.
            //In any case that happens, we got that covered.

            TvEpisodeResultsPage tvEpisodeResultsPage = call.execute().body();

            assertThat(tvEpisodeResultsPage).isNotNull();
            assertThat(tvEpisodeResultsPage.page).isNotNull();
            assertThat(tvEpisodeResultsPage.total_pages).isNotNull();
            assertThat(tvEpisodeResultsPage.total_results).isNotNull();
            assertThat(tvEpisodeResultsPage.results).isNotNull();
        } catch (TmdbServiceErrorException exc) {
            assumeTrue(false);
        }
    }

}
