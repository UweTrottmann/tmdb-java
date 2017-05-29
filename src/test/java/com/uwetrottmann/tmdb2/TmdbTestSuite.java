package com.uwetrottmann.tmdb2;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import com.uwetrottmann.tmdb2.entities.Account;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.FavoriteMedia;
import com.uwetrottmann.tmdb2.entities.ListCreateRequest;
import com.uwetrottmann.tmdb2.entities.ListCreateResponse;
import com.uwetrottmann.tmdb2.entities.ListOperation;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.WatchlistMedia;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import org.assertj.core.util.Strings;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static com.uwetrottmann.tmdb2.TestData.initializeTestingIntegrityData;
import static com.uwetrottmann.tmdb2.TestData.testList;
import static com.uwetrottmann.tmdb2.TestData.testListMovies;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*Test.class"})
public class TmdbTestSuite {

    private static String API_KEY;
    public static String USERNAME ;
    public static String PASSWORD;
    static final boolean DEBUG = true;

    static Tmdb unauthenticatedInstance;
    static Tmdb authenticatedInstance;

    static Boolean initialized = false;

    public static Boolean suiteRunning = false;
    @ClassRule
    public static ExternalResource triggerSuiteRunning = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            suiteRunning = true;
        }
    };

    @BeforeClass
    public static void initializeEnvironment() throws IOException, ParseException {
        if (initialized)
            return;

        initialized = true;

        if (Strings.isNullOrEmpty(USERNAME) || Strings.isNullOrEmpty(PASSWORD)) {
            USERNAME = System.getenv("TMDB_USERNAME");
            PASSWORD = System.getenv("TMDB_PASSWORD");
        }
        if (Strings.isNullOrEmpty(API_KEY)) {
            API_KEY = System.getenv("TMDB_APIKEY");
        }
        if (API_KEY == null) {
            System.err.println("Your Environment is not Set for Testing. Please Define the proper Environment variables and try again.");
            System.exit(0);
        }

        unauthenticatedInstance = new BaseTestCase.TestTmdb(API_KEY);
        authenticatedInstance = new BaseTestCase.TestTmdb(API_KEY);

        initializeTestingIntegrityData();
        initializeAccountData();
        initializeGuestData();
    }

    public static void initializeGuestData() throws IOException {
        authenticatedInstance.guestSession();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 8D;

        authenticatedInstance.moviesService().addRating(testMovie.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvService().addRating(testTvShow.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvEpisodesService().addRating(testTvEpisode.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST, ratingObject).execute();
    }

    public static void initializeAccountData() throws IOException {
        if (USERNAME == null || PASSWORD == null)
            return;

        System.out.println("Found TMDB Credentials. Authenticating Instance...");

        authenticatedInstance.accountSession(USERNAME, PASSWORD);

        Account account = authenticatedInstance.accountService().summary().execute().body();

        ListCreateRequest request = new ListCreateRequest();
        request.name = "TestList";
        request.description = "List for Testing Purposes";
        request.language = "en";


        ListCreateResponse response = authenticatedInstance.listsService().createList(request).execute().body();
        testList.id = response.list_id;
        testList.name = request.name;
        testList.created_by = account.username;
        testList.description = request.description;
        testList.items = new ArrayList<>();

        ListOperation listOperation = new ListOperation();
        FavoriteMedia favoriteMedia = new FavoriteMedia();
        favoriteMedia.media_type = MediaType.MOVIE;
        favoriteMedia.favorite = true;
        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.media_type = MediaType.MOVIE;
        watchlistMedia.watchlist = true;

        for (Integer i : testListMovies) {
            BaseMovie movie = new BaseMovie();
            movie.id = i;
            testList.items.add(movie);

            listOperation.media_id = i;
            favoriteMedia.media_id = i;
            watchlistMedia.media_id = i;

            authenticatedInstance.listsService().addMovie(testList.id, listOperation).execute();
            authenticatedInstance.accountService().favorite(0, favoriteMedia).execute();
            authenticatedInstance.accountService().watchlist(0, watchlistMedia).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        authenticatedInstance.accountService().favorite(0, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        authenticatedInstance.accountService().watchlist(0, watchlistMedia).execute();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 7.5;

        authenticatedInstance.moviesService().addRating(testMovie.id, AuthenticationType.ACCOUNT, ratingObject).execute();

        authenticatedInstance.tvService().addRating(testTvShow.id, AuthenticationType.ACCOUNT, ratingObject).execute();

        authenticatedInstance.tvEpisodesService().addRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT, ratingObject).execute();

    }

    @AfterClass
    public static void destructEnvironment() throws IOException {

        System.out.println("Test Classes to run have been finished. Cleaning up...");
        rollbackAccountData();
        rollbackGuestData();
    }


    public static void rollbackGuestData() throws IOException {
        authenticatedInstance.moviesService().deleteRating(testMovie.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvService().deleteRating(testTvShow.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST).execute();
    }


    public static void rollbackAccountData() throws IOException {
        if (USERNAME == null || PASSWORD == null)
            return;

        FavoriteMedia favoriteMedia = new FavoriteMedia();
        favoriteMedia.media_type = MediaType.MOVIE;
        favoriteMedia.favorite = false;
        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.media_type = MediaType.MOVIE;
        watchlistMedia.watchlist = false;
        for (Integer i : testListMovies) {

            favoriteMedia.media_id = i;
            watchlistMedia.media_id = i;

            authenticatedInstance.accountService().favorite(0, favoriteMedia).execute();
            authenticatedInstance.accountService().watchlist(0, watchlistMedia).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        authenticatedInstance.accountService().favorite(0, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        authenticatedInstance.accountService().watchlist(0, watchlistMedia).execute();

        authenticatedInstance.moviesService().deleteRating(testMovie.id, AuthenticationType.ACCOUNT).execute();

        authenticatedInstance.tvService().deleteRating(testTvShow.id, AuthenticationType.ACCOUNT).execute();

        authenticatedInstance.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT).execute();

        try {
            authenticatedInstance.listsService().delete(testList.id).execute();
        } catch (Exception exc) {

        }
    }
}
