package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Network;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testNetwork;
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
}
