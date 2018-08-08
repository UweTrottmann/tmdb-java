package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testCredit;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Credit;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class CreditsServiceTest extends BaseTestCase {

    @Test
    public void test_credit() throws IOException {
        Call<Credit> call = getUnauthenticatedInstance().creditsService().credit(
                testCredit.id
        );

        Credit credit = call.execute().body();

        assertThat(credit).isNotNull();
        assertThat(credit.id).isNotNull();
        assertThat(credit.department).isNotNull();
        assertThat(credit.credit_type).isNotNull();
        assertThat(credit.job).isNotNull();
        assertThat(credit.media_type).isNotNull();
        assertThat(credit.person).isNotNull();
        assertThat(credit.person.name).isNotNull();
        assertThat(credit.person.id).isNotNull();
        assertThat(credit.media).isNotNull();
        assertThat(credit.media.character).isNotNull();
        assertThat(credit.media.id).isNotNull();
        assertThat(credit.media.original_name).isNotNull();
        assertThat(credit.media.name).isNotNull();
        assertThat(credit.media.seasons).isNotNull();
        assertThat(credit.media.seasons).isNotEmpty();

        assertThat(credit.media_type).isEqualTo(testCredit.media_type);
        assertThat(credit.id).isEqualTo(testCredit.id);
        assertThat(credit.department).isEqualTo(testCredit.department);
        assertThat(credit.job).isEqualTo(testCredit.job);
        assertThat(credit.credit_type).isEqualTo(testCredit.credit_type);
        assertThat(credit.media.id).isEqualTo(testCredit.media.id);
        assertThat(credit.media.name).isEqualTo(testCredit.media.name);
        assertThat(credit.media.original_name).isEqualTo(testCredit.media.original_name);
        assertThat(credit.media.character).isEqualTo(testCredit.media.character);
    }
}
