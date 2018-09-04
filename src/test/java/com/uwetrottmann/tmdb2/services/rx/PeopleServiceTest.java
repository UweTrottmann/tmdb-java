package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testPerson;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertTaggedImages;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPerson;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonCredits;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.PersonAssertions;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonCredits;
import com.uwetrottmann.tmdb2.entities.PersonExternalIds;
import com.uwetrottmann.tmdb2.entities.PersonImages;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TaggedImagesResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;

public class PeopleServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        Observable<Person> call = getUnauthenticatedInstance().rx.personService().summary(
                testPerson.id
        );

        Person person = call.singleOrError().blockingGet();

        assertPerson(person);
    }

    @Test
    public void test_summary_with_append_to_response() throws IOException {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", new TmdbDate(testPersonChangesStartDate).toString());
        opts.put("end_date", new TmdbDate(testPersonChangesEndDate).toString());

        Observable<Person> call = getUnauthenticatedInstance().rx.personService().summary(
                testPerson.id,
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.MOVIE_CREDITS,
                        AppendToResponseItem.COMBINED_CREDITS,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CHANGES,
                        AppendToResponseItem.TAGGED_IMAGES,
                        AppendToResponseItem.TV_CREDITS
                ),
                opts
        );

        Person person = call.singleOrError().blockingGet();

        assertPersonDataIntegrity(person);
        assertContentChanges(person.changes);
        assertImages(person.images.profiles);
        assertTaggedImages(person.tagged_images);
        assertPersonCredits(person.movie_credits);
        assertPersonCredits(person.tv_credits);
        assertPersonCredits(person.combined_credits);
        PersonAssertions.assertTestPersonExternalIds(person.external_ids);
    }

    @Test
    public void test_changes() throws IOException {
        Observable<Changes> call = getUnauthenticatedInstance().rx.personService().changes(
                testPerson.id,
                null,
                new TmdbDate(testPersonChangesStartDate),
                new TmdbDate(testPersonChangesEndDate),
                1
        );

        Changes changes = call.singleOrError().blockingGet();

        assertContentChanges(changes);
    }

    @Test
    public void test_movie_credits() throws IOException {
        Observable<PersonCredits> call = getUnauthenticatedInstance().rx.personService().movieCredits(
                testPerson.id,
                null
        );

        PersonCredits credits = call.singleOrError().blockingGet();

        assertPersonCredits(credits);
    }

    @Test
    public void test_tv_credits() throws IOException {
        Observable<PersonCredits> call = getUnauthenticatedInstance().rx.personService().tvCredits(
                testPerson.id,
                null
        );

        PersonCredits credits = call.singleOrError().blockingGet();

        assertPersonCredits(credits);
    }

    @Test
    public void test_combined_credits() throws IOException {
        Observable<PersonCredits> call = getUnauthenticatedInstance().rx.personService().combinedCredits(
                testPerson.id,
                null
        );

        PersonCredits credits = call.singleOrError().blockingGet();

        assertPersonCredits(credits);
    }

    @Test
    public void test_external_ids() throws IOException {
        Observable<PersonExternalIds> call = getUnauthenticatedInstance().rx.personService().externalIds(
                testPerson.id
        );

        PersonExternalIds ids = call.singleOrError().blockingGet();

        assertThat(ids.id).isEqualTo(testPerson.id);
        PersonAssertions.assertTestPersonExternalIds(ids);
    }

    @Test
    public void test_images() throws IOException {
        Observable<PersonImages> call = getUnauthenticatedInstance().rx.personService().images(testPerson.id);
        PersonImages images = call.singleOrError().blockingGet();

        assertImages(images.profiles);
        assertThat(images.id).isEqualTo(testPerson.id);
    }

    @Test
    public void test_tagged_images() throws IOException {
        Observable<TaggedImagesResultsPage> call = getUnauthenticatedInstance().rx.personService().taggedImages(testPerson.id, null, null);
        TaggedImagesResultsPage images = call.singleOrError().blockingGet();

        assertTaggedImages(images);
        assertThat(images.id).isEqualTo(testPerson.id);
    }

    @Test
    public void test_popular() throws IOException {
        Observable<PersonResultsPage> call = getUnauthenticatedInstance().rx.personService().popular(null);
        PersonResultsPage popular = call.singleOrError().blockingGet();

        assertPersonResultsPage(popular);
    }

    @Test
    public void test_latest() throws IOException {
        Observable<Person> call = getUnauthenticatedInstance().rx.personService().latest();
        Person person = call.singleOrError().blockingGet();

        // Latest testPerson might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(person).isNotNull();
        assertThat(person.name).isNotEmpty();
        assertThat(person.id).isPositive();
    }

}
