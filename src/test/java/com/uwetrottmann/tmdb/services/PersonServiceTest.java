package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.PersonCastCredit;
import com.uwetrottmann.tmdb.entities.PersonCredits;
import com.uwetrottmann.tmdb.entities.PersonCrewCredit;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PersonServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    @Test
    public void test_summary() throws ParseException {
        Person person = getManager().personService().summary(287);
        assertNotNull("Result was null.", person);
        assertEquals("Person name does not match.", "Brad Pitt", person.name);
        assertNotNull("Person homepage was null", person.homepage);
        assertNotNull("Person TMDB ID was null.", person.id);
        assertNotNull("Person biography was null.", person.biography);
        assertEquals("Person birthday does not match.", JSON_STRING_DATE.parse("1963-12-18"),
                person.birthday);
        assertNull("Person deathday does not match", person.deathday);
        assertNotNull("Person place of birth does not match", person.place_of_birth);
        assertNotNull("Movie profile path was null.", person.profile_path);
    }

    @Test
    public void test_movie_credits() {
        PersonCredits credits = getManager().personService().movieCredits(287, null);
        assertThat(credits.id).isEqualTo(287);
        assertCastCredits(credits, false);
        assertCrewCredits(credits, false);

        for (PersonCastCredit credit : credits.cast) {
            assertThat(credit.title).isNotEmpty();
        }
    }

    @Test
    public void test_tv_credits() {
        PersonCredits credits = getManager().personService().tvCredits(287, null);
        assertThat(credits.id).isEqualTo(287);
        assertCastCredits(credits, false);

        for (PersonCastCredit credit : credits.cast) {
            assertThat(credit.episode_count).isGreaterThanOrEqualTo(0);
            assertThat(credit.name).isNotEmpty();
        }
    }

    @Test
    public void test_combined_credits() {
        PersonCredits credits = getManager().personService().combinedCredits(287, null);
        assertThat(credits.id).isEqualTo(287);
        assertCastCredits(credits, true);
        assertCrewCredits(credits, true);
    }

    private void assertCastCredits(PersonCredits credits, boolean hasMediaType) {
        // assert cast credits
        assertThat(credits.cast).isNotEmpty();
        for (PersonCastCredit credit : credits.cast) {
            assertThat(credit.character).isNotNull(); // may be empty

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty();
            }
        }
    }

    private void assertCrewCredits(PersonCredits credits, boolean hasMediaType) {
        // assert crew credits
        assertThat(credits.crew).isNotEmpty();
        for (PersonCrewCredit credit : credits.crew) {
            // may be empty, but should exist
            assertThat(credit.department).isNotNull();
            assertThat(credit.job).isNotNull();

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty();
            }
        }
    }

}
