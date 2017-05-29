package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.BaseKeyword;
import com.uwetrottmann.tmdb2.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb2.entities.Keywords;

import static com.uwetrottmann.tmdb2.TestData.testKeyword;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

public class KeywordAssertions {
    public static void assertBaseKeyword(BaseKeyword keyword) {
        assertThat(keyword).isNotNull();
        assertThat(keyword.id).isNotNull();
        assertThat(keyword.name).isNotNull();
    }

    public static void assertKeywordDataIntegrity(BaseKeyword keyword) {
        assertBaseKeyword(keyword);
        assertThat(keyword.id).isEqualTo(testKeyword.id);
        assertThat(keyword.name).isEqualTo(testKeyword.name);
    }

    public static void assertKeywords(Keywords keywords) {
        assertThat(keywords).isNotNull();
        assertThat(keywords.keywords).isNotNull();
        assertThat(keywords.keywords).isNotEmpty();

        for (BaseKeyword keyword : keywords.keywords) {
            assertBaseKeyword(keyword);
        }
    }

    public static void assertKeywordResultsPage(KeywordResultsPage keywordResultsPage) {
        assertBaseResultsPage(keywordResultsPage);

        assertThat(keywordResultsPage.results).isNotNull();
        assertThat(keywordResultsPage.results).isNotEmpty();

        for (BaseKeyword keyword : keywordResultsPage.results) {
            assertBaseKeyword(keyword);
        }
    }
}
