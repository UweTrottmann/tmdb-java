package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Account;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.AccountStatesResults;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.BaseList;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseTvEpisode;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.FavoriteMedia;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListCreateRequest;
import com.uwetrottmann.tmdb2.entities.ListCreateResponse;
import com.uwetrottmann.tmdb2.entities.ListOperation;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.entities.WatchlistMedia;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.exceptions.TmdbNotFoundException;
import com.uwetrottmann.tmdb2.exceptions.TmdbServiceErrorException;
import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import org.assertj.core.util.Strings;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;

import javax.annotation.Nullable;
import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testListMovies;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.AccountAssertions.assertAccountStates;
import static com.uwetrottmann.tmdb2.assertions.AccountAssertions.assertBaseAccountStates;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseRatingObject;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertLocale;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertStatus;
import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowResultsPage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public class AccountSessionTest extends BaseTestCase {

    private static String USERNAME;
    private static String PASSWORD;

    @Nullable
    private static Account account;
    public static boolean accountDataInitialized;

    // TODO ut: split up and only run for tests that require it
    @BeforeClass
    public static void initializeAccountData() throws IOException, InterruptedException {
        if (Strings.isNullOrEmpty(USERNAME)) {
            USERNAME = System.getenv("TMDB_USERNAME");
        }
        if (Strings.isNullOrEmpty(PASSWORD)) {
            PASSWORD = System.getenv("TMDB_PASSWORD");
        }
        if (Strings.isNullOrEmpty(USERNAME) || Strings.isNullOrEmpty(PASSWORD)) {
            System.err.println("Skipping account tests. Set a username and password to run them.");
            accountDataInitialized = false;
            return;
        }

        Tmdb tmdb = getAuthenticatedInstance();
        tmdb.accountSession(USERNAME, PASSWORD);

        // FIXME ut: for all requests below ensure success or fail setup
        account = tmdb.accountService().summary().execute().body();

        ListCreateRequest listCreateRequest = new ListCreateRequest();

        listCreateRequest.name = "tmdb-java";
        listCreateRequest.description = "List for tmdb-java testing purposes";
        listCreateRequest.language = new TmdbLocale("en");

        ListCreateResponse listCreateResponse = getAuthenticatedInstance().listsService().createList(listCreateRequest).execute().body();

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

            listOperation.media_id = i;
            favoriteMedia.media_id = i;
            watchlistMedia.media_id = i;

            tmdb.accountService().favorite(account.id, favoriteMedia).execute();
            Thread.sleep(200);
            tmdb.accountService().watchlist(account.id, watchlistMedia).execute();
            Thread.sleep(200);
            tmdb.listsService().addMovie(listCreateResponse.list_id, listOperation).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        tmdb.accountService().favorite(account.id, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        tmdb.accountService().watchlist(account.id, watchlistMedia).execute();

        RatingObject ratingObject = new RatingObject();
        ratingObject.value = 7.5;

        tmdb.moviesService().addRating(testMovie.id, AuthenticationType.ACCOUNT,
                ratingObject).execute();

        tmdb.tvService().addRating(testTvShow.id, AuthenticationType.ACCOUNT, ratingObject).execute();

        tmdb.tvEpisodesService().addRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.ACCOUNT, ratingObject).execute();

        // leave some time for TMDb to process changes
        Thread.sleep(3000);

        accountDataInitialized = true;
    }

    @AfterClass
    public static void rollbackAccountData() throws IOException, InterruptedException {
        if (!accountDataInitialized) {
            return;
        }

        // FIXME ut: for all requests below ensure success or fail setup
        Tmdb tmdb = getAuthenticatedInstance();

        FavoriteMedia favoriteMedia = new FavoriteMedia();
        favoriteMedia.media_type = MediaType.MOVIE;
        favoriteMedia.favorite = false;
        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.media_type = MediaType.MOVIE;
        watchlistMedia.watchlist = false;
        for (Integer i : testListMovies) {

            favoriteMedia.media_id = i;
            watchlistMedia.media_id = i;

            tmdb.accountService().favorite(account.id, favoriteMedia).execute();
            Thread.sleep(200);
            tmdb.accountService().watchlist(account.id, watchlistMedia).execute();
        }

        favoriteMedia.media_type = MediaType.TV;
        favoriteMedia.media_id = testTvShow.id;
        tmdb.accountService().favorite(account.id, favoriteMedia).execute();

        watchlistMedia.media_type = MediaType.TV;
        watchlistMedia.media_id = 1399;
        tmdb.accountService().watchlist(account.id, watchlistMedia).execute();

        tmdb.moviesService().deleteRating(testMovie.id, AuthenticationType.ACCOUNT).execute();

        tmdb.tvService().deleteRating(testTvShow.id, AuthenticationType.ACCOUNT).execute();

        tmdb.tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.ACCOUNT).execute();

        ListResultsPage listResultsPage = getAuthenticatedInstance().accountService().lists(account.id).execute().body();

        for (BaseList list : listResultsPage.results) {
            {
                if (list.name.equals("tmdb-java")) {
                    try {
                        getAuthenticatedInstance().listsService().delete(list.id).execute();
                    } catch (TmdbServiceErrorException ignored) {
                    }
                }
            }
        }
    }

    @Test
    public void test_details() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<Account> call = getAuthenticatedInstance().accountService().summary();

        Account account = call.execute().body();

        assertThat(account).isNotNull();
        assertThat(account.id).isNotNull();
        assertThat(account.name).isNotNull();
        assertThat(account.avatar).isNotNull();
        assertThat(account.username).isNotNull();
        assertLocale(account.locale, true, true);
        assertThat(account.include_adult).isNotNull();
    }

    @Test
    public void test_lists() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<ListResultsPage> call = getAuthenticatedInstance().accountService().lists(
                0
        );

        ListResultsPage list = call.execute().body();

        assertListResultsPage(list);
    }

    @Test
    public void test_account_states_movie() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<AccountStates> call = getAuthenticatedInstance().moviesService().accountStates(
                testMovie.id
        );

        AccountStates accountStates = call.execute().body();

        assertAccountStates(accountStates);
    }

    @Test
    public void test_account_states_tvEpisode() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<BaseAccountStates> call = getAuthenticatedInstance().tvEpisodesService().accountStates(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        BaseAccountStates baseAccountStates = call.execute().body();

        assertBaseAccountStates(baseAccountStates);
    }

    @Test
    public void test_account_states_tvSeason() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<AccountStatesResults> call = getAuthenticatedInstance().tvSeasonsService().accountStates(
                testTvShow.id,
                testTvSeason.season_number
        );

        AccountStatesResults accountStatesResults = call.execute().body();

        assertThat(accountStatesResults).isNotNull();
        assertThat(accountStatesResults.id).isNotNull();
        assertThat(accountStatesResults.results).isNotNull();
        for (AccountStates baseAccountStates : accountStatesResults.results) {
            assertBaseAccountStates(baseAccountStates);
            assertThat(baseAccountStates.episode_number).isNotNull();
        }
    }

    @Test
    public void test_account_states_tvShow() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<AccountStates> call = getAuthenticatedInstance().tvService().accountStates(
                testTvShow.id
        );

        AccountStates accountStates = call.execute().body();

        assertAccountStates(accountStates);
    }

    @Test
    public void test_favorite_movies() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<MovieResultsPage> call = getAuthenticatedInstance().accountService().favoriteMovies(
                0
        );

        MovieResultsPage movies = call.execute().body();

        assertThat(movies).isNotNull();
        assumeTrue(!movies.results.isEmpty());

        assertMovieResultsPage(movies);
    }

    @Test
    public void test_favorite_tvShows() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvShowResultsPage> call = getAuthenticatedInstance().accountService().favoriteTvShows(
                0
        );

        TvShowResultsPage tvShows = call.execute().body();

        assertThat(tvShows).isNotNull();
        assumeTrue(!tvShows.results.isEmpty());

        assertTvShowResultsPage(tvShows);
    }

    @Test
    public void test_watchlist_movies() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<MovieResultsPage> call = getAuthenticatedInstance().accountService().watchlistMovies(
                0
        );

        MovieResultsPage movies = call.execute().body();

        assertThat(movies).isNotNull();
        assumeTrue(!movies.results.isEmpty());

        assertMovieResultsPage(movies);
    }

    @Test
    public void test_watchlist_tvShows() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvShowResultsPage> call = getAuthenticatedInstance().accountService().watchlistTvShows(
                0
        );

        TvShowResultsPage tvShows = call.execute().body();

        assertThat(tvShows).isNotNull();
        assumeTrue(!tvShows.results.isEmpty());

        assertTvShowResultsPage(tvShows);
    }

    @Test
    public void test_rated_movies() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<MovieResultsPage> callMRP = getAuthenticatedInstance().accountService().ratedMovies(
                0
        );

        MovieResultsPage movies = callMRP.execute().body();

        assertThat(movies).isNotNull();
        assumeTrue(!movies.results.isEmpty());

        assertMovieResultsPage(movies);
        for (BaseMovie movie : movies.results) {
            assertBaseRatingObject(movie);
        }

        assumeTrue(accountDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 10D;

        Call<Status> call = getAuthenticatedInstance().moviesService().addRating(testMovie.id,
                AuthenticationType.ACCOUNT, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().moviesService().deleteRating(testMovie.id, AuthenticationType.ACCOUNT);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_rated_tvShows() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvShowResultsPage> call = getAuthenticatedInstance().accountService().ratedTvShows(
                0
        );

        TvShowResultsPage tvShows = call.execute().body();

        assertThat(tvShows).isNotNull();
        assumeTrue(!tvShows.results.isEmpty());

        assertTvShowResultsPage(tvShows);
        for (BaseTvShow baseTvShow : tvShows.results) {
            assertBaseRatingObject(baseTvShow);
        }

        RatingObject obj = new RatingObject();
        obj.value = 10D;

        Call<Status> callStatus = getAuthenticatedInstance().tvService().addRating(testTvShow.id, AuthenticationType.ACCOUNT,
                obj);
        Status status = callStatus.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        callStatus = getAuthenticatedInstance().tvService().deleteRating(testTvShow.id, AuthenticationType.ACCOUNT);
        status = callStatus.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_rated_tvEpisodes() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvEpisodeResultsPage> callTERP = getAuthenticatedInstance().accountService().ratedTvShowEpisodes(
                0
        );

        TvEpisodeResultsPage tvShowEpisodes = callTERP.execute().body();

        assertThat(tvShowEpisodes).isNotNull();
        assumeTrue(!tvShowEpisodes.results.isEmpty());

        assertTvEpisodeResultsPage(tvShowEpisodes);
        for (BaseTvEpisode baseTvEpisode : tvShowEpisodes.results) {
            assertBaseRatingObject(baseTvEpisode);
            assertThat(baseTvEpisode.show_id).isNotNull();
        }

        assumeTrue(accountDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 10D;

        Call<Status> call = getAuthenticatedInstance().tvEpisodesService().addRating(testTvShow.id,
                testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number,
                testTvEpisode.episode_number, AuthenticationType.ACCOUNT);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);
    }

    @Test
    public void test_mark_as_favorite() throws IOException {
        assumeTrue(accountDataInitialized);

        FavoriteMedia fMedia = new FavoriteMedia();
        fMedia.favorite = true;
        fMedia.media_id = 550;
        fMedia.media_type = MediaType.MOVIE;

        // Add a movie to favorites and verify.
        Call<Status> call = getAuthenticatedInstance().accountService().favorite(
                0,
                fMedia
        );

        Status status = call.execute().body();

        assertStatus(status);

        fMedia.favorite = false;

        // Remove a movie from favorites and verify
        call = getAuthenticatedInstance().accountService().favorite(0, fMedia);

        status = call.execute().body();

        assertStatus(status);

    }

    @Test
    public void test_add_to_watchlist() throws IOException {
        assumeTrue(accountDataInitialized);

        WatchlistMedia wMedia = new WatchlistMedia();
        wMedia.watchlist = true;
        wMedia.media_id = 550;
        wMedia.media_type = MediaType.MOVIE;

        // Add a movie to favorites and verify.
        Call<Status> call = getAuthenticatedInstance().accountService().watchlist(
                0,
                wMedia
        );

        Status status = call.execute().body();

        assertStatus(status);

        wMedia.watchlist = false;

        // Remove a movie from favorites and verify
        call = getAuthenticatedInstance().accountService().watchlist(
                0,
                wMedia
        );

        status = call.execute().body();

        assertStatus(status);
    }

    // TODO ut: verify list contents (maybe implicitly if delete works?)
    @Test(timeout = 100000)
    public void test_create_edit_clear_delete_list() throws IOException, InterruptedException {
        assumeTrue(accountDataInitialized);

        ListCreateRequest listCreateRequest = new ListCreateRequest();
        listCreateRequest.name = "Test";
        listCreateRequest.language = new TmdbLocale("en");
        listCreateRequest.description = "Test";

        Call<ListCreateResponse> callLCResponse = getAuthenticatedInstance().listsService().createList(
                listCreateRequest);

        ListCreateResponse listCreateResponse = callLCResponse.execute().body();

        assertThat(listCreateResponse).isNotNull();
        assertThat(listCreateResponse.success).isTrue();
        assertThat(listCreateResponse.list_id).isNotNull();
        assertThat(listCreateResponse.status_code).isEqualTo(1);

        ListOperation listOperation = new ListOperation();
        listOperation.media_id = testMovie.id;

        Call<Status> callStatus = getAuthenticatedInstance().listsService().addMovie(listCreateResponse.list_id,
                listOperation);

        Status status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(12);

        callStatus = getAuthenticatedInstance().listsService().removeMovie(listCreateResponse.list_id, listOperation);

        status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(13);

        callStatus = getAuthenticatedInstance().listsService().clear(listCreateResponse.list_id, true);

        status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(12);

        // Delete List will delete the list, but it will throw a service related error due to a service bug.
        // Related Information: https://www.themoviedb.org/talk/592ae962c3a368787005afc1

        callStatus = getAuthenticatedInstance().listsService().delete(listCreateResponse.list_id);
        try {
            status = callStatus.execute().body();
            assertThat(status).isNotNull();
        } catch (TmdbServiceErrorException exc) {

            Call<List> callList = getAuthenticatedInstance().listsService().summary(listCreateResponse.list_id);

            // We get a Service Related Exception. We are not sure if our list deleted
            // but we can query and check it out.
            try {
                List list = callList.execute().body();

                fail("List hasn't been erased. It is advised to manually erase the list from your profile.");
            } catch (TmdbNotFoundException nExc) {
                assertThat(nExc).isNotNull();
            }
        }
    }
}
