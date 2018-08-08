package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;

import static com.uwetrottmann.tmdb2.TestData.testProductionCompany;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertBaseCompany;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class MovieAssertions {
    public static void assertBaseMovie(BaseMovie movie) {
        assertThat(movie).isNotNull();
        assertThat(movie.id).isNotNull();
        assertThat(movie.title).isNotNull();
        assertThat(movie.original_language).isNotEmpty();
        assertThat(movie.overview).isNotNull();
        assertThat(movie.adult).isNotNull();
        assertThat(movie.vote_average).isGreaterThanOrEqualTo(0);
        assertThat(movie.vote_count).isGreaterThanOrEqualTo(0);

        try {
            Movie _movie = (Movie) movie;
            assertThat(_movie.genres.get(0).id).isNotNull();
            assertThat(_movie.genres.get(0).name).isNotNull();
        } catch (Exception exc) {
            assertThat(movie.genre_ids).isNotNull();
        }
    }


    public static void assertMovie(Movie movie) {
        assertBaseMovie(movie);
        assertThat(movie.tagline).isNotEmpty();
        assertThat(movie.spoken_languages).isNotNull();
        assertThat(movie.homepage).isNotNull();
        assertThat(movie.budget).isNotNull();
        assertThat(movie.imdb_id).isNotNull();
        assertThat(movie.belongs_to_collection).isNotNull();
        assertThat(movie.belongs_to_collection.id).isNotNull();
        assertThat(movie.belongs_to_collection.name).isNotNull();
        assertThat(movie.production_countries).isNotNull();

        for (BaseCompany company : movie.production_companies) {
            assertBaseCompany(company, false);
        }

        assertThat(movie.revenue).isNotNull();
        assertThat(movie.status).isNotNull();
    }

    public static void assertMovieDataIntegrity(Movie movie) {
        assertMovie(movie);
        assertThat(movie.budget).isEqualTo(TestData.testMovie.budget);
        assertThat(movie.homepage).isEqualTo(TestData.testMovie.homepage);
        assertThat(movie.imdb_id).isEqualTo(TestData.testMovie.imdb_id);
        assertThat(movie.production_companies).isNotEmpty();
        assertThat(movie.production_companies.get(0).id).isEqualTo(testProductionCompany.id);
        assertThat(movie.production_companies.get(0).name).isEqualTo(testProductionCompany.name);
        assertThat(movie.production_countries).isNotEmpty();
        assertThat(movie.production_countries.get(0).iso_3166_1).isEqualTo("US");
        assertThat(movie.production_countries.get(0).name).isEqualTo("United States of America");
        assertThat(movie.revenue).isEqualTo(TestData.testMovie.revenue);
        assertThat(movie.runtime).isEqualTo(TestData.testMovie.runtime);
        assertThat(movie.spoken_languages.get(0).iso_639_1).isEqualTo("en");
        assertThat(movie.spoken_languages.get(0).name).isEqualTo("English");
        assertThat(movie.status).isEqualTo(TestData.testMovie.status);
        assertThat(movie.release_date).isEqualTo(TestData.testMovie.release_date);
        assertThat(movie.tagline).isEqualTo(TestData.testMovie.tagline);
        assertThat(movie.title).isEqualTo(TestData.testMovie.title);
        assertThat(movie.original_title).isEqualTo(TestData.testMovie.original_title);
        assertThat(movie.original_language).isEqualTo(TestData.testMovie.original_language);
        assertThat(movie.belongs_to_collection.name).isEqualTo(TestData.testCollection.name);
        assertThat(movie.belongs_to_collection.id).isEqualTo(TestData.testCollection.id);
        assertThat(movie.id).isEqualTo(TestData.testMovie.id);
    }

    public static void assertMovieReleaseDates(ReleaseDatesResults releaseDates) {
        assertThat(releaseDates).isNotNull();
        assertThat(releaseDates.results).isNotNull();
        assertThat(releaseDates.results).isNotEmpty();

        for (ReleaseDatesResult result : releaseDates.results) {
            assertThat(result.iso_3166_1).isNotNull();
            assertThat(result.release_dates).isNotNull();
            assertThat(result.release_dates).isNotEmpty();
            for (ReleaseDate releaseDate : result.release_dates) {
                assertThat(releaseDate.certification).isNotNull();
                assertThat(releaseDate.iso_639_1).isNotNull();
                assertThat(releaseDate.note).isNotNull();
                assertThat(releaseDate.release_date).isNotNull();
                assertThat(releaseDate.type).isNotNull();
            }
        }
    }

    public static void assertMovieExternalIds(MovieExternalIds externalIds) {
        assertThat(externalIds.imdb_id).isNotNull();
    }

    public static void assertMovieResultsPage(MovieResultsPage movieResultsPage) {
        assertBaseResultsPage(movieResultsPage);
        for (BaseMovie baseMovie : movieResultsPage.results) {
            assertBaseMovie(baseMovie);
        }
    }
}
