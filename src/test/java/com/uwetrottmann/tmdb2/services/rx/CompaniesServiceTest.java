package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testCompany;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertCompany;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertCompanyDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class CompaniesServiceTest extends BaseTestCase {
    @Test
    public void test_company_summary() throws IOException {
        Observable<Company> call = getUnauthenticatedInstance().rx.companiesService().summary(
                testCompany.id
        );

        Company company = call.singleOrError().blockingGet();

        assertCompanyDataIntegrity(company);
    }

    @Test
    public void test_company_summary_append_movies() throws IOException {
        Observable<Company> call = getUnauthenticatedInstance().rx.companiesService().summary(
                testCompany.id,
                new AppendToResponse(
                        AppendToResponseItem.MOVIES
                )
        );

        Company company = call.singleOrError().blockingGet();

        assertCompany(company);

        assertMovieResultsPage(company.movies);
    }

    @Test
    public void test_company_movies() throws IOException {
        Observable<MovieResultsPage> call = getUnauthenticatedInstance().rx.companiesService().movies(
                testCompany.id
        );

        MovieResultsPage movieResultsPage = call.singleOrError().blockingGet();

        assertMovieResultsPage(movieResultsPage);
    }
}
