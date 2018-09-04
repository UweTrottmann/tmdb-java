package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testKeyword;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertBaseKeyword;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertKeywordDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Keyword;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class KeywordsServiceTest extends BaseTestCase {

    @Test
    public void test_keyword() throws IOException {
        Observable<Keyword> call = getUnauthenticatedInstance().rx.keywordsService().summary(
                testKeyword.id
        );

        Keyword keyword = call.singleOrError().blockingGet();

        assertKeywordDataIntegrity(keyword);
    }

    @Test
    public void test_keyword_append_movies() throws IOException {
        Observable<Keyword> call = getUnauthenticatedInstance().rx.keywordsService().summary(
                testKeyword.id,
                new AppendToResponse(
                        AppendToResponseItem.MOVIES
                )
        );

        Keyword keyword = call.singleOrError().blockingGet();

        assertBaseKeyword(keyword);

        assertMovieResultsPage(keyword.movies);
    }

    @Test
    public void test__movies() throws IOException {
        Observable<MovieResultsPage> call = getUnauthenticatedInstance().rx.keywordsService().movies(
                testKeyword.id
        );

        MovieResultsPage movieResultsPage = call.singleOrError().blockingGet();

        assertMovieResultsPage(movieResultsPage);
    }
}
