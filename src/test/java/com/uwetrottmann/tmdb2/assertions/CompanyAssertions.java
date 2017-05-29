package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseCompany;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;

import static com.uwetrottmann.tmdb2.TestData.testCompany;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyAssertions {
    public static void assertCompanyResultsPage(CompanyResultsPage companyResultsPage) {
        assertBaseResultsPage(companyResultsPage);

        for (BaseCompany company : companyResultsPage.results) {
            assertBaseCompany(company, false);
        }
    }

    public static void assertBaseCompany(BaseCompany company, Boolean isLogoPresent) {
        assertThat(company).isNotNull();
        assertThat(company.id).isNotNull();
        assertThat(company.name).isNotNull();

        if (!isLogoPresent)
            return;

        assertThat(company.logo_path).isNotNull();
    }

    public static void assertCompany(Company company) {

        assertBaseCompany(company, true);

        //Can be null.
        //assertThat(company.description).isNotNull();
        assertThat(company.headquarters).isNotNull();
        assertThat(company.homepage).isNotNull();

        if (company.parent_company != null)
            assertBaseCompany(company.parent_company, true);
    }

    public static void assertCompanyDataIntegrity(Company company) {
        assertCompany(company);
        assertThat(company.homepage).isEqualTo(testCompany.homepage);
        assertThat(company.logo_path).isEqualTo(testCompany.logo_path);
        assertThat(company.id).isEqualTo(testCompany.id);
        assertThat(company.headquarters).isEqualTo(testCompany.headquarters);
        assertThat(company.name).isEqualTo(testCompany.name);
        assertThat(company.description).isEqualTo(testCompany.description);
    }
}
