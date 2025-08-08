// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.entities.CastMember;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.CrewMember;
import java.util.List;

public class CreditAssertions {
    public static void assertCrewCredits(List<CrewMember> crew) {
        assertThat(crew).isNotNull();

        for (CrewMember member : crew) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.department).isNotNull();
            assertThat(member.job).isNotNull();
        }
    }

    public static void assertCastCredits(List<CastMember> cast) {
        assertThat(cast).isNotNull();

        for (CastMember member : cast) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.character).isNotNull();
            assertThat(member.order).isNotNull();
            assertThat(member.order).isGreaterThanOrEqualTo(0);
        }
    }


    public static void assertCredits(Credits credits) {
        assertThat(credits).isNotNull();

        assertCastCredits(credits.cast);
        assertCrewCredits(credits.crew);

        if (credits.guest_stars != null) {
            assertCastCredits(credits.guest_stars);
        }

    }

}
