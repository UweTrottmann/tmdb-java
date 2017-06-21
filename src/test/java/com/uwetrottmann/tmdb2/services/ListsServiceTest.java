package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListItemStatus;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testList;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

public class ListsServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        Call<List> call = getUnauthenticatedInstance().listsService().summary(
                testList.id
        );

        List list = call.execute().body();

        assertListDataIntegrity(list);

    }

    @Test
    public void test_item_status() throws IOException {
        Call<ListItemStatus> call = getUnauthenticatedInstance().listsService().itemStatus(
                testList.id,
                testMovie.id
        );

        ListItemStatus listItemStatus = call.execute().body();

        assertThat(listItemStatus).isNotNull();
        assertThat(listItemStatus.item_present).isTrue();
    }
}
