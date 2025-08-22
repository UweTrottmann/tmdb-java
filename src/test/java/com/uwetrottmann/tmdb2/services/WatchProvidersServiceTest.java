// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;


import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertWatchProviders;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.WatchProvidersResults;
import java.io.IOException;
import org.junit.Test;

public class WatchProvidersServiceTest extends BaseTestCase {

    @Test
    public void movies() throws IOException {
        WatchProvidersResults results = getUnauthenticatedInstance()
                .watchProvidersService()
                .movie(null, null)
                .execute()
                .body();
        
        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
        assertWatchProviders(results.results);
    }

    @Test
    public void tv() throws IOException {
        WatchProvidersResults results = getUnauthenticatedInstance()
                .watchProvidersService()
                .tv(null, null)
                .execute()
                .body();

        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
        assertWatchProviders(results.results);
    }
}
