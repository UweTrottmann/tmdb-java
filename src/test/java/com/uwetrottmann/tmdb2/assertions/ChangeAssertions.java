package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.ChangeResultsPage;
import com.uwetrottmann.tmdb2.entities.Changes;

import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeAssertions {
    public static void assertContentChanges(Changes changes) {
        assertThat(changes).isNotNull();
        for (Changes.Entries entry : changes.changes) {
            assertThat(entry.key).isNotNull();
            assertThat(entry.key).isNotEmpty();
            assertThat(entry.items).isNotNull();
            assertThat(entry.items).isNotEmpty();
            for (Changes.Change change : entry.items) {
                assertThat(change).isNotNull();
                assertThat(change.action).isNotNull();
                assertThat(change.id).isNotNull();
                assertThat(change.time).isNotNull();
            }
        }
    }

    public static void assertChangeResultsPage(ChangeResultsPage changeResultsPage) {
        assertBaseResultsPage(changeResultsPage);
        for (ChangeResultsPage.Change change : changeResultsPage.results) {
            assertThat(change).isNotNull();
            assertThat(change.id).isNotNull();
        }
    }
}
