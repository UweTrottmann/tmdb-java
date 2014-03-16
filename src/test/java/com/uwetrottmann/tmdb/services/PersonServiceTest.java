
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.PersonCredits;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.fest.assertions.api.Assertions.assertThat;

public class PersonServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

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

    public void test_movie_credits() {
        PersonCredits credits = getManager().personService().movieCredits(287) ;
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(287);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.cast.get(0).title).isNotNull();
        assertThat(credits.crew).isNotEmpty();
    }

    public void test_tv_credits() {
        PersonCredits credits = getManager().personService().tvCredits(287) ;
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(287);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.cast.get(0).name).isNotNull();
        assertThat(credits.crew).isNotEmpty();
    }

    public void test_combined_credits() {
        PersonCredits credits = getManager().personService().combinedCredits(287) ;
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(287);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
            assertThat(credits.cast.get(0).media_type).isNotNull();
        assertThat(credits.crew).isNotEmpty();
    }

}
