package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseCollection;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.CollectionResultsPage;

import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertBaseMovie;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionAssertions {
    public static void assertCollectionResultsPage(CollectionResultsPage collectionResultsPage) {
        assertBaseResultsPage(collectionResultsPage);

        for (BaseCollection collection : collectionResultsPage.results) {
            assertBaseCollection(collection);
        }
    }


    public static void assertBaseCollection(BaseCollection collection) {
        assertThat(collection).isNotNull();
        assertThat(collection.id).isNotNull();
        assertThat(collection.backdrop_path).isNotNull();
        assertThat(collection.poster_path).isNotNull();
        assertThat(collection.name).isNotNull();
    }

    public static void assertCollection(Collection collection) {

        assertBaseCollection(collection);

        assertThat(collection.overview).isNotNull();

        assertThat(collection.parts).isNotNull();
        assertThat(collection.parts).isNotEmpty();
        for (BaseMovie movie : collection.parts) {
            assertBaseMovie(movie);
        }
    }

}
