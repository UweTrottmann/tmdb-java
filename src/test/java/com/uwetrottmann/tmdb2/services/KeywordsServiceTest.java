// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Keyword;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testKeyword;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertBaseKeyword;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertKeywordDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

public class KeywordsServiceTest extends BaseTestCase {

    @Test
    public void test_keyword() throws IOException {
        Call<Keyword> call = getUnauthenticatedInstance().keywordsService().summary(
                testKeyword.id
        );

        Keyword keyword = call.execute().body();

        assertKeywordDataIntegrity(keyword);
    }

    @Test
    public void test_keyword_append_movies() throws IOException {
        Call<Keyword> call = getUnauthenticatedInstance().keywordsService().summary(
                testKeyword.id,
                new AppendToResponse(
                        AppendToResponseItem.MOVIES
                )
        );

        Keyword keyword = call.execute().body();

        assertBaseKeyword(keyword);

        assertMovieResultsPage(keyword.movies);
    }

    @Test
    public void test__movies() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().keywordsService().movies(
                testKeyword.id
        );

        MovieResultsPage movieResultsPage = call.execute().body();

        assertMovieResultsPage(movieResultsPage);
    }
}
