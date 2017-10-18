package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.ChangeResultsPage;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testMovieChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testMovieChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertChangeResultsPage;

public class ChangesServiceTest extends BaseTestCase {

    @Test
    public void test_movie_changes() throws IOException {
        Call<ChangeResultsPage> call = getUnauthenticatedInstance().changesService().movie(
                testMovieChangesStartDate,
                testMovieChangesEndDate
        );

        ChangeResultsPage changeResultsPage = call.execute().body();

        assertChangeResultsPage(changeResultsPage);
    }

    @Test
    public void test_tv_changes() throws IOException {
        Call<ChangeResultsPage> call = getUnauthenticatedInstance().changesService().tv(
                testTvShowChangesStartDate,
                testTvShowChangesEndDate
        );

        ChangeResultsPage changeResultsPage = call.execute().body();

        assertChangeResultsPage(changeResultsPage);
    }

    @Test
    public void test_person_changes() throws IOException {
        Call<ChangeResultsPage> call = getUnauthenticatedInstance().changesService().person(
                testPersonChangesStartDate,
                testPersonChangesEndDate
        );

        ChangeResultsPage changeResultsPage = call.execute().body();

        assertChangeResultsPage(changeResultsPage);
    }
}
