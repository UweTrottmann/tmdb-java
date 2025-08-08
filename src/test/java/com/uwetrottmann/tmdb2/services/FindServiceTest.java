// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testPerson;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertBaseMovie;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertBasePerson;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertBaseTvSeason;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertBaseTvShow;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvEpisode;
import com.uwetrottmann.tmdb2.entities.BaseTvSeason;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.FindResults;
import com.uwetrottmann.tmdb2.enumerations.ExternalSource;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class FindServiceTest extends BaseTestCase {

    @Test
    public void test_find_no_results() throws IOException {
        FindResults noResults = getUnauthenticatedInstance().findService()
                .find(1, ExternalSource.TVDB_ID, null)
                .execute()
                .body();
        assertThat(noResults).isNotNull();
        assertThat(noResults.movie_results).isNotNull();
        assertThat(noResults.person_results).isNotNull();
        assertThat(noResults.tv_results).isNotNull();
        assertThat(noResults.tv_episode_results).isNotNull();
        assertThat(noResults.tv_season_results).isNotNull();
    }

    @Test
    public void test_find_movie() throws IOException {
        Call<FindResults> call = getUnauthenticatedInstance().findService().find(
                testMovie.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.execute().body();

        assertThat(results).isNotNull();

        assertThat(results.movie_results).isNotNull();
        assertThat(results.movie_results).isNotEmpty();
        for (BaseMovie movie : results.movie_results) {
            assertBaseMovie(movie);
            assertThat(movie.title).isEqualTo(testMovie.title);
        }
    }

    @Test
    public void test_find_people() throws IOException {
        Call<FindResults> call = getUnauthenticatedInstance().findService().find(
                testPerson.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.execute().body();

        assertThat(results).isNotNull();

        assertThat(results.person_results).isNotNull();
        assertThat(results.person_results).isNotEmpty();
        for (BasePerson person : results.person_results) {
            assertBasePerson(person);
            assertThat(person.name).isEqualTo(testPerson.name);
        }

    }

    @Test
    public void test_find_tv_show() throws IOException {
        Call<FindResults> call = getUnauthenticatedInstance().findService().find(
                testTvShow.external_ids.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.execute().body();

        assertThat(results).isNotNull();

        assertThat(results.tv_results).isNotNull();
        assertThat(results.tv_results).isNotEmpty();
        for (BaseTvShow tvShow : results.tv_results) {
            assertBaseTvShow(tvShow);
            assertThat(tvShow.name).isEqualTo(testTvShow.name);
        }
    }

    @Test
    public void test_find_tv_season() throws IOException {
        Call<FindResults> call = getUnauthenticatedInstance().findService().find(
                testTvSeason.external_ids.tvdb_id,
                ExternalSource.TVDB_ID,
                null
        );

        FindResults results = call.execute().body();

        assertThat(results).isNotNull();

        assertThat(results.tv_season_results).isNotNull();
        assertThat(results.tv_season_results).isNotEmpty();
        for (BaseTvSeason tvSeason : results.tv_season_results) {
            assertBaseTvSeason(tvSeason);
            assertThat(tvSeason.id).isEqualTo(testTvSeason.id);
        }
    }

    @Test
    public void test_find_tv_episode() throws IOException {
        Call<FindResults> call = getUnauthenticatedInstance().findService().find(
                testTvEpisode.external_ids.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.execute().body();
        assertThat(results).isNotNull();

        assertThat(results.tv_episode_results).isNotNull();
        assertThat(results.tv_episode_results).isNotEmpty();
        for (BaseTvEpisode tvEpisode : results.tv_episode_results) {
            assertThat(tvEpisode.id).isEqualTo(testTvEpisode.id);
        }
    }

}
