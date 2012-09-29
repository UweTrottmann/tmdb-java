package com.uwetrottmann.tmdb.services;

import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TraktApiBuilder;
import com.uwetrottmann.tmdb.TraktApiService;
import com.uwetrottmann.tmdb.entities.Genre;

public class GenreService extends TraktApiService {
    public MoviesBuilder movies() {
        return new MoviesBuilder(this);
    }

    public ShowsBuilder shows() {
        return new ShowsBuilder(this);
    }

    public static final class MoviesBuilder extends TraktApiBuilder<List<Genre>> {
        private static final String URI = "/genres/movies.json/" + FIELD_API_KEY;

        private MoviesBuilder(GenreService service) {
            super(service, new TypeToken<List<Genre>>() {}, URI);
        }
    }
    public static final class ShowsBuilder extends TraktApiBuilder<List<Genre>> {
        private static final String URI = "/genres/shows.json/" + FIELD_API_KEY;

        private ShowsBuilder(GenreService service) {
            super(service, new TypeToken<List<Genre>>() {}, URI);
        }
    }
}
