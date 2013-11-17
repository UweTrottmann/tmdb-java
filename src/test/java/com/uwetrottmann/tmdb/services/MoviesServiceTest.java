
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MoviesServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    public void test_summary() throws ParseException {
        Movie movie = getManager().moviesService().summary(550);
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
}
