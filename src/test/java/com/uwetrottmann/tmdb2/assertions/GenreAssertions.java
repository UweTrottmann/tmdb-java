package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.GenreResults;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreAssertions {
    public static void assertGenres(GenreResults results) {
        assertThat(results).isNotNull();

        assertThat(results.genres).isNotNull();
        assertThat(results.genres).isNotEmpty();

        for (Genre genre : results.genres) {
            assertThat(genre.id).isGreaterThan(0);
            assertThat(genre.name).isNotEmpty();
        }
    }

    public static void assertGenre(Genre genre) {
        assertThat(genre).isNotNull();
        assertThat(genre.name).isNotNull();
        assertThat(genre.name).isNotEmpty();
        assertThat(genre.id).isNotNull();
        assertThat(genre.id).isGreaterThanOrEqualTo(0);
    }
}
