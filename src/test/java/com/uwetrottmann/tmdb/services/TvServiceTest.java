package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Credits;

import static org.fest.assertions.api.Assertions.assertThat;

public class TvServiceTest extends BaseTestCase {

    public void test_credits() {
        Credits credits = getManager().tvService().credits(1396, null);
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(1396);
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

}
