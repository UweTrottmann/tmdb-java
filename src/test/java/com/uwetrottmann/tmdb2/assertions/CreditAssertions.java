package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.CastMember;
import com.uwetrottmann.tmdb2.entities.Credit;
import com.uwetrottmann.tmdb2.entities.CreditMedia;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.CrewMember;

import java.util.List;

import static com.uwetrottmann.tmdb2.TestData.testCredit;
import static org.assertj.core.api.Assertions.assertThat;

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

    public static void assertCredit(Credit credit) {
        assertThat(credit).isNotNull();
        assertThat(credit.id).isNotNull();
        assertThat(credit.department).isNotNull();
        assertThat(credit.credit_type).isNotNull();
        assertThat(credit.job).isNotNull();
        assertThat(credit.media_type).isNotNull();
        assertThat(credit.person).isNotNull();
        assertThat(credit.person.name).isNotNull();
        assertThat(credit.person.id).isNotNull();
        assertCreditMedia(credit.media);

    }
    public static void assertCreditMedia(CreditMedia creditMedia) {
        assertThat(creditMedia).isNotNull();
        assertThat(creditMedia.character).isNotNull();
        assertThat(creditMedia.id).isNotNull();
        assertThat(creditMedia.original_name).isNotNull();
        assertThat(creditMedia.name).isNotNull();
        assertThat(creditMedia.seasons).isNotNull();
        assertThat(creditMedia.seasons).isNotEmpty();
    }

    public static void assertCreditDataIntegrity(Credit credit) {
        assertCredit(credit);
        assertThat(credit.media_type).isEqualTo(testCredit.media_type);
        assertThat(credit.id).isEqualTo(testCredit.id);
        assertThat(credit.department).isEqualTo(testCredit.department);
        assertThat(credit.job).isEqualTo(testCredit.job);
        assertThat(credit.credit_type).isEqualTo(testCredit.credit_type);
        assertThat(credit.media.id).isEqualTo(testCredit.media.id);
        assertThat(credit.media.name).isEqualTo(testCredit.media.name);
        assertThat(credit.media.original_name).isEqualTo(testCredit.media.original_name);
        assertThat(credit.media.character).isEqualTo(testCredit.media.character);
    }
}
