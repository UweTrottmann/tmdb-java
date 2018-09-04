package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testMovieGenreAdventure;
import static com.uwetrottmann.tmdb2.assertions.GenreAssertions.assertGenres;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class GenresServiceTest extends BaseTestCase {

    @Test
    public void test_movie() throws IOException {
        Observable<GenreResults> call = getUnauthenticatedInstance().rx.genreService().movie(
                null
        );

        GenreResults results = call.singleOrError().blockingGet();
        assertGenres(results);
    }

    @Test
    public void test_tv() throws IOException {
        Observable<GenreResults> call = getUnauthenticatedInstance().rx.genreService().tv(
                null
        );
        GenreResults results = call.singleOrError().blockingGet();
        assertGenres(results);
    }

    @Test
    public void test_movies() throws IOException {
        Observable<MovieResultsPage> call = getUnauthenticatedInstance().rx.genreService().movies(
                testMovieGenreAdventure.id,
                null,
                null,
                null
        );

        MovieResultsPage results = call.singleOrError().blockingGet();

        assertMovieResultsPage(results);
    }
}
