package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import com.uwetrottmann.tmdb2.entities.CollectionResultsPage;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    @Test
    public void test_companySearch() throws IOException {
        Call<CompanyResultsPage> call = getManager().searchService().company("Sony Pictures", null);
        CompanyResultsPage companyResults = call.execute().body();

        assertResultsPage(companyResults);
        assertThat(companyResults.results).isNotEmpty();
        assertThat(companyResults.results.get(0).id).isNotNull();
        assertThat(companyResults.results.get(0)).isNotNull();
        assertThat(companyResults.results.get(0).logo_path).isNotNull();
    }

    @Test
    public void test_collectionSearch() throws IOException {
        Call<CollectionResultsPage> call = getManager().searchService().collection("The Avengers Collection", null,
                null);
        CollectionResultsPage collectionResults = call.execute().body();

        assertResultsPage(collectionResults);
        assertThat(collectionResults.results).isNotEmpty();
        assertThat(collectionResults.results.get(0).id).isNotNull();
        assertThat(collectionResults.results.get(0).backdrop_path).isNotNull();
        assertThat(collectionResults.results.get(0).name).isNotNull();
        assertThat(collectionResults.results.get(0).poster_path).isNotNull();
    }

    @Test
    public void test_keywordSearch() throws IOException {
        Call<KeywordResultsPage> call = getManager().searchService().keyword("fight", null);
        KeywordResultsPage keywordResults = call.execute().body();

        assertResultsPage(keywordResults);
        assertThat(keywordResults.results).isNotEmpty();
        assertThat(keywordResults.results.get(0).id).isNotNull();
        assertThat(keywordResults.results.get(0).name).isNotNull();
    }

    @Test
    public void test_movieSearch() throws IOException {
        Call<MovieResultsPage> call = getManager().searchService().movie(TestData.MOVIE_TITLE, null, null, null, null,
                null, null);
        MovieResultsPage movieResults = call.execute().body();

        assertResultsPage(movieResults);
        assertThat(movieResults.results).isNotEmpty();
    }

    @Test
    public void test_personSearch() throws IOException {
        Call<PersonResultsPage> call = getManager().searchService().person(TestData.PERSON_NAME, null, null, null);
        PersonResultsPage movieResults = call.execute().body();

        assertResultsPage(movieResults);
        assertThat(movieResults.results.get(0).id).isNotNull();
        assertThat(movieResults.results.get(0).name).isNotNull();
        assertThat(movieResults.results.get(0).popularity).isNotNull();
        assertThat(movieResults.results.get(0).profile_path).isNotNull();
        assertThat(movieResults.results.get(0).adult).isNotNull();
        assertMedia(movieResults.results.get(0).known_for);
    }

    @Test
    public void test_tv() throws IOException {
        Call<TvResultsPage> call = getManager().searchService().tv(TestData.TVSHOW_TITLE, null, null, null, null);
        TvResultsPage tvResults = call.execute().body();

        assertResultsPage(tvResults);
        assertThat(tvResults.results).isNotEmpty();
        assertThat(tvResults.results.get(0).name).isEqualTo(TestData.TVSHOW_TITLE);
    }

    private void assertResultsPage(BaseResultsPage results) {
        assertThat(results.page).isPositive();
        assertThat(results.total_pages).isPositive();
        assertThat(results.total_results).isPositive();
    }

}
