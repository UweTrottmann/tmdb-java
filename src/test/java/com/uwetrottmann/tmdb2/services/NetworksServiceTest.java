// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Network;
import com.uwetrottmann.tmdb2.entities.NetworkImages;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testNetwork;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertNetworkImages;
import static com.uwetrottmann.tmdb2.assertions.NetworkAssertions.assertNetworkDataIntegrity;

public class NetworksServiceTest extends BaseTestCase {
    @Test
    public void test_networks() throws IOException {
        Call<Network> call = getUnauthenticatedInstance().networksService().summary(
                testNetwork.id
        );

        Network network = call.execute().body();

        assertNetworkDataIntegrity(network);
    }

    @Test
    public void test_network_images() throws IOException {
        Call<NetworkImages> call = getUnauthenticatedInstance().networksService().images(
                testNetwork.id
        );

        NetworkImages images = call.execute().body();

        assertNetworkImages(images.logos);
    }
}
