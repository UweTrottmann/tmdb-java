package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.ExternalIds;

import static org.fest.assertions.api.Assertions.assertThat;

public class TvServiceTest extends BaseTestCase {

    public void test_credits() {
        Credits credits = getManager().tvService().credits(TestData.TVSHOW_ID, null);
        assertThat(credits.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(credits.cast).isNotEmpty();

        Credits.CastMember bryanCranston = credits.cast.get(0);
        assertThat(bryanCranston).isNotNull();
        assertThat(bryanCranston.id).isEqualTo(17419);
        assertThat(bryanCranston.name).isEqualTo("Bryan Cranston");
        assertThat(bryanCranston.character).isEqualTo("Walter White");

        assertThat(credits.crew).isNotEmpty();
        Credits.CrewMember vinceGilligan = credits.crew.get(0);
        assertThat(vinceGilligan).isNotNull();
        assertThat(vinceGilligan.id).isEqualTo(66633);
        assertThat(vinceGilligan.name).isEqualTo("Vince Gilligan");
        assertThat(vinceGilligan.job).isEqualTo("Executive Producer");
    }

    public void test_externalIds() {
        ExternalIds ids = getManager().tvService().externalIds(TestData.TVSHOW_ID, null);
        assertThat(ids.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(ids.tvdb_id).isEqualTo(TestData.TVSHOW_TVDB_ID);
    }

}
