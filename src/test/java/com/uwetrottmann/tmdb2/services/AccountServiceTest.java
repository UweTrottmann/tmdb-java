package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.annotations.RequiresAccountSession;
import com.uwetrottmann.tmdb2.entities.Account;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseTvEpisode;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.FavoriteMedia;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.entities.WatchlistMedia;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TmdbTestSuite.accountDataInitialized;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseRatingObject;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertStatus;
import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowResultsPage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

@RequiresAccountSession
public class AccountServiceTest extends BaseTestCase {

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
        assertThat(account.iso_639_1).isNotNull();
        assertThat(account.iso_3166_1).isNotNull();
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
    public void test_favorite_movies() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<MovieResultsPage> call = getAuthenticatedInstance().accountService().favoriteMovies(
                0,
                null,
                null,
                null
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
                0,
                null,
                null,
                null
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
                0,
                null,
                null,
                null
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
                0,
                null,
                null,
                null
        );

        TvShowResultsPage tvShows = call.execute().body();

        assertThat(tvShows).isNotNull();
        assumeTrue(!tvShows.results.isEmpty());

        assertTvShowResultsPage(tvShows);
    }

    @Test
    public void test_rated_movies() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<MovieResultsPage> call = getAuthenticatedInstance().accountService().ratedMovies(
                0,
                null,
                null,
                null
        );

        MovieResultsPage movies = call.execute().body();

        assertThat(movies).isNotNull();
        assumeTrue(!movies.results.isEmpty());

        assertMovieResultsPage(movies);
        for (BaseMovie movie : movies.results) {
            assertBaseRatingObject(movie);
        }
    }

    @Test
    public void test_rated_tvShows() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvShowResultsPage> call = getAuthenticatedInstance().accountService().ratedTvShows(
                0,
                null,
                null,
                null
        );

        TvShowResultsPage tvShows = call.execute().body();

        assertThat(tvShows).isNotNull();
        assumeTrue(!tvShows.results.isEmpty());

        assertTvShowResultsPage(tvShows);
        for (BaseTvShow baseTvShow : tvShows.results) {
            assertBaseRatingObject(baseTvShow);
        }
    }

    @Test
    public void test_rated_tvEpisodes() throws IOException {
        assumeTrue(accountDataInitialized);

        Call<TvEpisodeResultsPage> call = getAuthenticatedInstance().accountService().ratedTvShowEpisodes(
                0,
                null,
                null,
                null
        );

        TvEpisodeResultsPage tvShowEpisodes = call.execute().body();

        assertThat(tvShowEpisodes).isNotNull();
        assumeTrue(!tvShowEpisodes.results.isEmpty());

        assertTvEpisodeResultsPage(tvShowEpisodes);
        for (BaseTvEpisode baseTvEpisode : tvShowEpisodes.results) {
            assertBaseRatingObject(baseTvEpisode);
            assertThat(baseTvEpisode.show_id).isNotNull();
        }

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
}
