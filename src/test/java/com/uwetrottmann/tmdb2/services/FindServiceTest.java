package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.FindResults;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.enumerations.ExternalSource;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindServiceTest extends BaseTestCase {

    @Test
    public void test_find_movie() {
        FindResults results = getManager().findService().find("tt0361748", ExternalSource.IMDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.movie_results).isNotEmpty();

        Movie movie = results.movie_results.get(0);
        assertThat(movie).isNotNull();
        assertThat(movie.adult).isFalse();
        assertThat(movie.backdrop_path).isNotNull();
        assertThat(movie.id).isNotNull();
        assertThat(movie.original_title).isEqualTo("Inglourious Basterds");
        assertThat(movie.release_date).isEqualTo("2009-08-19");
        assertThat(movie.poster_path).isNotNull();
        assertThat(movie.popularity).isNotNull();
        assertThat(movie.title).isEqualTo("Inglourious Basterds");
        assertThat(movie.vote_average).isGreaterThanOrEqualTo(0);
        assertThat(movie.vote_average).isGreaterThanOrEqualTo(0);
    }
    
    @Test
    public void test_find_people() {
        FindResults results = getManager().findService().find("nm0000093", ExternalSource.IMDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.person_results).isNotEmpty();

        Person person = results.person_results.get(0);
        assertThat(person).isNotNull();
        assertThat(person.id).isNotNull();
        assertThat(person.name).isEqualTo(TestData.PERSON_NAME);
        assertThat(person.profile_path).isNotNull();
    }
    
    @Test
    public void test_find_tv_show() {
        FindResults results = getManager().findService().find("tt0903747", ExternalSource.IMDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.tv_results).isNotEmpty();

        TvShow show = results.tv_results.get(0);
        assertThat(show).isNotNull();
        assertThat(show.id).isNotNull();
        assertThat(show.original_name).isEqualTo("Breaking Bad");
        assertThat(show.name).isEqualTo("Breaking Bad");
        assertThat(show.first_air_date).isNotNull();
        assertThat(show.backdrop_path).isNotNull();
        assertThat(show.poster_path).isNotNull();
        assertThat(show.popularity).isGreaterThanOrEqualTo(0);
        assertThat(show.vote_average).isGreaterThanOrEqualTo(0);
        assertThat(show.vote_count).isGreaterThanOrEqualTo(0);
    }
    
    @Test
    public void test_find_tv_season() {
        FindResults results = getManager().findService().find("30272", ExternalSource.TVDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.tv_season_results).isNotEmpty();

        TvSeason season = results.tv_season_results.get(0);
        assertThat(season).isNotNull();
        assertThat(season.air_date).isNotNull();
        assertThat(season.name).isEqualTo("Season 1");
        assertThat(season.id).isNotNull();
        assertThat(season.poster_path).isNotEmpty();
        assertThat(season.season_number).isEqualTo(1);
    }
    
    @Test
    public void test_find_tv_episode() {
        FindResults results = getManager().findService().find("tt0959621", ExternalSource.IMDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.tv_episode_results).isNotEmpty();

        TvEpisode episode = results.tv_episode_results.get(0);
        assertThat(episode).isNotNull();
        assertThat(episode.air_date).isNotNull();
        assertThat(episode.episode_number).isPositive();
        assertThat(episode.name).isNotNull();
        assertThat(episode.id).isNotNull();
        assertThat(episode.season_number).isEqualTo(1);
        assertThat(episode.still_path).isNotNull();
        assertThat(episode.vote_average).isGreaterThanOrEqualTo(0);
        assertThat(episode.vote_count).isGreaterThanOrEqualTo(0);
    }

}
