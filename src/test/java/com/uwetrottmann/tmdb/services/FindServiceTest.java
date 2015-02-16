package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.FindResults;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.enumerations.ExternalSource;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindServiceTest extends BaseTestCase {

    @Test
    public void test_find() {
        FindResults results = getManager().findService().find("nm0000093", ExternalSource.IMDB_ID, null);
        assertThat(results).isNotNull();
        assertThat(results.person_results).isNotEmpty();

        Person bradPitt = results.person_results.get(0);
        assertThat(bradPitt).isNotNull();
        assertThat(bradPitt.name).isEqualTo("Brad Pitt");
    }

}
