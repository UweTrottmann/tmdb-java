package com.uwetrottmann.tmdb2.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Configuration;
import com.uwetrottmann.tmdb2.entities.Jobs;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class ConfigurationServiceTest extends BaseTestCase {

    @Test
    public void test_configuration() throws IOException {
        Observable<Configuration> call = getUnauthenticatedInstance().rx.configurationService().configuration();
        Configuration config = call.singleOrError().blockingGet();
        assertThat(config).isNotNull();
        assertThat(config.images).isNotNull();
        assertThat(config.images.base_url).isNotEmpty();
        assertThat(config.images.secure_base_url).isNotEmpty();
        assertThat(config.images.poster_sizes).isNotEmpty();
        assertThat(config.images.backdrop_sizes).isNotEmpty();
        assertThat(config.images.profile_sizes).isNotEmpty();
        assertThat(config.images.logo_sizes).isNotEmpty();
        assertThat(config.images.still_sizes).isNotEmpty();
        assertThat(config.change_keys).isNotEmpty();
    }

    @Test
    public void test_jobs() throws IOException {
        Observable<List<Jobs>> call = getUnauthenticatedInstance().rx.configurationService().jobs();
        List<Jobs> jobs = call.singleOrError().blockingGet();

        assertThat(jobs).isNotNull();
        assertThat(jobs).isNotEmpty();
        for (Jobs dept : jobs) {
            assertThat(dept).isNotNull();
            assertThat(dept.department).isNotEmpty();
            assertThat(dept.jobs).isNotEmpty();
            assertThat(dept.jobs.contains(null)).isFalse();
        }
    }

}
