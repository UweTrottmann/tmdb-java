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

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    @Test
    public void test_companySearch() throws ParseException {
        CompanyResultsPage companyResults = getManager().searchService().company("Sony Pictures", null);
        
        assertResultsPage(companyResults);
        assertThat(companyResults.results).isNotEmpty();
        assertThat(companyResults.results.get(0).id).isNotNull();
        assertThat(companyResults.results.get(0)).isNotNull();
        assertThat(companyResults.results.get(0).logo_path).isNotNull();
    }
    
    @Test
    public void test_collectionSearch() throws ParseException {
        CollectionResultsPage collectionResults = getManager().searchService().collection("The Avengers Collection",
                null, null);
        
        assertResultsPage(collectionResults);
        assertThat(collectionResults.results).isNotEmpty();
        assertThat(collectionResults.results.get(0).id).isNotNull();
        assertThat(collectionResults.results.get(0).backdrop_path).isNotNull();
        assertThat(collectionResults.results.get(0).name).isNotNull();
        assertThat(collectionResults.results.get(0).poster_path).isNotNull();
    }
    
    @Test
    public void test_keywordSearch() throws ParseException {
        KeywordResultsPage keywordResults = getManager().searchService().keyword("fight", null);
        
        assertResultsPage(keywordResults);
        assertThat(keywordResults.results).isNotEmpty();
        assertThat(keywordResults.results.get(0).id).isNotNull();
        assertThat(keywordResults.results.get(0).name).isNotNull();
    }
    
    @Test
    public void test_movieSearch() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie(TestData.MOVIE_TITLE, null, null,
                null, null, null, null);
        
        assertResultsPage(movieResults);
        assertThat(movieResults.results).isNotEmpty();
    }
    
    @Test
    public void test_personSearch() throws ParseException {
        PersonResultsPage movieResults = getManager().searchService().person(TestData.PERSON_NAME, null, null, null);
        
        assertResultsPage(movieResults);
        assertThat(movieResults.results.get(0).id).isNotNull();
        assertThat(movieResults.results.get(0).name).isNotNull();
        assertThat(movieResults.results.get(0).popularity).isNotNull();
        assertThat(movieResults.results.get(0).profile_path).isNotNull();
        assertThat(movieResults.results.get(0).adult).isNotNull();
        
        for (Media media : movieResults.results.get(0).known_for) {
            assertThat(media.adult).isNotNull();
            assertThat(media.backdrop_path).isNotNull();
            assertThat(media.id).isNotNull();
            assertThat(media.original_title).isNotNull();
            assertThat(media.release_date).isNotNull();
            assertThat(media.poster_path).isNotNull();
            assertThat(media.popularity).isNotNull().isGreaterThan(0);
            assertThat(media.title).isNotNull();
            assertThat(media.vote_average).isNotNull().isGreaterThan(0);
            assertThat(media.vote_count).isNotNull().isGreaterThan(0);
            assertThat(media.media_type).isNotNull();
        }
        
    }

    @Test
    public void test_tv() {
        TvResultsPage tvResults = getManager().searchService().tv(TestData.TVSHOW_TITLE, null, null, null, null);
        
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
