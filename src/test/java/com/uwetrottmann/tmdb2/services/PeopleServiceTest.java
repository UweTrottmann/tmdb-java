package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonCredits;
import com.uwetrottmann.tmdb2.entities.PersonExternalIds;
import com.uwetrottmann.tmdb2.entities.PersonImages;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TaggedImagesResultsPage;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import com.uwetrottmann.tmdb2.utils.AppendToResponse;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static com.uwetrottmann.tmdb2.TestData.testPerson;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testPersonChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertTaggedImages;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPerson;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonCredits;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonExternalIds;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonExternalIdsIntegrity;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertPersonResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class PeopleServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException, ParseException {
        Call<Person> call = getUnauthenticatedInstance().personService().summary(
                testPerson.id
        );

        Person person = call.execute().body();

        assertPerson(person);
    }

    @Test
    public void test_summary_with_append_to_response() throws IOException, ParseException {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", testPersonChangesStartDate.toString());
        opts.put("end_date", testPersonChangesEndDate.toString());

        Call<Person> call = getUnauthenticatedInstance().personService().summary(
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

        Person person = call.execute().body();

        assertPersonDataIntegrity(person);
        assertContentChanges(person.changes);
        assertImages(person.images.profiles);
        assertTaggedImages(person.tagged_images);
        assertPersonCredits(person.movie_credits);
        assertPersonCredits(person.tv_credits);
        assertPersonCredits(person.combined_credits);
        assertPersonExternalIds(person.external_ids);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().personService().changes(
                testPerson.id,
                testPersonChangesStartDate,
                testPersonChangesEndDate,
                1
        );

        Changes changes = call.execute().body();

        assertContentChanges(changes);
    }

    @Test
    public void test_movie_credits() throws IOException {
        Call<PersonCredits> call = getUnauthenticatedInstance().personService().movieCredits(
                testPerson.id
        );

        PersonCredits credits = call.execute().body();

        assertPersonCredits(credits);
    }

    @Test
    public void test_tv_credits() throws IOException {
        Call<PersonCredits> call = getUnauthenticatedInstance().personService().tvCredits(
                testPerson.id
        );

        PersonCredits credits = call.execute().body();

        assertPersonCredits(credits);
    }

    @Test
    public void test_combined_credits() throws IOException {
        Call<PersonCredits> call = getUnauthenticatedInstance().personService().combinedCredits(
                testPerson.id
        );

        PersonCredits credits = call.execute().body();

        assertPersonCredits(credits);
    }

    @Test
    public void test_external_ids() throws IOException {
        Call<PersonExternalIds> call = getUnauthenticatedInstance().personService().externalIds(
                testPerson.id
        );

        PersonExternalIds ids = call.execute().body();

        assertPersonExternalIdsIntegrity(ids);
    }

    @Test
    public void test_images() throws IOException {
        Call<PersonImages> call = getUnauthenticatedInstance().personService().images(
                testPerson.id
        );

        PersonImages images = call.execute().body();

        assertImages(images.profiles);
        assertThat(images.id).isEqualTo(testPerson.id);
    }

    @Test
    public void test_tagged_images() throws IOException {
        Call<TaggedImagesResultsPage> call = getUnauthenticatedInstance().personService().taggedImages(
                testPerson.id
        );

        TaggedImagesResultsPage images = call.execute().body();

        assertTaggedImages(images);
        assertThat(images.id).isEqualTo(testPerson.id);
    }

    @Test
    public void test_popular() throws IOException {
        Call<PersonResultsPage> call = getUnauthenticatedInstance().personService().popular();
        PersonResultsPage popular = call.execute().body();

        assertPersonResultsPage(popular);
    }

    @Test
    public void test_latest() throws IOException {
        Call<Person> call = getUnauthenticatedInstance().personService().latest();
        Person person = call.execute().body();

        // Latest testPerson might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(person).isNotNull();
        assertThat(person.name).isNotEmpty();
        assertThat(person.id).isPositive();
    }

}
