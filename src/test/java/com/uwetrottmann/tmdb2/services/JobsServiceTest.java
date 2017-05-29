package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Jobs;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JobsServiceTest extends BaseTestCase {
    @Test
    public void test_jobs() throws IOException {
        Call<Jobs> call = getUnauthenticatedInstance().jobsService().jobs();

        Jobs jobs = call.execute().body();

        assertThat(jobs).isNotNull();
        assertThat(jobs.jobs).isNotNull();
        assertThat(jobs.jobs).isNotEmpty();
        for (Jobs.Department dept : jobs.jobs) {
            assertThat(dept).isNotNull();
            assertThat(dept.department).isNotNull();
            assertThat(dept.job_list).isNotNull();
            assertThat(dept.job_list).isNotEmpty();
            assertThat(dept.job_list.contains(null)).isFalse();
        }
    }
}
