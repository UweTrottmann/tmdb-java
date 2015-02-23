
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.Images;
import com.uwetrottmann.tmdb.entities.MovieAlternativeTitles;
import com.uwetrottmann.tmdb.entities.AppendToResponse;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.MovieKeywords;
import com.uwetrottmann.tmdb.entities.ListResultsPage;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.Releases;
import com.uwetrottmann.tmdb.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb.entities.Videos;
import com.uwetrottmann.tmdb.enumerations.AppendToResponseItem;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MoviesServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    @Test
    public void test_summary() throws ParseException {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID, null, null);
        assertNotNull("Result was null.", movie);
        assertNotNull("Movie Adult was null.", movie.adult);
        assertThat(movie.adult).isFalse();
        assertNotNull("Movie backdrop path was null.", movie.backdrop_path);
        assertNotNull("Movie budget was null.", movie.budget);
        assertEquals("Movie budget does not match.", 63000000, movie.budget.intValue());
        assertNotNull("Movie TMDB ID was null.", movie.id);
        assertEquals("Movie TMDB ID does not match.", TestData.MOVIE_ID, movie.id.intValue());
        assertEquals("Movie IMDB ID does not match.", "tt0137523", movie.imdb_id);
        assertEquals("Move orignal title does not match.", "Fight Club", movie.original_title);
        assertNotNull("Movie overview was null.", movie.overview);
        assertNotNull("Movie poster path was null.", movie.poster_path);
        assertNotNull("Movie release date was null.", movie.release_date);
        assertEquals("Movie release date does not match.", JSON_STRING_DATE.parse("1999-10-14"),
                movie.release_date);
        assertNotNull("Movie revenue was null.", movie.revenue);
        assertEquals("Movie revenue does not match.", 100853753, movie.revenue.intValue());
        assertNotNull("Movie runtime was null.", movie.runtime);
        assertEquals("Movie runtime does not match.", 139, movie.runtime.intValue());
        assertNotNull("Movie tagline was null.", movie.tagline);
        assertEquals("Move title does not match.", "Fight Club", movie.title);
        assertNotNull("Movie vote_average was null.", movie.vote_average);
        assertNotNull("Movie vote_count was null.", movie.vote_count);
    }
    
    @Test
    public void test_summary_language() throws ParseException {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID, "pt", null);
        assertNotNull("Result was null.", movie);
        assertNotNull("Movie Adult was null.", movie.adult);
        assertEquals("Movie Adult does not match.", false, movie.adult.booleanValue());
        assertNotNull("Movie backdrop path was null.", movie.backdrop_path);
        assertNotNull("Movie budget was null.", movie.budget);
        assertEquals("Movie budget does not match.", 63000000, movie.budget.intValue());
        assertNotNull("Movie TMDB ID was null.", movie.id);
        assertEquals("Movie TMDB ID does not match.", TestData.MOVIE_ID, movie.id.intValue());
        assertEquals("Movie IMDB ID does not match.", "tt0137523", movie.imdb_id);
        assertEquals("Move orignal title does not match.", "Fight Club", movie.original_title);
        assertNotNull("Movie overview was null.", movie.overview);
        assertNotNull("Movie poster path was null.", movie.poster_path);
        assertNotNull("Movie release date was null.", movie.release_date);
        assertEquals("Movie release date does not match.", JSON_STRING_DATE.parse("1999-10-14"),
                movie.release_date);
        assertNotNull("Movie revenue was null.", movie.revenue);
        assertEquals("Movie revenue does not match.", 100853753, movie.revenue.intValue());
        assertNotNull("Movie runtime was null.", movie.runtime);
        assertEquals("Movie runtime does not match.", 139, movie.runtime.intValue());
        assertNotNull("Movie tagline was null.", movie.tagline);
        assertEquals("Move title does not match.", "Clube da Luta", movie.title);
        assertNotNull("Movie vote_average was null.", movie.vote_average);
        assertNotNull("Movie vote_count was null.", movie.vote_count);
    }

    @Test
    public void test_summary_append_videos() {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.VIDEOS));

        assertNotNull(movie.videos);
    }

    @Test
    public void test_summary_append_credits() {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.CREDITS));

        assertNotNull(movie.credits);
    }

    @Test
    public void test_summary_append_releases() {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASES));

        assertNotNull(movie.releases);
    }
    
    @Test
    public void test_summary_append_similar() {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.SIMILAR));

        assertNotNull(movie.similar);
    }

    @Test
    public void test_summary_append_all() {
        Movie movie = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.SIMILAR));

        assertNotNull(movie.releases);
        assertNotNull(movie.credits);
        assertNotNull(movie.videos);
        assertNotNull(movie.similar);
    }
    
    @Test
    public void test_alternative_titles() {
        MovieAlternativeTitles titles = getManager().moviesService().alternativeTitles(TestData.MOVIE_ID, null);
        assertThat(titles).isNotNull();
        assertThat(titles.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(titles.titles).isNotEmpty();
        assertThat(titles.titles.get(0).iso_3166_1).isEqualTo("PL");
        assertThat(titles.titles.get(0).title).isEqualTo("Podziemny krąg");
    }
    
    @Test
    public void test_credits() {
        Credits credits = getManager().moviesService().credits(TestData.MOVIE_ID);
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.cast.get(0).name).isEqualTo("Edward Norton");
        assertThat(credits.crew).isNotEmpty();
    }
    
    @Test
    public void test_images() {
        Images images = getManager().moviesService().images(TestData.MOVIE_ID, null);
        assertThat(images).isNotNull();
        assertThat(images.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(images.backdrops).isNotEmpty();
        assertThat(images.backdrops.get(0).file_path).isEqualTo("/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg");
        assertThat(images.backdrops.get(0).width).isEqualTo(1280);
        assertThat(images.backdrops.get(0).height).isEqualTo(720);
        assertThat(images.backdrops.get(0).iso_639_1).isEqualTo("en");
        assertThat(images.backdrops.get(0).aspect_ratio).isGreaterThan(1.7f);
        assertThat(images.backdrops.get(0).vote_average).isPositive();
        assertThat(images.backdrops.get(0).vote_count).isPositive();
        assertThat(images.posters).isNotEmpty();
        assertThat(images.posters.get(0).file_path).isEqualTo("/2lECpi35Hnbpa4y46JX0aY3AWTy.jpg");
        assertThat(images.posters.get(0).width).isEqualTo(1000);
        assertThat(images.posters.get(0).height).isEqualTo(1500);
        assertThat(images.posters.get(0).iso_639_1).isEqualTo("en");
        assertThat(images.posters.get(0).aspect_ratio).isGreaterThan(0.6f);
        assertThat(images.posters.get(0).vote_average).isPositive();
        assertThat(images.posters.get(0).vote_count).isPositive();
    }
    
    @Test
    public void test_keywords() {
        MovieKeywords keywords = getManager().moviesService().keywords(TestData.MOVIE_ID);
        assertThat(keywords).isNotNull();
        assertThat(keywords.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(keywords.keywords.get(0).id).isEqualTo(825);
        assertThat(keywords.keywords.get(0).name).isEqualTo("support group");
    }
    
    @Test
    public void test_releases() {
        Releases releases = getManager().moviesService().releases(TestData.MOVIE_ID);
        assertThat(releases).isNotNull();
        assertThat(releases.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(releases.countries.get(0).iso_3166_1).isEqualTo("US");
        assertThat(releases.countries.get(0).certification).isEqualTo("R");
        assertThat(releases.countries.get(0).release_date).isEqualTo("1999-10-14");
    }
    
    @Test
    public void test_videos() {
        Videos videos = getManager().moviesService().videos(TestData.MOVIE_ID);
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
    public void test_similar() {
        MovieResultsPage results = getManager().moviesService().similar(TestData.MOVIE_ID, 3, null);
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
    public void test_reviews() {
        ReviewResultsPage results = getManager().moviesService().reviews(49026, 1, null);
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
    public void test_lists() {
        ListResultsPage results = getManager().moviesService().lists(49026, 1, null);
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
    public void test_latest() {
        Movie movie = getManager().moviesService().latest();
        // Latest movie might not have a complete TMDb entry, but should at least some basic properties.
        assertThat(movie).isNotNull();
        assertThat(movie.id).isPositive();
        assertThat(movie.title).isNotEmpty();
    }
    
    @Test
    public void test_upcoming() {
        MovieResultsPage page = getManager().moviesService().upcoming(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_nowPlaying() {
        MovieResultsPage page = getManager().moviesService().nowPlaying(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_popular() {
        MovieResultsPage page = getManager().moviesService().popular(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    @Test
    public void test_topRated() {
        MovieResultsPage page = getManager().moviesService().topRated(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }
    
}
