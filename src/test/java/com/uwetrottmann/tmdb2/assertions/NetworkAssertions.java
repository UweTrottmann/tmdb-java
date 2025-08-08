// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.Network;

import static com.uwetrottmann.tmdb2.TestData.testNetwork;
import static org.assertj.core.api.Assertions.assertThat;

public class NetworkAssertions {
    public static void assertNetwork(Network network, boolean fromTvSummary) {
        assertThat(network).isNotNull();
        assertThat(network.name).isNotNull();
        assertThat(network.id).isNotNull();
        assertThat(network.origin_country).isNotNull();

        if (fromTvSummary) {
            // logo_path is only present from a tv summary response
            assertThat(network.logo_path).isNotNull();
        }
    }

    public static void assertNetworkDataIntegrity(Network network) {
        assertNetwork(network, false);
        assertThat(network.name).isEqualTo(testNetwork.name);
        assertThat(network.id).isEqualTo(testNetwork.id);
    }
}
