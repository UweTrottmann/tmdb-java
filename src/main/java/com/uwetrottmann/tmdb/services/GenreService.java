
package com.uwetrottmann.tmdb.services;

import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TmdbApiBuilder;
import com.uwetrottmann.tmdb.TmdbApiService;
import com.uwetrottmann.tmdb.entities.Genre;

import java.util.List;

public class GenreService extends TmdbApiService {
    public MoviesBuilder movies() {
        return new MoviesBuilder(this);
    }

    public ShowsBuilder shows() {
        return new ShowsBuilder(this);
    }

    public static final class MoviesBuilder extends TmdbApiBuilder<List<Genre>> {
        private static final String URI = "/genres/movies.json/" + FIELD_API_KEY;

        private MoviesBuilder(GenreService service) {
            super(service, new TypeToken<List<Genre>>() {
            }, URI);
        }
    }

    public static final class ShowsBuilder extends TmdbApiBuilder<List<Genre>> {
        private static final String URI = "/genres/shows.json/" + FIELD_API_KEY;

        private ShowsBuilder(GenreService service) {
            super(service, new TypeToken<List<Genre>>() {
            }, URI);
        }
    }
}
