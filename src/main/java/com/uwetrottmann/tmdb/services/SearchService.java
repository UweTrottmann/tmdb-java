
package com.uwetrottmann.tmdb.services;

import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TmdbApiBuilder;
import com.uwetrottmann.tmdb.TmdbApiService;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.TvEntity;
import com.uwetrottmann.tmdb.entities.TvShow;
import com.uwetrottmann.tmdb.entities.UserProfile;

import java.util.List;

public class SearchService extends TmdbApiService {
    /**
     * Search for TV show episodes.
     * 
     * @param query The search query that should be used.
     * @return Builder instance.
     */
    public EpisodesBuilder episodes(String query) {
        return new EpisodesBuilder(this, query);
    }

    /**
     * Search for movies.
     * 
     * @param query The search query that should be used.
     * @return Builder instance.
     */
    public MoviesBuilder movies(String query) {
        return new MoviesBuilder(this, query);
    }

    /**
     * Search for people including actors, directors, producers, and writers.
     * 
     * @param query The search query that should be used.
     * @return Builder instance.
     */
    public PeopleBuilder people(String query) {
        return new PeopleBuilder(this, query);
    }

    /**
     * Search for TV shows.
     * 
     * @param query The search query that should be used.
     * @return Builder instance.
     */
    public ShowsBuilder shows(String query) {
        return new ShowsBuilder(this, query);
    }

    /**
     * Search for Trakt users.
     * 
     * @param query The search query that should be used.
     * @return Builder instance.
     */
    public UsersBuilder users(String query) {
        return new UsersBuilder(this, query);
    }

    public static final class EpisodesBuilder extends TmdbApiBuilder<List<TvEntity>> {
        private static final String URI = "/search/episodes.json/" + FIELD_API_KEY + "/"
                + FIELD_QUERY;

        private EpisodesBuilder(SearchService service, String query) {
            super(service, new TypeToken<List<TvEntity>>() {
            }, URI);

            this.field(FIELD_QUERY, query);
        }
    }

    public static final class MoviesBuilder extends TmdbApiBuilder<List<Movie>> {
        private static final String URI = "/search/movies.json/" + FIELD_API_KEY + "/"
                + FIELD_QUERY;

        private MoviesBuilder(SearchService service, String query) {
            super(service, new TypeToken<List<Movie>>() {
            }, URI);

            this.field(FIELD_QUERY, query);
        }
    }

    public static final class PeopleBuilder extends TmdbApiBuilder<List<Person>> {
        private static final String URI = "/search/people.json/" + FIELD_API_KEY + "/"
                + FIELD_QUERY;

        private PeopleBuilder(SearchService service, String query) {
            super(service, new TypeToken<List<Person>>() {
            }, URI);

            this.field(FIELD_QUERY, query);
        }
    }

    public static final class ShowsBuilder extends TmdbApiBuilder<List<TvShow>> {
        private static final String URI = "/search/shows.json/" + FIELD_API_KEY + "/" + FIELD_QUERY;

        private ShowsBuilder(SearchService service, String query) {
            super(service, new TypeToken<List<TvShow>>() {
            }, URI);

            this.field(FIELD_QUERY, query);
        }
    }

    public static final class UsersBuilder extends TmdbApiBuilder<List<UserProfile>> {
        private static final String URI = "/search/users.json/" + FIELD_API_KEY + "/" + FIELD_QUERY;

        private UsersBuilder(SearchService service, String query) {
            super(service, new TypeToken<List<UserProfile>>() {
            }, URI);

            this.field(FIELD_QUERY, query);
        }
    }
}
