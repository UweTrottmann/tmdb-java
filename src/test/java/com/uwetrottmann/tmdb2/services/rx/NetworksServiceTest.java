package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testNetwork;
import static com.uwetrottmann.tmdb2.assertions.NetworkAssertions.assertNetworkDataIntegrity;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Network;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class NetworksServiceTest extends BaseTestCase {
    @Test
    public void test_networks() throws IOException {
        Observable<Network> call = getUnauthenticatedInstance().rx.networksService().summary(
                testNetwork.id
        );

        Network network = call.singleOrError().blockingGet();

        assertNetworkDataIntegrity(network);
    }
}
