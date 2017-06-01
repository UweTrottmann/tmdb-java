package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.MediaResultsPage;
import org.junit.Assert;

import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertBaseMovie;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertBasePerson;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertBaseTvShow;
import static org.assertj.core.api.Assertions.assertThat;

public class MediaAssertions {
    public static void assertMedia(Media media) {
        assertThat(media).isNotNull();
        switch (media.media_type) {
            case MOVIE:
                BaseMovie movie = media.movie;
                assertBaseMovie(movie);
                break;
            case TV:
                BaseTvShow tv = media.tvShow;
                assertBaseTvShow(tv);
                break;
            case PERSON:
                BasePerson person = media.person;
                assertBasePerson(person);
                break;
            default:
                Assert.fail("Unknown Media type");

        }
    }

    public static void assertMediaResultsPage(MediaResultsPage mediaResultsPage) {
        assertBaseResultsPage(mediaResultsPage);
        for (Media media : mediaResultsPage.results) {
            assertMedia(media);
        }
    }
}
