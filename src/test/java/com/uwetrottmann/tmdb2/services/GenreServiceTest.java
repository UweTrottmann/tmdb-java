package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreServiceTest extends BaseTestCase {

    @Test
    public void test_movie() throws IOException {
        Call<GenreResults> call = getManager().genreService().movie(null);
        GenreResults results = call.execute().body();
        assertGenres(results);
    }

    @Test
    public void test_tv() throws IOException {
        Call<GenreResults> call = getManager().genreService().tv(null);
        GenreResults results = call.execute().body();
        assertGenres(results);
    }

    private static void assertGenres(GenreResults results) {
        assertThat(results).isNotNull();
        assertThat(results.genres).isNotEmpty();
        for (Genre genre : results.genres) {
            assertThat(genre.id).isGreaterThan(0);
            assertThat(genre.name).isNotEmpty();
        }
    }

}
