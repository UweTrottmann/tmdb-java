
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Movie;
import com.uwetrottmann.tmdb.entities.ResultsPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SearchServiceTest extends BaseTestCase {

    public void test_movieSearch() throws ParseException {
        ResultsPage movieResults = getManager().searchService().movieSearch("Fight Club").fire();
        assertNotNull("Result was null.", movieResults);
        assertNotNull("Returned movie list was null.", movieResults.results);
        assertNotNull("Returned page number was null.", movieResults.page);
        assertNotNull("Total pages number was null.", movieResults.total_pages);
        assertNotNull("Total results number was null.", movieResults.total_results);
    }
}
