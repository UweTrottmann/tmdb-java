// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testMovieGenreAdventure;
import static com.uwetrottmann.tmdb2.assertions.GenreAssertions.assertGenres;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

public class GenresServiceTest extends BaseTestCase {

    @Test
    public void test_movie() throws IOException {
        Call<GenreResults> call = getUnauthenticatedInstance().genreService().movie(
                null
        );

        GenreResults results = call.execute().body();
        assertGenres(results);
    }

    @Test
    public void test_tv() throws IOException {
        Call<GenreResults> call = getUnauthenticatedInstance().genreService().tv(
                null
        );
        GenreResults results = call.execute().body();
        assertGenres(results);
    }

    @Test
    public void test_movies() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().genreService().movies(
                testMovieGenreAdventure.id,
                null,
                null,
                null
        );

        MovieResultsPage results = call.execute().body();

        assertMovieResultsPage(results);
    }
}
