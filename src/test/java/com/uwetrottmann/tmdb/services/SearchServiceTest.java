
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.MovieResultsPage;
import com.uwetrottmann.tmdb.entities.TvResultsPage;

import java.text.ParseException;

import static org.fest.assertions.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    public void test_movieSearch() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie("Fight Club");
        assertNotNull("Result was null.", movieResults);
        assertNotNull("Returned movie list was null.", movieResults.results);
        assertNotNull("Returned page number was null.", movieResults.page);
        assertNotNull("Total pages number was null.", movieResults.total_pages);
        assertNotNull("Total results number was null.", movieResults.total_results);
    }

    public void test_movieSearchWithNullParams() throws ParseException {
        MovieResultsPage movieResults = getManager().searchService().movie("Fight Club", null, null,
                null, null, null, null);
        assertNotNull("Result was null.", movieResults);
        assertNotNull("Returned movie list was null.", movieResults.results);
        assertNotNull("Returned page number was null.", movieResults.page);
        assertNotNull("Total pages number was null.", movieResults.total_pages);
        assertNotNull("Total results number was null.", movieResults.total_results);
    }
    
    public void test_tv() {
        TvResultsPage results = getManager().searchService().tv("Breaking Bad", null, null, null, null);
        assertThat(results.results).isNotEmpty();
        assertThat(results.results.get(0).name).isEqualTo("Breaking Bad");
    }

}
