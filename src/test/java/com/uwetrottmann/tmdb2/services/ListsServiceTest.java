package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.annotations.RequiresAccountSession;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListCreateRequest;
import com.uwetrottmann.tmdb2.entities.ListCreateResponse;
import com.uwetrottmann.tmdb2.entities.ListItemStatus;
import com.uwetrottmann.tmdb2.entities.ListOperation;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.exceptions.TmdbServiceErrorException;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testList;
import static com.uwetrottmann.tmdb2.TestData.testMovie;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.accountDataInitialized;
import static com.uwetrottmann.tmdb2.assertions.ListAssertions.assertListDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

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

    @Test
    @RequiresAccountSession
    public void test_create_edit_clear_delete_list() throws IOException {
        assumeTrue(accountDataInitialized);

        ListCreateRequest listCreateRequest = new ListCreateRequest();
        listCreateRequest.name = "Test";
        listCreateRequest.language = "en";
        listCreateRequest.description = "Test";

        Call<ListCreateResponse> callLCResponse = getAuthenticatedInstance().listsService().createList(listCreateRequest);

        ListCreateResponse listCreateResponse = callLCResponse.execute().body();

        assertThat(listCreateResponse).isNotNull();
        assertThat(listCreateResponse.success).isTrue();
        assertThat(listCreateResponse.list_id).isNotNull();
        assertThat(listCreateResponse.status_code).isEqualTo(1);

        ListOperation listOperation = new ListOperation();
        listOperation.media_id = testMovie.id;

        Call<Status> callStatus = getAuthenticatedInstance().listsService().addMovie(listCreateResponse.list_id, listOperation);

        Status status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(12);

        callStatus = getAuthenticatedInstance().listsService().removeMovie(listCreateResponse.list_id, listOperation);

        status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(13);

        callStatus = getAuthenticatedInstance().listsService().clear(listCreateResponse.list_id, true);

        status = callStatus.execute().body();

        assertThat(status).isNotNull();
        assertThat(status.status_code).isEqualTo(12);

        // Delete List will delete the list, but it will throw a service related error due to a service bug.
        // Related Information: https://www.themoviedb.org/talk/592ae962c3a368787005afc1

        callStatus = getAuthenticatedInstance().listsService().delete(listCreateResponse.list_id);
        try {
            status = callStatus.execute().body();

            assertThat(status).isNotNull();
        } catch (TmdbServiceErrorException exc) {
            assumeTrue(true);
        }

    }
}
