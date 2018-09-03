package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testMovieChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testMovieChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertAlternativeTitles;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertTranslations;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertKeywords;
import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovie;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieReleaseDates;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;
import static com.uwetrottmann.tmdb2.assertions.ReviewAssertions.assertReviews;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.AlternativeTitles;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.Keywords;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.MovieExternalIds;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResults;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Single;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;

public class MoviesServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        Single<Movie> single = getUnauthenticatedInstance().rx.moviesService().summary(
                testMovie.id
        );

        Movie movie = single.blockingGet();

        assertMovie(movie);
    }

    @Test
    public void test_summary_language() throws IOException {
        Single<Movie> single = getUnauthenticatedInstance().rx.moviesService().summary(
                testMovie.id,
                "pt-BR"
        );

        Movie movie = single.blockingGet();

        assertMovie(movie);

        assertThat(movie.title).isEqualTo("Os Vingadores");
    }


    @Test
    public void test_summary_append_all() throws IOException {

        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", new TmdbDate(testMovieChangesStartDate).toString());
        opts.put("end_date", new TmdbDate(testMovieChangesEndDate).toString());

        Single<Movie> single = getUnauthenticatedInstance().rx.moviesService().summary(
                testMovie.id,
                new AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR,
                        AppendToResponseItem.CHANGES,
                        AppendToResponseItem.TRANSLATIONS,
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.RECOMMENDATIONS,
                        AppendToResponseItem.REVIEWS,
                        AppendToResponseItem.LISTS,
                        AppendToResponseItem.KEYWORDS
                ),
                opts
        );

        Movie movie = single.blockingGet();

        assertMovieDataIntegrity(movie);

        //Release Dates Assertions
        assertMovieReleaseDates(movie.release_dates);

        //Credits Assertions
        assertCredits(movie.credits);

        //Videos Assertions
        assertVideos(movie.videos);

        //Similar Movies Assertions
        assertMovieResultsPage(movie.similar);

        //Alternative Titles Assertions
        assertAlternativeTitles(movie.alternative_titles);

        //Movie Changes Assertions
        assertContentChanges(movie.changes);

        //Translations Assertions
        assertTranslations(movie.translations);

        //Recommended Movies Assertions
        assertMovieResultsPage(movie.recommendations);

        //Images Assertions
        assertThat(movie.images).isNotNull();
        assertImages(movie.images.backdrops);
        assertImages(movie.images.posters);

        //Keywords Assertions
        assertKeywords(movie.keywords);

        //Movie Lists Assertions
        assertListResultsPage(movie.lists);

        //Reviews Assertions
        assertReviews(movie.reviews);
    }

    @Test
    public void test_alternative_titles() throws IOException {
        Single<AlternativeTitles> single = getUnauthenticatedInstance().rx.moviesService().alternativeTitles(
                testMovie.id,
                null
        );

        AlternativeTitles titles = single.blockingGet();

        assertAlternativeTitles(titles);
    }

    @Test
    public void test_credits() throws IOException {
        Single<Credits> single = getUnauthenticatedInstance().rx.moviesService().credits(
                testMovie.id
        );

        Credits credits = single.blockingGet();

        assertCredits(credits);
    }

    @Test
    public void test_externalIds() throws IOException {
        int movieId = 335984;
        Single<MovieExternalIds> single = getUnauthenticatedInstance().rx.moviesService().externalIds(
                movieId, null
        );

        MovieExternalIds ids = single.blockingGet();
        assertThat(ids.id).isEqualTo(movieId);
        assertThat(ids.imdb_id).isEqualTo("tt1856101");
        assertThat(ids.facebook_id).isEqualTo("BladeRunner2049");
        assertThat(ids.instagram_id).isEqualTo("bladerunnermovie");
        assertThat(ids.twitter_id).isEqualTo("bladerunner");
    }

    @Test
    public void test_images() throws IOException {
        Single<Images> single = getUnauthenticatedInstance().rx.moviesService().images(
                testMovie.id,
                null
        );

        Images images = single.blockingGet();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.backdrops);
        assertImages(images.posters);
    }

    @Test
    public void test_keywords() throws IOException {
        Single<Keywords> single = getUnauthenticatedInstance().rx.moviesService().keywords(
                TestData.testMovie.id
        );

        Keywords keywords = single.blockingGet();


        assertKeywords(keywords);
    }

    @Test
    public void test_release_dates() throws IOException {
        Single<ReleaseDatesResults> single = getUnauthenticatedInstance().rx.moviesService().releaseDates(
                testMovie.id
        );

        ReleaseDatesResults results = single.blockingGet();

        assertMovieReleaseDates(results);
    }

    @Test
    public void test_videos() throws IOException {
        Single<Videos> single = getUnauthenticatedInstance().rx.moviesService().videos(
                testMovie.id,
                null
        );

        Videos videos = single.blockingGet();

        assertVideos(videos);
    }

    @Test
    public void test_translations() throws IOException {
        Single<Translations> single = getUnauthenticatedInstance().rx.moviesService().translations(
                testMovie.id
        );
        Translations translations = single.blockingGet();

        assertTranslations(translations);

    }

    @Test
    public void test_similar() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().similar(
                testMovie.id,
                1,
                null
        );
        MovieResultsPage results = single.blockingGet();

        assertMovieResultsPage(results);
    }

    @Test
    public void test_changes() throws IOException {
        Single<Changes> single = getUnauthenticatedInstance().rx.moviesService().changes(
                testMovie.id,
                new TmdbDate(testMovieChangesStartDate),
                new TmdbDate(testMovieChangesEndDate),
                null
        );

        Changes results = single.blockingGet();

        assertContentChanges(results);
    }

    @Test
    public void test_reviews() throws IOException {
        Single<ReviewResultsPage> single = getUnauthenticatedInstance().rx.moviesService().reviews(
                testMovie.id,
                1,
                null
        );

        ReviewResultsPage results = single.blockingGet();

        assertReviews(results);
    }

    @Test
    public void test_recommendations() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().recommendations(
                testMovie.id,
                1,
                null
        );

        MovieResultsPage results = single.blockingGet();

        assertMovieResultsPage(results);
    }

    @Test
    public void test_lists() throws IOException {
        Single<ListResultsPage> single = getUnauthenticatedInstance().rx.moviesService().lists(
                testMovie.id,
                1,
                null
        );

        ListResultsPage results = single.blockingGet();

        assertListResultsPage(results);

    }

    @Test
    public void test_latest() throws IOException {
        Single<Movie> single = getUnauthenticatedInstance().rx.moviesService().latest();

        Movie movie = single.blockingGet();

        // Latest testMovie might not have a complete TMDb entry, but should at least some basic properties.
        assertThat(movie).isNotNull();
        assertThat(movie.id).isPositive();
        assertThat(movie.title).isNotEmpty();
    }

    @Test
    public void test_upcoming() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().upcoming(
                null,
                null
        );

        MovieResultsPage page = single.blockingGet();

        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_nowPlaying() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().nowPlaying(
                null,
                null
        );

        MovieResultsPage page = single.blockingGet();

        assertMovieResultsPage(page);
    }

    @Test
    public void test_popular() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().popular(
                null,
                null
        );

        MovieResultsPage page = single.blockingGet();

        assertMovieResultsPage(page);

    }

    @Test
    public void test_topRated() throws IOException {
        Single<MovieResultsPage> single = getUnauthenticatedInstance().rx.moviesService().topRated(
                null,
                null
        );

        MovieResultsPage page = single.blockingGet();

        assertMovieResultsPage(page);
    }

}
