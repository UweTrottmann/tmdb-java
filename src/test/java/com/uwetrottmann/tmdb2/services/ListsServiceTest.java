// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.assertions.MediaAssertions.assertMedia;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.ListAssertions;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListItemStatus;
import com.uwetrottmann.tmdb2.entities.Media;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class ListsServiceTest extends BaseTestCase {

    private static final int TEST_LIST_ID = 5;

    @Test
    public void test_summary() throws IOException {
        Call<List> call = getUnauthenticatedInstance().listsService().summary(
                TEST_LIST_ID
        );

        List list = call.execute().body();

        ListAssertions.assertBaseList(list);

        assertThat(list).isNotNull();
        assertThat(list.created_by).isEqualTo("Travis Bell");
        assertThat(list.name).isEqualTo("The Avengers");

        assertThat(list.items).isNotNull();
        assertThat(list.items).isNotEmpty();
        for (Media media : list.items) {
            assertMedia(media);
        }
    }

    @Test
    public void test_item_status() throws IOException {
        Call<ListItemStatus> call = getUnauthenticatedInstance().listsService().itemStatus(
                TEST_LIST_ID,
                10195 /* Thor */
        );

        ListItemStatus listItemStatus = call.execute().body();

        assertThat(listItemStatus).isNotNull();
        assertThat(listItemStatus.item_present).isTrue();
    }
}
