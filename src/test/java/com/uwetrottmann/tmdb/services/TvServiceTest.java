package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.CastMember;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.CrewMember;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.api.Assertions.assertThat;

public class TvServiceTest extends BaseTestCase {

    private CountDownLatch lock = new CountDownLatch(1);
    private Credits credits;
    private ExternalIds ids;

    public void test_credits() {
        Credits credits = getManager().tvService().credits(TestData.TVSHOW_ID, null);
        assertCredits(credits);
    }

    public void test_credits_async() throws Exception {
        getManager().tvService().credits(TestData.TVSHOW_ID, null, new Callback<Credits>() {
            @Override
            public void success(Credits credits, Response response) {
                TvServiceTest.this.credits = credits;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();

        assertCredits(credits);
    }

    private void assertCredits(Credits credits) {
        assertThat(credits.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(credits.cast).isNotEmpty();

        CastMember bryanCranston = credits.cast.get(0);
        assertThat(bryanCranston).isNotNull();
        assertThat(bryanCranston.id).isEqualTo(17419);
        assertThat(bryanCranston.name).isEqualTo("Bryan Cranston");
        assertThat(bryanCranston.character).isEqualTo("Walter White");

        assertThat(credits.crew).isNotEmpty();
        CrewMember vinceGilligan = credits.crew.get(0);
        assertThat(vinceGilligan).isNotNull();
        assertThat(vinceGilligan.id).isEqualTo(66633);
        assertThat(vinceGilligan.name).isEqualTo("Vince Gilligan");
        assertThat(vinceGilligan.job).isEqualTo("Executive Producer");
    }

    public void test_externalIds() {
        ExternalIds ids = getManager().tvService().externalIds(TestData.TVSHOW_ID, null);
        assertExternalIds(ids);
    }

    public void test_externalIds_async() throws Exception {
        getManager().tvService().externalIds(TestData.TVSHOW_ID, null, new Callback<ExternalIds>() {
            @Override
            public void success(ExternalIds externalIds, Response response) {
                ids = externalIds;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();

        assertExternalIds(ids);
    }

    private void assertExternalIds(ExternalIds ids) {
        assertThat(ids.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(ids.tvdb_id).isEqualTo(TestData.TVSHOW_TVDB_ID);
    }

    private void lockAwait () {
        try {
            lock.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
