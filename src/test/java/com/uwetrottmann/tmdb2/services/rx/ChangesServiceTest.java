package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testMovieChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testMovieChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertChangeResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.ChangeResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class ChangesServiceTest extends BaseTestCase {

    @Test
    public void test_movie_changes() throws IOException {
        Observable<ChangeResultsPage> call = getUnauthenticatedInstance().rx.changesService().movie(
                new TmdbDate(testMovieChangesStartDate),
                new TmdbDate(testMovieChangesEndDate)
        );

        ChangeResultsPage changeResultsPage = call.singleOrError().blockingGet();

        assertChangeResultsPage(changeResultsPage);
    }

    @Test
    public void test_tv_changes() throws IOException {
        Observable<ChangeResultsPage> call = getUnauthenticatedInstance().rx.changesService().tv(
                new TmdbDate(testTvShowChangesStartDate),
                new TmdbDate(testTvShowChangesEndDate)
        );

        ChangeResultsPage changeResultsPage = call.singleOrError().blockingGet();

        assertChangeResultsPage(changeResultsPage);
    }

    @Test
    public void test_person_changes() throws IOException {
        Observable<ChangeResultsPage> call = getUnauthenticatedInstance().rx.changesService().person(
                new TmdbDate(testPersonChangesStartDate),
                new TmdbDate(testPersonChangesEndDate)
        );

        ChangeResultsPage changeResultsPage = call.singleOrError().blockingGet();

        assertChangeResultsPage(changeResultsPage);
    }
}
