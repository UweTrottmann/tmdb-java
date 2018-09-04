package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListItemStatus;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class ListsServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        Observable<List> call = getUnauthenticatedInstance().rx.listsService().summary(
                TestData.testList.id
        );

        List list = call.singleOrError().blockingGet();

        assertListDataIntegrity(list);
    }

    @Test
    public void test_item_status() throws IOException {
        Observable<ListItemStatus> call = getUnauthenticatedInstance().rx.listsService().itemStatus(
                TestData.testList.id,
                TestData.testListMovie.id
        );

        ListItemStatus listItemStatus = call.singleOrError().blockingGet();

        assertThat(listItemStatus).isNotNull();
        assertThat(listItemStatus.item_present).isTrue();
    }
}
