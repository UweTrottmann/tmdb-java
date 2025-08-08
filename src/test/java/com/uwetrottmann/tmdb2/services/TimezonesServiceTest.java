// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Timezones;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TimezonesServiceTest extends BaseTestCase {

    @Test
    public void test_timezones() throws IOException {
        Call<Timezones> call = getUnauthenticatedInstance().timezonesService().timezones();

        Timezones timezones = call.execute().body();
        assertThat(timezones).isNotNull();
        for (HashMap<String, List<String>> entryMap : timezones) {
            assertThat(entryMap).isNotNull();
            for (Map.Entry<String, List<String>> entry : entryMap.entrySet()) {
                assertThat(entry).isNotNull();
                assertThat(entry.getKey()).isNotNull();
                assertThat(entry.getValue()).isNotNull();
                assertThat(entry.getValue()).isNotNull();
                for (String value : entry.getValue()) {
                    assertThat(value).isNotNull();
                }
            }
        }
    }
}
