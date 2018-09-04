package com.uwetrottmann.tmdb2.services.rx;

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
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class SearchServiceTest extends BaseTestCase {

    @Test
    public void test_companySearch() throws IOException {
        Observable<CompanyResultsPage> call = getUnauthenticatedInstance().rx.searchService().company(
                testCompany.name,
                null
        );

        CompanyResultsPage companyResults = call.singleOrError().blockingGet();

        assertCompanyResultsPage(companyResults);
    }

    @Test
    public void test_collectionSearch() throws IOException {
        Observable<CollectionResultsPage> call = getUnauthenticatedInstance().rx.searchService().collection(
                testCollection.name,
                null,
                null
        );
        CollectionResultsPage collectionResults = call.singleOrError().blockingGet();

        assertCollectionResultsPage(collectionResults);
    }

    @Test
    public void test_keywordSearch() throws IOException {
        Observable<KeywordResultsPage> call = getUnauthenticatedInstance().rx.searchService().keyword(
                "fight",
                null
        );
        KeywordResultsPage keywordResults = call.singleOrError().blockingGet();

        assertKeywordResultsPage(keywordResults);
    }

    @Test
    public void test_movieSearch() throws IOException {
        Observable<MovieResultsPage> call = getUnauthenticatedInstance().rx.searchService().movie(
                testMovie.title,
                null,
                null,
                null,
                null,
                null,
                null
        );

        MovieResultsPage movieResults = call.singleOrError().blockingGet();

        assertMovieResultsPage(movieResults);
    }

    @Test
    public void test_personSearch() throws IOException {
        Observable<PersonResultsPage> call = getUnauthenticatedInstance().rx.searchService().person(
                testPerson.name,
                null,
                null,
                null
        );
        PersonResultsPage personResults = call.singleOrError().blockingGet();

        assertPersonResultsPage(personResults);
    }

    @Test
    public void test_tv() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.searchService().tv(
                testTvShow.name,
                null,
                null,
                null,
                null
        );

        TvShowResultsPage tvResults = call.singleOrError().blockingGet();

        assertTvShowResultsPage(tvResults);
    }

    @Test
    public void test_multiSearch() throws IOException {
        Observable<MediaResultsPage> call = getUnauthenticatedInstance().rx.searchService().multi(
                "snowden",
                null,
                null,
                null,
                null
        );

        MediaResultsPage mediaResultsPage = call.singleOrError().blockingGet();

        assertMediaResultsPage(mediaResultsPage);
    }
}
