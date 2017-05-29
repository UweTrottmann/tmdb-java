package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Credit;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testCredit;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCreditDataIntegrity;

public class CreditsServiceTest extends BaseTestCase {

    @Test
    public void test_credit() throws IOException {
        Call<Credit> call = getUnauthenticatedInstance().creditsService().credit(
                testCredit.id
        );

        Credit credit = call.execute().body();

        assertCreditDataIntegrity(credit);
    }
}
