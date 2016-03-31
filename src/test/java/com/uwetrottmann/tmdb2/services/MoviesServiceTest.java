
package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.MovieAlternativeTitles;
import com.uwetrottmann.tmdb2.entities.MovieKeywords;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResult;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResults;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;
import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class MoviesServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws ParseException, IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID, null, null);
        Movie movie = call.execute().body();
        assertMovie(movie);
        assertThat(movie.original_title).isEqualTo(TestData.MOVIE_TITLE);
    }

    @Test
    public void test_summary_language() throws ParseException, IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID, "pt-BR", null);
        Movie movie = call.execute().body();
        assertThat(movie).isNotNull();
        assertThat(movie.title).isEqualTo("Clube da Luta");
    }

    @Test
    public void test_summary_with_collection() throws ParseException, IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_WITH_COLLECTION_ID, null, null);
        Movie movie = call.execute().body();
        assertThat(movie.title).isEqualTo(TestData.MOVIE_WITH_COLLECTION_TITLE);
        assertThat(movie.belongs_to_collection).isNotNull();
        assertThat(movie.belongs_to_collection.id).isEqualTo(1241);
        assertThat(movie.belongs_to_collection.name).isEqualTo("Harry Potter Collection");
    }

    private void assertMovie(Movie movie) {
        assertThat(movie).isNotNull();
        assertThat(movie.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(movie.title).isEqualTo(TestData.MOVIE_TITLE);
        assertThat(movie.overview).isNotEmpty();
        assertThat(movie.tagline).isNotEmpty();
        assertThat(movie.adult).isFalse();
        assertThat(movie.backdrop_path).isNotEmpty();
        assertThat(movie.budget).isEqualTo(63000000);
        assertThat(movie.imdb_id).isEqualTo(TestData.MOVIE_IMDB);
        assertThat(movie.poster_path).isNotEmpty();
        assertThat(movie.release_date).isEqualTo("1999-10-14");
        assertThat(movie.revenue).isEqualTo(100853753);
        assertThat(movie.runtime).isEqualTo(139);
        assertThat(movie.vote_average).isPositive();
        assertThat(movie.vote_count).isPositive();
    }

    @Test
    public void test_summary_append_videos() throws IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.VIDEOS));
        Movie movie = call.execute().body();

        assertNotNull(movie.videos);
    }

    @Test
    public void test_summary_append_credits() throws IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.CREDITS));
        Movie movie = call.execute().body();

        assertNotNull(movie.credits);
    }

    @Test
    public void test_summary_append_release_dates() throws IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES));
        Movie movie = call.execute().body();

        assertNotNull(movie.release_dates);
    }

    @Test
    public void test_summary_append_similar() throws IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.SIMILAR));
        Movie movie = call.execute().body();

        assertNotNull(movie.similar);
    }

    @Test
    public void test_summary_append_all() throws IOException {
        Call<Movie> call = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.SIMILAR));
        Movie movie = call.execute().body();

        assertNotNull(movie.release_dates);
        assertNotNull(movie.release_dates.results);
        assertThat(movie.release_dates.results).isNotEmpty();
        assertNotNull(movie.credits);
        assertNotNull(movie.videos);
        assertNotNull(movie.similar);
    }

    @Test
    public void test_alternative_titles() throws IOException {
        Call<MovieAlternativeTitles> call = getManager().moviesService().alternativeTitles(TestData.MOVIE_ID, null);
        MovieAlternativeTitles titles = call.execute().body();
        assertThat(titles).isNotNull();
        assertThat(titles.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(titles.titles).isNotEmpty();
        assertThat(titles.titles.get(0).iso_3166_1).hasSize(2);
        assertThat(titles.titles.get(0).title).isNotEmpty();
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getManager().moviesService().credits(TestData.MOVIE_ID);
        Credits credits = call.execute().body();
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.cast.get(0).name).isEqualTo("Edward Norton");
        assertThat(credits.crew).isNotEmpty();
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getManager().moviesService().images(TestData.MOVIE_ID, null);
        Images images = call.execute().body();
        assertThat(images).isNotNull();
        assertThat(images.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(images.backdrops).isNotEmpty();
        assertThat(images.backdrops.get(0).file_path).isNotEmpty();
        assertThat(images.backdrops.get(0).width).isEqualTo(1280);
        assertThat(images.backdrops.get(0).height).isEqualTo(720);
        assertThat(images.backdrops.get(0).iso_639_1).isEqualTo("en");
        assertThat(images.backdrops.get(0).aspect_ratio).isGreaterThan(1.7f);
        assertThat(images.backdrops.get(0).vote_average).isPositive();
        assertThat(images.backdrops.get(0).vote_count).isPositive();
        assertThat(images.posters).isNotEmpty();
        assertThat(images.posters.get(0).file_path).isNotEmpty();
        assertThat(images.posters.get(0).width).isPositive();
        assertThat(images.posters.get(0).height).isPositive();
        assertThat(images.posters.get(0).iso_639_1).hasSize(2);
        assertThat(images.posters.get(0).aspect_ratio).isGreaterThan(0.6f);
        assertThat(images.posters.get(0).vote_average).isPositive();
        assertThat(images.posters.get(0).vote_count).isPositive();
    }

    @Test
    public void test_keywords() throws IOException {
        Call<MovieKeywords> call = getManager().moviesService().keywords(TestData.MOVIE_ID);
        MovieKeywords keywords = call.execute().body();
        assertThat(keywords).isNotNull();
        assertThat(keywords.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(keywords.keywords.get(0).id).isEqualTo(825);
        assertThat(keywords.keywords.get(0).name).isEqualTo("support group");
    }

    @Test
    public void test_release_dates() throws IOException {
        Call<ReleaseDatesResults> call = getManager().moviesService().releaseDates(TestData.MOVIE_ID);
        ReleaseDatesResults results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(results.results).isNotNull();
        assertThat(results.results.isEmpty()).isFalse();

        ReleaseDatesResult usResult = null;
        for (ReleaseDatesResult result : results.results) {
            assertThat(result.iso_3166_1).isNotNull();
            if (result.iso_3166_1.equals("US")) {
                usResult = result;
            }
        }

        assertThat(usResult).isNotNull();
        assertThat(usResult.release_dates).isNotNull();
        assertThat(usResult.release_dates.isEmpty()).isFalse();
        assertThat(usResult.release_dates.get(0).iso_639_1).isNotNull();
        assertThat(usResult.release_dates.get(0).certification).isEqualTo("R");
        assertThat(usResult.release_dates.get(0).release_date).isEqualTo("1999-10-14T00:00:00.000Z");
        assertThat(usResult.release_dates.get(0).note).isNotNull();
        assertThat(usResult.release_dates.get(0).type).isBetween(1, 6);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getManager().moviesService().videos(TestData.MOVIE_ID, null);
        Videos videos = call.execute().body();
        assertThat(videos).isNotNull();
        assertThat(videos.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(videos.results.get(0).id).isNotNull();
        assertThat(videos.results.get(0).iso_639_1).isNotNull();
        assertThat(videos.results.get(0).key).isNotNull();
        assertThat(videos.results.get(0).name).isNotNull();
        assertThat(videos.results.get(0).site).isEqualTo("YouTube");
        assertThat(videos.results.get(0).size).isNotNull();
        assertThat(videos.results.get(0).type).isEqualTo("Trailer");
    }

    @Test
    public void test_translations() throws IOException {
        Call<Translations> call = getManager().moviesService().translations(TestData.MOVIE_ID, null);
        Translations translations = call.execute().body();
        assertThat(translations).isNotNull();
        assertThat(translations.id).isEqualTo(TestData.MOVIE_ID);
        for (Translations.Translation translation : translations.translations) {
            assertThat(translation.name).isNotNull();
            assertThat(translation.iso_639_1).isNotNull();
            assertThat(translation.english_name).isNotNull();
        }
    }

    @Test
    public void test_similar() throws IOException {
        Call<MovieResultsPage> call = getManager().moviesService().similar(TestData.MOVIE_ID, 3, null);
        MovieResultsPage results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.page).isNotNull().isPositive();
        assertThat(results.total_pages).isNotNull().isPositive();
        assertThat(results.total_results).isNotNull().isPositive();
        assertThat(results.results).isNotEmpty();
        assertThat(results.results.get(0).adult).isEqualTo(false);
        assertThat(results.results.get(0).backdrop_path).isNotNull();
        assertThat(results.results.get(0).id).isNotNull().isPositive();
        assertThat(results.results.get(0).original_title).isNotNull();
        assertThat(results.results.get(0).release_date).isNotNull();
        assertThat(results.results.get(0).poster_path).isNotNull();
        assertThat(results.results.get(0).popularity).isNotNull().isPositive();
        assertThat(results.results.get(0).title).isNotNull();
        assertThat(results.results.get(0).vote_average).isNotNull().isPositive();
        assertThat(results.results.get(0).vote_count).isNotNull().isPositive();
    }

    @Test
    public void test_reviews() throws IOException {
        Call<ReviewResultsPage> call = getManager().moviesService().reviews(49026, 1, null);
        ReviewResultsPage results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.id).isNotNull();
        assertThat(results.page).isNotNull().isPositive();
        assertThat(results.total_pages).isNotNull().isPositive();
        assertThat(results.total_results).isNotNull().isPositive();
        assertThat(results.results).isNotEmpty();
        assertThat(results.results.get(0).id).isNotNull();
        assertThat(results.results.get(0).author).isNotNull();
        assertThat(results.results.get(0).content).isNotNull();
        assertThat(results.results.get(0).url).isNotNull();
    }

    @Test
    public void test_lists() throws IOException {
        Call<ListResultsPage> call = getManager().moviesService().lists(49026, 1, null);
        ListResultsPage results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.id).isNotNull();
        assertThat(results.page).isNotNull().isPositive();
        assertThat(results.total_pages).isNotNull().isPositive();
        assertThat(results.total_results).isNotNull().isPositive();
        assertThat(results.results).isNotEmpty();
        assertThat(results.results.get(0).id).isNotNull();
        assertThat(results.results.get(0).description).isNotNull();
        assertThat(results.results.get(0).favorite_count).isNotNull().isPositive();
        assertThat(results.results.get(0).item_count).isNotNull().isPositive();
        assertThat(results.results.get(0).iso_639_1).isNotNull();
        assertThat(results.results.get(0).name).isNotNull();
        assertThat(results.results.get(0).poster_path).isNotNull();
    }

    @Test
    public void test_latest() throws IOException {
        Call<Movie> call = getManager().moviesService().latest();
        Movie movie = call.execute().body();
        // Latest movie might not have a complete TMDb entry, but should at least some basic properties.
        assertThat(movie).isNotNull();
        assertThat(movie.id).isPositive();
        assertThat(movie.title).isNotEmpty();
    }

    @Test
    public void test_upcoming() throws IOException {
        Call<MovieResultsPage> call = getManager().moviesService().upcoming(null, null);
        MovieResultsPage page = call.execute().body();
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_nowPlaying() throws IOException {
        Call<MovieResultsPage> call = getManager().moviesService().nowPlaying(null, null);
        MovieResultsPage page = call.execute().body();
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_popular() throws IOException {
        Call<MovieResultsPage> call = getManager().moviesService().popular(null, null);
        MovieResultsPage page = call.execute().body();
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_topRated() throws IOException {
        Call<MovieResultsPage> call = getManager().moviesService().topRated(null, null);
        MovieResultsPage page = call.execute().body();
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

}
