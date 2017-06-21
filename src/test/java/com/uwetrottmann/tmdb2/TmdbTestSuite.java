package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import com.uwetrottmann.tmdb2.services.AccountSessionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;

@RunWith(Suite.class)
@SuiteClasses({AccountSessionTest.class})
public class TmdbTestSuite {

    static Tmdb authenticatedInstance;

    public static Boolean guestDataInitialized = false;

    public static void initializeGuestData() throws IOException {
        authenticatedInstance.guestSession();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 8D;

        authenticatedInstance.moviesService().addRating(testMovie.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvService().addRating(testTvShow.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvEpisodesService().addRating(testTvEpisode.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.GUEST, ratingObject).execute();

        guestDataInitialized = true;
    }

    public static void rollbackGuestData() throws IOException {
        if (!guestDataInitialized) {
            return;
        }

        authenticatedInstance.moviesService().deleteRating(testMovie.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvService().deleteRating(testTvShow.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.GUEST).execute();
    }

}
