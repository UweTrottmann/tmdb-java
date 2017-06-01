package com.uwetrottmann.tmdb2;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import com.uwetrottmann.tmdb2.annotations.RequiresAccountSession;
import com.uwetrottmann.tmdb2.annotations.RequiresGuestSession;
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
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import static com.uwetrottmann.tmdb2.BaseTestCase.testClassToRun;
import static com.uwetrottmann.tmdb2.TestData.initializeTestingIntegrityData;
import static com.uwetrottmann.tmdb2.TestData.testList;
import static com.uwetrottmann.tmdb2.TestData.testListMovies;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*.class", "!TmdbTestSuite.class", "!BaseTestCase.class"})
public class TmdbTestSuite {

    private static String API_KEY;
    private static String USERNAME;
    private static String PASSWORD;
    static final boolean DEBUG = true;

    static Tmdb unauthenticatedInstance;
    static Tmdb authenticatedInstance;


    public static Boolean initialized = false;
    public static Boolean accountDataInitialized = false;
    public static Boolean guestDataInitialized = false;

    public static Boolean suiteRunning = false;

    private static Account account;

    @ClassRule
    public static ExternalResource triggerSuiteRunning = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            suiteRunning = true;
        }
    };

    @BeforeClass
    public static void initializeEnvironment() throws IOException, ParseException, InterruptedException {
        if (initialized) {
            return;
        }

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

        initializeData();

        initialized = true;
    }

    public static void initializeData() throws ParseException, IOException, InterruptedException {
        initializeTestingIntegrityData();

        try {
            Field field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);

            @SuppressWarnings({"unchecked", "rawtypes"})
            Vector<Class> classes = (Vector<Class>) field.get(BaseTestCase.class.getClassLoader());
            Boolean classCounted;
            for (Class<?> clazz : new Vector<>(classes)) {

                if (clazz.getName().startsWith("com.uwetrottmann.tmdb2") && !clazz.getSimpleName().equals("BaseTestCase") && !clazz.getSimpleName().equals("TmdbTestSuite")) {
                    classCounted = false;

                    if (clazz.isAnnotationPresent(RequiresAccountSession.class) && !accountDataInitialized) {
                        initializeAccountData();
                    }
                    if (clazz.isAnnotationPresent(RequiresGuestSession.class) && !guestDataInitialized) {
                        initializeGuestData();
                    }

                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(Test.class)) {
                            if (!classCounted) {
                                classCounted = true;
                                testClassToRun++;
                            }

                            if (method.isAnnotationPresent(RequiresAccountSession.class) && !accountDataInitialized) {
                                initializeAccountData();
                            } else if (method.isAnnotationPresent(RequiresGuestSession.class) && !guestDataInitialized) {
                                initializeGuestData();
                            }
                        }
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void initializeGuestData() throws IOException {
        authenticatedInstance.guestSession();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 8D;

        authenticatedInstance.moviesService().addRating(testMovie.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvService().addRating(testTvShow.id, AuthenticationType.GUEST, ratingObject).execute();

        authenticatedInstance.tvEpisodesService().addRating(testTvEpisode.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST, ratingObject).execute();

        guestDataInitialized = true;
    }

    public static void initializeAccountData() throws IOException, InterruptedException {
        if (Strings.isNullOrEmpty(USERNAME) || Strings.isNullOrEmpty(PASSWORD)) {
            return;
        }

        System.out.println("Found TMDB Credentials. Authenticating Instance...");

        authenticatedInstance.accountSession(USERNAME, PASSWORD);

        account = authenticatedInstance.accountService().summary().execute().body();

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
            Thread.sleep(200);
            authenticatedInstance.accountService().favorite(account.id, favoriteMedia).execute();
            Thread.sleep(200);
            authenticatedInstance.accountService().watchlist(account.id, watchlistMedia).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        authenticatedInstance.accountService().favorite(account.id, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        authenticatedInstance.accountService().watchlist(account.id, watchlistMedia).execute();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 7.5;

        authenticatedInstance.moviesService().addRating(testMovie.id, AuthenticationType.ACCOUNT, ratingObject).execute();

        authenticatedInstance.tvService().addRating(testTvShow.id, AuthenticationType.ACCOUNT, ratingObject).execute();

        authenticatedInstance.tvEpisodesService().addRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT, ratingObject).execute();


        Thread.sleep(5000);

        accountDataInitialized = true;
    }

    @AfterClass
    public static void destructEnvironment() throws IOException, InterruptedException {

        System.out.println("Test Classes to run have been finished. Cleaning up...");
        rollbackAccountData();
        rollbackGuestData();
    }


    public static void rollbackGuestData() throws IOException {
        if (!guestDataInitialized) {
            return;
        }

        authenticatedInstance.moviesService().deleteRating(testMovie.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvService().deleteRating(testTvShow.id, AuthenticationType.GUEST).execute();

        authenticatedInstance.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST).execute();
    }


    public static void rollbackAccountData() throws IOException, InterruptedException {
        if (!accountDataInitialized) {
            return;
        }

        FavoriteMedia favoriteMedia = new FavoriteMedia();
        favoriteMedia.media_type = MediaType.MOVIE;
        favoriteMedia.favorite = false;
        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.media_type = MediaType.MOVIE;
        watchlistMedia.watchlist = false;
        for (Integer i : testListMovies) {

            favoriteMedia.media_id = i;
            watchlistMedia.media_id = i;

            authenticatedInstance.accountService().favorite(account.id, favoriteMedia).execute();
            Thread.sleep(200);
            authenticatedInstance.accountService().watchlist(account.id, watchlistMedia).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        authenticatedInstance.accountService().favorite(account.id, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        authenticatedInstance.accountService().watchlist(account.id, watchlistMedia).execute();

        authenticatedInstance.moviesService().deleteRating(testMovie.id, AuthenticationType.ACCOUNT).execute();

        authenticatedInstance.tvService().deleteRating(testTvShow.id, AuthenticationType.ACCOUNT).execute();

        authenticatedInstance.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT).execute();

        try {
            authenticatedInstance.listsService().delete(testList.id).execute();
        } catch (Exception exc) {

        }
    }
}
