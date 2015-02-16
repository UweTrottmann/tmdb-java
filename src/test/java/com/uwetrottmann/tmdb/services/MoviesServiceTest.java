
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.AppendToResponse;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.Trailers;
import com.uwetrottmann.tmdb.enumerations.AppendToResponseItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    public void test_summary() throws ParseException {
        Movie movie = getManager().moviesService().summary(550, null, null);
        assertNotNull("Result was null.", movie);
        assertNotNull("Movie Adult was null.", movie.adult);
        assertEquals("Movie Adult does not match.", false, movie.adult.booleanValue());
        assertNotNull("Movie backdrop path was null.", movie.backdrop_path);
        assertNotNull("Movie budget was null.", movie.budget);
        assertEquals("Movie budget does not match.", 63000000, movie.budget.intValue());
        assertNotNull("Movie TMDB ID was null.", movie.id);
        assertEquals("Movie TMDB ID does not match.", 550, movie.id.intValue());
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

    public void test_summary_append_trailers() {
        Movie movie = getManager().moviesService().summary(550,
                null,
                new AppendToResponse(
                    AppendToResponseItem.TRAILERS));

        assertNotNull(movie.trailers);
    }

    public void test_summary_append_credits() {
        Movie movie = getManager().moviesService().summary(550,
                null,
                new AppendToResponse(
                        AppendToResponseItem.CREDITS));

        assertNotNull(movie.credits);
    }

    public void test_summary_append_releases() {
        Movie movie = getManager().moviesService().summary(550,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASES));

        assertNotNull(movie.releases);
    }

    public void test_summary_append_all() {
        Movie movie = getManager().moviesService().summary(550,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.TRAILERS));

        assertNotNull(movie.releases);
        assertNotNull(movie.credits);
        assertNotNull(movie.trailers);
    }

    public void test_trailers() {
        Trailers trailers = getManager().moviesService().trailers(550);
        assertThat(trailers).isNotNull();
        assertThat(trailers.id).isEqualTo(550);
        assertThat(trailers.quicktime).isNotNull();
        assertThat(trailers.youtube).isNotNull();
    }

    public void test_credits() {
        Credits credits = getManager().moviesService().credits(550);
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(550);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.cast.get(0).name).isEqualTo("Edward Norton");
        assertThat(credits.crew).isNotEmpty();
    }

    public void test_nowPlaying() {
        MovieResultsPage page = getManager().moviesService().nowPlaying(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    public void test_popular() {
        MovieResultsPage page = getManager().moviesService().popular(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    public void test_similar() {
        MovieResultsPage page = getManager().moviesService().similar(550, null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    public void test_topRated() {
        MovieResultsPage page = getManager().moviesService().topRated(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }

    public void test_upcoming() {
        MovieResultsPage page = getManager().moviesService().upcoming(null, null);
        assertThat(page).isNotNull();
        assertThat(page.results).isNotEmpty();
    }
}
