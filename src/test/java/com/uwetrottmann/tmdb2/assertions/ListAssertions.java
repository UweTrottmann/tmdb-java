package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseList;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;

import static com.uwetrottmann.tmdb2.TestData.testList;
import static com.uwetrottmann.tmdb2.TestData.testListMovies;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertBaseMovie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class ListAssertions {
    public static void assertListResultsPage(ListResultsPage listResultsPage) {
        assertBaseResultsPage(listResultsPage);

        for (BaseList list : listResultsPage.results) {
            assertBaseList(list);
        }

    }

    public static void assertBaseList(BaseList baseList) {
        assertThat(baseList).isNotNull();
        assertThat(baseList.description).isNotNull();
        assertThat(baseList.id).isNotNull();
        assertThat(baseList.favorite_count).isNotNull();
        assertThat(baseList.favorite_count).isGreaterThanOrEqualTo(0);
        assertThat(baseList.item_count).isNotNull();
        assertThat(baseList.item_count).isGreaterThanOrEqualTo(0);
        assertThat(baseList.iso_639_1).isNotNull();
        assertThat(baseList.name).isNotNull();

        try {
            List list = (List) baseList;

            assertThat(list.list_type).isNull();
        } catch (Exception exc) {
            assertThat(baseList.list_type).isNotNull();
        }
    }

    public static void assertList(List list) {
        assertBaseList(list);

        assertThat(list.created_by).isNotNull();
        assertThat(list.items).isNotNull();
        assertThat(list.items).isNotEmpty();
        for (BaseMovie movie : list.items) {
            assertBaseMovie(movie);
        }

        assertThat(list.list_type).isNull();
    }

    public static void assertListDataIntegrity(List list) {
        assertList(list);
        assertThat(list.created_by).isEqualTo(testList.created_by);
        assertThat(list.name).isEqualTo(testList.name);
        assertThat(list.description).isEqualTo(testList.description);

        Boolean found;
        for (BaseMovie movie : list.items) {
            found = false;
            for (Integer i : testListMovies) {
                if (movie.id.equals(i)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                fail("Id not in testList");
        }
    }
}
