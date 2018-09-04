package com.uwetrottmann.tmdb2.services.rx;

import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Timezones;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import retrofit2.Call;

public class TimezonesServiceTest extends BaseTestCase {

    @Test
    public void test_timezones() throws IOException {
        Observable<Timezones> call = getUnauthenticatedInstance().rx.timezonesService().timezones();

        Timezones timezones = call.singleOrError().blockingGet();
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
