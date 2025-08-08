// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Trending;
import com.uwetrottmann.tmdb2.entities.TrendingResultsPage;

import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertBaseMovie;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertBasePerson;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertBaseTvShow;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

public class TrendingAssertions {
    public static void assertTrending(Trending trending) {
        assertThat(trending).isNotNull();
        switch (trending.media_type) {
            case MOVIE:
                BaseMovie movie = trending.movie;
                assertBaseMovie(movie);
                break;
            case TV:
                BaseTvShow tv = trending.tvShow;
                assertBaseTvShow(tv);
                break;
            case PERSON:
                BasePerson person = trending.person;
                assertBasePerson(person);
                break;
            default:
                Assert.fail("Unknown Media type");

        }
    }

    public static void assertTrendingResultsPage(TrendingResultsPage trendingResultsPage) {
        assertBaseResultsPage(trendingResultsPage);
        for (Trending trending : trendingResultsPage.results) {
            assertTrending(trending);
        }
    }
}
