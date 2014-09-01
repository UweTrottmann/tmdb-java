
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.PersonCredits;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.api.Assertions.assertThat;

public class PersonServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");
    private CountDownLatch lock = new CountDownLatch(1);
    private Person personResponse;
    private PersonCredits personCreditsResponse;

    public void test_summary() throws ParseException {
        Person person = getManager().personService().summary(287);
        assertsPerson(person);
    }

    public void test_sumary_async() throws Exception {
        getManager().personService().summary(287, new Callback<Person>() {
            @Override
            public void success(Person person, Response response) {
                personResponse = person;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertsPerson(personResponse);

    }

    private void assertsPerson (Person person) throws ParseException{
        assertNotNull("Result was null.", person);
        assertEquals("Person name does not match.", "Brad Pitt", person.name);
        assertNotNull("Person homepage was null", person.homepage);
        assertNotNull("Person TMDB ID was null.", person.id);
        assertNotNull("Person biography was null.", person.biography);
        assertEquals("Person birthday does not match.", JSON_STRING_DATE.parse("1963-12-18"), person.birthday);
        assertNull("Person deathday does not match", person.deathday);
        assertNotNull("Person place of birth does not match", person.place_of_birth);
        assertNotNull("Movie profile path was null.", person.profile_path);
    }

    public void test_movie_credits() {
        PersonCredits credits = getManager().personService().movieCredits(287) ;
        assertsMovieCredits(credits);
    }

    public void test_movie_credits_async() throws Exception {
        getManager().personService().movieCredits(287, new Callback<PersonCredits>() {
            @Override
            public void success(PersonCredits personCredits, Response response) {
                personCreditsResponse = personCredits;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertsMovieCredits(personCreditsResponse);
    }

    public void test_tv_credits() {
        PersonCredits credits = getManager().personService().tvCredits(287) ;
        assertsTvCredits(credits);
    }

    public void test_tv_credits_async() throws Exception {
        getManager().personService().tvCredits(287, new Callback<PersonCredits>() {
            @Override
            public void success(PersonCredits personCredits, Response response) {
                personCreditsResponse = personCredits;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertsTvCredits(personCreditsResponse);

    }

    public void test_combined_credits() {
        PersonCredits credits = getManager().personService().combinedCredits(287) ;
        assertsCombinedCredits(credits);
    }

    public void test_combined_credits_async() throws Exception {
        getManager().personService().combinedCredits(287, new Callback<PersonCredits>() {
            @Override
            public void success(PersonCredits credits, Response response) {
                personCreditsResponse = credits;
                lock.countDown();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        lockAwait();
        assertsCombinedCredits(personCreditsResponse);

    }

    private void assertsPersonCredits (PersonCredits credits) {
        assertThat(credits).isNotNull();
        assertThat(credits.id).isEqualTo(287);
        assertThat(credits.cast).isNotEmpty();
        assertThat(credits.cast.get(0)).isNotNull();
        assertThat(credits.crew).isNotEmpty();
    }

    private void assertsMovieCredits (PersonCredits credits) {
        assertThat(credits.cast.get(0).title).isNotNull();
        assertsPersonCredits(credits);
    }

    private void assertsTvCredits (PersonCredits credits) {
        assertThat(credits.cast.get(0).name).isNotNull();
        assertsPersonCredits(credits);
    }

    private void assertsCombinedCredits (PersonCredits credits) {
        assertThat(credits.cast.get(0).media_type).isNotNull();
        assertsPersonCredits(credits);
    }

    private void lockAwait() {
        try {
            lock.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
