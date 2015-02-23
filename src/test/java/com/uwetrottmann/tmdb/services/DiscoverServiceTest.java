/*
 * Copyright 2015 Miguel Teixeira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.AppendToDiscoverResponse;
import com.uwetrottmann.tmdb.entities.BaseResultsPage;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import com.uwetrottmann.tmdb.enumerations.SortBy;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscoverServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    @Test
    public void test_discover_movie() throws ParseException {
        MovieResultsPage results = getManager().discoverService().discoverMovie(false, true, null, 1,
                null, JSON_STRING_DATE.parse("1990-01-01"), null, JSON_STRING_DATE.parse("1990-01-01"), null,
                SortBy.POPULARITY_DESC, null, null, null, null, new AppendToDiscoverResponse(287),
                new AppendToDiscoverResponse(7467),
                null, new AppendToDiscoverResponse(10749), null, null, null);

        assertResultsPage(results);
        assertThat(results.results).isNotEmpty();
    }

    @Test
    public void test_discover_tv() throws ParseException {
        TvResultsPage results = getManager().discoverService().discoverTv(null, null,
                SortBy.VOTE_AVERAGE_DESC, null, null, null, new AppendToDiscoverResponse(18, 10765),
                new AppendToDiscoverResponse(49), JSON_STRING_DATE.parse("2010-01-01"),
                JSON_STRING_DATE.parse("2014-01-01"));

        assertResultsPage(results);
        assertThat(results.results).isNotEmpty();
    }

    private void assertResultsPage(BaseResultsPage results) {
        assertThat(results.page).isPositive();
        assertThat(results.total_pages).isPositive();
        assertThat(results.total_results).isPositive();
    }

}
