package com.uwetrottmann.tmdb2.services.rx;

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
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class FindServiceTest extends BaseTestCase {

    @Test
    public void test_find_movie() throws IOException {
        Observable<FindResults> call = getUnauthenticatedInstance().rx.findService().find(
                testMovie.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.singleOrError().blockingGet();

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
        Observable<FindResults> call = getUnauthenticatedInstance().rx.findService().find(
                testPerson.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.singleOrError().blockingGet();

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
        Observable<FindResults> call = getUnauthenticatedInstance().rx.findService().find(
                testTvShow.external_ids.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.singleOrError().blockingGet();

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
        Observable<FindResults> call = getUnauthenticatedInstance().rx.findService().find(
                testTvSeason.external_ids.tvdb_id,
                ExternalSource.TVDB_ID,
                null
        );

        FindResults results = call.singleOrError().blockingGet();

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
        Observable<FindResults> call = getUnauthenticatedInstance().rx.findService().find(
                testTvEpisode.external_ids.imdb_id,
                ExternalSource.IMDB_ID,
                null
        );

        FindResults results = call.singleOrError().blockingGet();
        assertThat(results).isNotNull();

        assertThat(results.tv_episode_results).isNotNull();
        assertThat(results.tv_episode_results).isNotEmpty();
        for (BaseTvEpisode tvEpisode : results.tv_episode_results) {
            assertThat(tvEpisode.id).isEqualTo(testTvEpisode.id);
        }
    }

}
