package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.Network;

import static com.uwetrottmann.tmdb2.TestData.testNetwork;
import static org.assertj.core.api.Assertions.assertThat;

public class NetworkAssertions {
    public static void assertNetwork(Network network) {
        assertThat(network).isNotNull();
        assertThat(network.name).isNotNull();
        assertThat(network.id).isNotNull();
    }

    public static void assertNetworkDataIntegrity(Network network) {
        assertNetwork(network);
        assertThat(network.name).isEqualTo(testNetwork.name);
        assertThat(network.id).isEqualTo(testNetwork.id);
    }
}
