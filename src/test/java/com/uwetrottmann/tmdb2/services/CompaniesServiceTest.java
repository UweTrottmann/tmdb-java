// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testCompany;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertCompany;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertCompanyDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

public class CompaniesServiceTest extends BaseTestCase {
    @Test
    public void test_company_summary() throws IOException {
        Call<Company> call = getUnauthenticatedInstance().companiesService().summary(
                testCompany.id
        );

        Company company = call.execute().body();

        assertCompanyDataIntegrity(company);
    }

    @Test
    public void test_company_summary_append_movies() throws IOException {
        Call<Company> call = getUnauthenticatedInstance().companiesService().summary(
                testCompany.id,
                new AppendToResponse(
                        AppendToResponseItem.MOVIES
                )
        );

        Company company = call.execute().body();

        assertCompany(company);

        assertMovieResultsPage(company.movies);
    }

    @Test
    public void test_company_movies() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().companiesService().movies(
                testCompany.id
        );

        MovieResultsPage movieResultsPage = call.execute().body();

        assertMovieResultsPage(movieResultsPage);
    }
}
