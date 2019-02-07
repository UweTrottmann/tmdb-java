package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;

public class GuestSessionTest extends BaseTestCase {

    public static Boolean guestDataInitialized;

    // TODO ut: split up and only run for tests that require it
    @BeforeClass
    public static void initializeGuestData() throws IOException {
        Tmdb tmdb = getAuthenticatedInstance();
        tmdb.guestSession();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 8D;

        tmdb.moviesService().addRating(testMovie.id, ratingObject).execute();

        tmdb.tvService().addRating(testTvShow.id, ratingObject).execute();

        tmdb.tvEpisodesService().addRating(testTvEpisode.id, testTvSeason.season_number,
                testTvEpisode.episode_number, ratingObject).execute();

        guestDataInitialized = true;
    }

    @AfterClass
    public static void rollbackGuestData() throws IOException {
        if (!guestDataInitialized) {
            return;
        }

        Tmdb tmdb = getAuthenticatedInstance();
        tmdb.moviesService().deleteRating(testMovie.id).execute();

        tmdb.tvService().deleteRating(testTvShow.id).execute();

        tmdb.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number).execute();
    }

    @Test
    public void test_rated_movies() throws IOException {
        assumeTrue(guestDataInitialized);

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
        assumeTrue(guestDataInitialized);

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
        assumeTrue(guestDataInitialized);

        Call<TvEpisodeResultsPage> call = getAuthenticatedInstance().guestSessionService().ratedTvEpisodes(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        // FIXME ut: should not ignore test due to service failure
//        try {
//            //There are rare cases which for some reason during the testing, because of the number of requests we are making to the service
//            //The service throws Internal Error.
//            //In any case that happens, we got that covered.

            TvEpisodeResultsPage tvEpisodeResultsPage = call.execute().body();

            assertThat(tvEpisodeResultsPage).isNotNull();
            assertThat(tvEpisodeResultsPage.page).isNotNull();
            assertThat(tvEpisodeResultsPage.total_pages).isNotNull();
            assertThat(tvEpisodeResultsPage.total_results).isNotNull();
            assertThat(tvEpisodeResultsPage.results).isNotNull();
//        } catch (TmdbServiceErrorException exc) {
//            assumeTrue(false);
//        }
    }

    @Test
    public void test_modify_rating_movie() throws IOException {
        assumeTrue(guestDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().moviesService().addRating(testMovie.id, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().moviesService().deleteRating(testMovie.id);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_modify_rating_tvEpisode() throws IOException {
        assumeTrue(guestDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().tvEpisodesService().addRating(testTvShow.id,
                testTvSeason.season_number, testTvEpisode.episode_number, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }


    @Test
    public void test_modify_rating_tvShow() throws IOException {
        assumeTrue(guestDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().tvService().addRating(testTvShow.id, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().tvService().deleteRating(testTvShow.id);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }

}
