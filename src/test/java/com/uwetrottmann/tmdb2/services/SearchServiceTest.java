// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testCollection;
import static com.uwetrottmann.tmdb2.TestData.testCompany;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TestData.testPerson;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.CollectionAssertions.assertCollectionResultsPage;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertCompanyResultsPage;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertKeywordResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MediaAssertions.assertMediaResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.CollectionResultsPage;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb2.entities.MediaResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class SearchServiceTest extends BaseTestCase {

    @Test
    public void test_companySearch() throws IOException {
        Call<CompanyResultsPage> call = getUnauthenticatedInstance().searchService().company(
                testCompany.name,
                null
        );

        CompanyResultsPage companyResults = call.execute().body();

        assertCompanyResultsPage(companyResults);
    }

    @Test
    public void test_collectionSearch() throws IOException {
        Call<CollectionResultsPage> call = getUnauthenticatedInstance().searchService().collection(
                testCollection.name,
                null,
                null
        );
        CollectionResultsPage collectionResults = call.execute().body();

        assertCollectionResultsPage(collectionResults);
    }

    @Test
    public void test_keywordSearch() throws IOException {
        Call<KeywordResultsPage> call = getUnauthenticatedInstance().searchService().keyword(
                "fight",
                null
        );
        KeywordResultsPage keywordResults = call.execute().body();

        assertKeywordResultsPage(keywordResults);
    }

    @Test
    public void test_movieSearch() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().searchService().movie(
                testMovie.title,
                null,
                null,
                null,
                null,
                null,
                null
        );

        MovieResultsPage movieResults = call.execute().body();

        assertMovieResultsPage(movieResults);
    }

    @Test
    public void test_personSearch() throws IOException {
        Call<PersonResultsPage> call = getUnauthenticatedInstance().searchService().person(
                testPerson.name,
                null,
                null,
                null,
                null
        );
        PersonResultsPage personResults = call.execute().body();

        assertPersonResultsPage(personResults);
    }

    @Test
    public void test_tv() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().searchService().tv(
                testTvShow.name,
                null,
                null,
                null,
                null
        );

        TvShowResultsPage tvResults = call.execute().body();

        assertTvShowResultsPage(tvResults);
    }

    @Test
    public void test_multiSearch() throws IOException {
        Call<MediaResultsPage> call = getUnauthenticatedInstance().searchService().multi(
                "snowden",
                null,
                null,
                null,
                null
        );

        MediaResultsPage mediaResultsPage = call.execute().body();

        assertMediaResultsPage(mediaResultsPage);
    }
}
