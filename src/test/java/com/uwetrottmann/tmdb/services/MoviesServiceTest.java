
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Movie;

public class MoviesServiceTest extends BaseTestCase {

    public void test_summary() {
        Movie movie = getManager().moviesService().summary(550).fire();
        assertNotNull("Result was null.", movie);
        assertNotNull("Adult was null.", movie.adult);
        assertEquals("Adult does not match.", false, movie.adult.booleanValue());
        assertNotNull("TMDB ID was null.", movie.id);
        assertEquals("Movie TMDB ID does not match.", 550, movie.id.intValue());
        assertEquals("Movie IMDB ID does not match.", "tt0137523", movie.imdb_id);
        assertEquals("Move title does not match.", "Fight Club", movie.title);
        assertNotNull("Runtime was null.", movie.runtime);
        assertEquals("Movie runtime does not match.", 139, movie.runtime.intValue());
        assertNotNull("Movie overview was null.", movie.overview);
        assertNotNull("Movie tagline was null.", movie.tagline);

    }
}
