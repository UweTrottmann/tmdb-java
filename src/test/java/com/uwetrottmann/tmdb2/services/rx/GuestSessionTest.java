package com.uwetrottmann.tmdb2.services.rx;

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
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GuestSessionTest extends BaseTestCase {

    public static Boolean guestDataInitialized;

    // TODO ut: split up and only run for tests that require it
    @BeforeClass
    public static void initializeGuestData() throws IOException {
        Tmdb tmdb = getAuthenticatedInstance();
        tmdb.rx.guestSessionService();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 8D;

        tmdb.rx.moviesService().addRating(testMovie.id, AuthenticationType.GUEST, ratingObject).singleOrError().blockingGet();

        tmdb.rx.tvService().addRating(testTvShow.id, AuthenticationType.GUEST, ratingObject).singleOrError().blockingGet();

        tmdb.rx.tvEpisodesService().addRating(testTvEpisode.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.GUEST, ratingObject).singleOrError().blockingGet();

        guestDataInitialized = true;
    }

    @AfterClass
    public static void rollbackGuestData() throws IOException {
        if (!guestDataInitialized) {
            return;
        }

        Tmdb tmdb = getAuthenticatedInstance();
        tmdb.rx.moviesService().deleteRating(testMovie.id, AuthenticationType.GUEST).singleOrError().blockingGet();

        tmdb.rx.tvService().deleteRating(testTvShow.id, AuthenticationType.GUEST).singleOrError().blockingGet();

        tmdb.rx.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.GUEST).singleOrError().blockingGet();
    }

    @Test
    public void test_rated_movies() throws IOException {
        assumeTrue(guestDataInitialized);

        Observable<MovieResultsPage> call = getAuthenticatedInstance().rx.guestSessionService().ratedMovies(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        MovieResultsPage movieResultsPage = call.singleOrError().blockingGet();

        assertThat(movieResultsPage).isNotNull();
        assertThat(movieResultsPage.page).isNotNull();
        assertThat(movieResultsPage.total_pages).isNotNull();
        assertThat(movieResultsPage.total_results).isNotNull();
        assertThat(movieResultsPage.results).isNotNull();
    }

    @Test
    public void test_rated_tvShows() throws IOException {
        assumeTrue(guestDataInitialized);

        Observable<TvShowResultsPage> call = getAuthenticatedInstance().rx.guestSessionService().ratedTvShows(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        TvShowResultsPage tvShowResultsPage = call.singleOrError().blockingGet();

        assertThat(tvShowResultsPage).isNotNull();
        assertThat(tvShowResultsPage.page).isNotNull();
        assertThat(tvShowResultsPage.total_pages).isNotNull();
        assertThat(tvShowResultsPage.total_results).isNotNull();
        assertThat(tvShowResultsPage.results).isNotNull();
    }

    @Test
    public void test_rated_tvEpisodes() throws IOException {
        assumeTrue(guestDataInitialized);

        Observable<TvEpisodeResultsPage> call = getAuthenticatedInstance().rx.guestSessionService().ratedTvEpisodes(
                getAuthenticatedInstance().getGuestSessionId(),
                null,
                null
        );

        // FIXME ut: should not ignore test due to service failure
//        try {
//            //There are rare cases which for some reason during the testing, because of the number of requests we are making to the service
//            //The service throws Internal Error.
//            //In any case that happens, we got that covered.

            TvEpisodeResultsPage tvEpisodeResultsPage = call.singleOrError().blockingGet();

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

        Observable<Status> call = getAuthenticatedInstance().rx.moviesService().addRating(testMovie.id, AuthenticationType.GUEST,
                obj);
        Status status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().rx.moviesService().deleteRating(testMovie.id, AuthenticationType.GUEST);
        status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_modify_rating_tvEpisode() throws IOException {
        assumeTrue(guestDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Observable<Status> call = getAuthenticatedInstance().rx.tvEpisodesService().addRating(testTvShow.id,
                testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST, obj);
        Status status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().rx.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.GUEST);
        status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isEqualTo(13);
    }


    @Test
    public void test_modify_rating_tvShow() throws IOException {
        assumeTrue(guestDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Observable<Status> call = getAuthenticatedInstance().rx.tvService().addRating(testTvShow.id, AuthenticationType.GUEST,
                obj);
        Status status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().rx.tvService().deleteRating(testTvShow.id, AuthenticationType.GUEST);
        status = call.singleOrError().blockingGet();
        assertThat(status.status_code).isEqualTo(13);
    }

}
