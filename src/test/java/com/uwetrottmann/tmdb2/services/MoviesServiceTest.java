package com.uwetrottmann.tmdb2.services;

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
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResults;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

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

public class MoviesServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws ParseException, IOException {
        Call<Movie> call = getUnauthenticatedInstance().moviesService().summary(
                testMovie.id
        );

        Movie movie = call.execute().body();

        assertMovie(movie);
    }

    @Test
    public void test_summary_language() throws ParseException, IOException {
        Call<Movie> call = getUnauthenticatedInstance().moviesService().summary(
                testMovie.id,
                "pt-BR"
        );

        Movie movie = call.execute().body();

        assertMovie(movie);

        assertThat(movie.title).isEqualTo("Os Vingadores");
    }


    @Test
    public void test_summary_append_all() throws IOException {

        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", new TmdbDate(testMovieChangesStartDate).toString());
        opts.put("end_date", new TmdbDate(testMovieChangesEndDate).toString());

        Call<Movie> call = getUnauthenticatedInstance().moviesService().summary(
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

        Movie movie = call.execute().body();

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
        Call<AlternativeTitles> call = getUnauthenticatedInstance().moviesService().alternativeTitles(
                testMovie.id,
                null
        );

        AlternativeTitles titles = call.execute().body();

        assertAlternativeTitles(titles);
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getUnauthenticatedInstance().moviesService().credits(
                testMovie.id
        );

        Credits credits = call.execute().body();

        assertCredits(credits);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getUnauthenticatedInstance().moviesService().images(
                testMovie.id,
                null
        );

        Images images = call.execute().body();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.backdrops);
        assertImages(images.posters);
    }

    @Test
    public void test_keywords() throws IOException {
        Call<Keywords> call = getUnauthenticatedInstance().moviesService().keywords(
                TestData.testMovie.id
        );

        Keywords keywords = call.execute().body();


        assertKeywords(keywords);
    }

    @Test
    public void test_release_dates() throws IOException {
        Call<ReleaseDatesResults> call = getUnauthenticatedInstance().moviesService().releaseDates(
                testMovie.id
        );

        ReleaseDatesResults results = call.execute().body();

        assertMovieReleaseDates(results);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getUnauthenticatedInstance().moviesService().videos(
                testMovie.id,
                null
        );

        Videos videos = call.execute().body();

        assertVideos(videos);
    }

    @Test
    public void test_translations() throws IOException {
        Call<Translations> call = getUnauthenticatedInstance().moviesService().translations(
                testMovie.id
        );
        Translations translations = call.execute().body();

        assertTranslations(translations);

    }

    @Test
    public void test_similar() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().similar(
                testMovie.id,
                1,
                null
        );
        MovieResultsPage results = call.execute().body();

        assertMovieResultsPage(results);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().moviesService().changes(
                testMovie.id,
                new TmdbDate(testMovieChangesStartDate),
                new TmdbDate(testMovieChangesEndDate),
                null
        );

        Changes results = call.execute().body();

        assertContentChanges(results);
    }

    @Test
    public void test_reviews() throws IOException {
        Call<ReviewResultsPage> call = getUnauthenticatedInstance().moviesService().reviews(
                testMovie.id,
                1,
                null
        );

        ReviewResultsPage results = call.execute().body();

        assertReviews(results);
    }

    @Test
    public void test_recommendations() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().recommendations(
                testMovie.id,
                1,
                null
        );

        MovieResultsPage results = call.execute().body();

        assertMovieResultsPage(results);
    }

    @Test
    public void test_lists() throws IOException {
        Call<ListResultsPage> call = getUnauthenticatedInstance().moviesService().lists(
                testMovie.id,
                1,
                null
        );

        ListResultsPage results = call.execute().body();

        assertListResultsPage(results);

    }

    @Test
    public void test_latest() throws IOException {
        Call<Movie> call = getUnauthenticatedInstance().moviesService().latest();

        Movie movie = call.execute().body();

        // Latest testMovie might not have a complete TMDb entry, but should at least some basic properties.
        assertThat(movie).isNotNull();
        assertThat(movie.id).isPositive();
        assertThat(movie.title).isNotEmpty();
    }

    @Test
    public void test_upcoming() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().upcoming(
                null,
                null
        );

        MovieResultsPage page = call.execute().body();

        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_nowPlaying() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().nowPlaying(
                null,
                null
        );

        MovieResultsPage page = call.execute().body();

        assertMovieResultsPage(page);
    }

    @Test
    public void test_popular() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().popular(
                null,
                null
        );

        MovieResultsPage page = call.execute().body();

        assertMovieResultsPage(page);

    }

    @Test
    public void test_topRated() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().moviesService().topRated(
                null,
                null
        );

        MovieResultsPage page = call.execute().body();

        assertMovieResultsPage(page);
    }

}
