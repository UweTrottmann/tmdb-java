package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BasePersonCredit;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonCastCredit;
import com.uwetrottmann.tmdb2.entities.PersonCredits;
import com.uwetrottmann.tmdb2.entities.PersonCrewCredit;
import com.uwetrottmann.tmdb2.entities.PersonExternalIds;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;

import java.util.List;

import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseExternalIds;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MediaAssertions.assertMedia;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonAssertions {
    public static void assertBasePerson(BasePerson person) {
        assertBasePerson(person, true);
    }

    public static void assertBasePerson(BasePerson person, Boolean extensive) {
        assertThat(person).isNotNull();
        assertThat(person.id).isPositive();
        assertThat(person.name).isNotNull();

        if (!extensive)
            return;

        assertThat(person.adult).isNotNull();
        assertThat(person.popularity).isPositive();
        try {
            Person p = (Person) person;
        } catch (Exception exc) {
            assertThat(person.known_for).isNotNull();
            for (Media media : person.known_for) {
                assertMedia(media);
            }
        }
    }

    public static void assertPerson(Person person) {
        assertBasePerson(person);
        assertThat(person.biography).isNotNull();
        assertThat(person.birthday).isNotNull();
        assertThat(person.gender).isNotNull();
        assertThat(person.homepage).isNotNull();
        assertThat(person.imdb_id).isNotNull();
        assertThat(person.place_of_birth).isNotNull();
        assertThat(person.also_known_as).isNotNull();
    }


    public static void assertPersonDataIntegrity(Person person) {
        assertPerson(person);
        assertThat(person.name).isEqualTo(TestData.testPerson.name);
        assertThat(person.birthday).isEqualTo(TestData.testPerson.birthday);
        assertThat(person.id).isEqualTo(TestData.testPerson.id);
        assertThat(person.imdb_id).isEqualTo(TestData.testPerson.imdb_id);
        assertThat(person.gender).isEqualTo(TestData.testPerson.gender);
        assertThat(person.adult).isEqualTo(TestData.testPerson.adult);
        assertThat(person.place_of_birth).isEqualTo(TestData.testPerson.place_of_birth);

    }

    public static void assertPersonExternalIds(PersonExternalIds externalIds) {
        assertBaseExternalIds(externalIds);
        assertThat(externalIds.instagram_id).isNotNull();
        assertThat(externalIds.imdb_id).isNotNull();
        assertThat(externalIds.facebook_id).isNotNull();
        assertThat(externalIds.twitter_id).isNotNull();
    }

    public static void assertPersonResultsPage(PersonResultsPage personResultsPage) {
        assertBaseResultsPage(personResultsPage);
        for (BasePerson basePerson : personResultsPage.results) {
            assertBasePerson(basePerson);
        }
    }

    public static void assertPersonExternalIdsIntegrity(PersonExternalIds personExternalIds) {

        assertPersonExternalIds(personExternalIds);

        assertThat(personExternalIds.facebook_id).isEqualTo(TestData.testPerson.external_ids.facebook_id);
        assertThat(personExternalIds.imdb_id).isEqualTo(TestData.testPerson.external_ids.imdb_id);
        assertThat(personExternalIds.instagram_id).isEqualTo(TestData.testPerson.external_ids.instagram_id);
        assertThat(personExternalIds.freebase_id).isEqualTo(TestData.testPerson.external_ids.freebase_id);
        assertThat(personExternalIds.freebase_mid).isEqualTo(TestData.testPerson.external_ids.freebase_mid);
        assertThat(personExternalIds.tvrage_id).isEqualTo(TestData.testPerson.external_ids.tvrage_id);
    }

    public static void assertPersonCredits(PersonCredits credits) {
        assertThat(credits).isNotNull();
        assertPersonCastCredits(credits.cast);
        assertPersonCrewCredits(credits.crew);
    }

    public static void assertPersonCastCredits(List<PersonCastCredit> credits) {
        assertThat(credits).isNotNull();
        assertThat(credits).isNotEmpty();
        for (PersonCastCredit credit : credits) {
            assertBasePersonCredit(credit);

            assertThat(credit.character).isNotNull();

            switch (credit.media.media_type) {
                case TV:
                    assertThat(credit.episode_count).isNotNull();
            }

        }
    }

    public static void assertPersonCrewCredits(List<PersonCrewCredit> credits) {
        assertThat(credits).isNotNull();
        assertThat(credits).isNotEmpty();
        for (PersonCrewCredit credit : credits) {
            assertBasePersonCredit(credit);

            assertThat(credit.job).isNotNull();
            assertThat(credit.department).isNotNull();
        }
    }


    public static void assertBasePersonCredit(BasePersonCredit credit) {
        assertThat(credit.media.media_type).isNotNull();
        assertThat(credit.credit_id).isNotNull();
        assertThat(credit.media).isNotNull();
        switch (credit.media.media_type) {
            case MOVIE:
                assertThat(credit.media.movie).isNotNull();
                assertThat(credit.media.movie.id).isNotNull();
                assertThat(credit.media.movie.title).isNotNull();
                assertThat(credit.media.movie.release_date).isNotNull();
                assertThat(credit.media.movie.original_title).isNotNull();
                break;
            case TV:
                assertThat(credit.media.tvShow).isNotNull();
                assertThat(credit.media.tvShow.id).isNotNull();
                assertThat(credit.media.tvShow.name).isNotNull();
                assertThat(credit.media.tvShow.original_name).isNotNull();
                break;
        }
    }
}
