package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import com.uwetrottmann.tmdb2.enumerations.ReleaseType;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscoverServiceTest extends BaseTestCase {

    @Test
    public void test_discover_movie() throws IOException {
        Call<MovieResultsPage> call = getManager().discoverMovie()
                .page(1)
                .primary_release_date_gte(new TmdbDate("1990-01-01"))
                .sort_by(SortBy.RELEASE_DATE_DESC)
                .with_cast(new DiscoverFilter(TestData.PERSON_ID_BRAD_PITT))
                .with_crew(new DiscoverFilter(TestData.PERSON_ID_DAVID_FINCHER))
                .without_genres(new DiscoverFilter(TestData.GENRE_ID_ROMANCE))
                .with_release_type(new DiscoverFilter(DiscoverFilter.Separator.OR,
                        ReleaseType.THEATRICAL, ReleaseType.DIGITAL))
                .build();
        MovieResultsPage results = call.execute().body();
        assertResultsPage(results);
        assertThat(results.results).isNotEmpty();
    }

    @Test
    public void test_discover_tv() throws IOException {
        Call<TvResultsPage> call = getManager().discoverTv()
                .sort_by(SortBy.VOTE_AVERAGE_DESC)
                .with_genres(new DiscoverFilter(TestData.GENRE_ID_DRAMA, TestData.GENRE_ID_SCIFI))
                .with_networks(new DiscoverFilter(TestData.NETWORK_ID_HBO))
                .first_air_date_gte(new TmdbDate("2010-01-01"))
                .first_air_date_lte(new TmdbDate("2017-01-01"))
                .build();
        TvResultsPage results = call.execute().body();
        assertResultsPage(results);
        assertThat(results.results).isNotEmpty();
    }

    private void assertResultsPage(BaseResultsPage results) {
        assertThat(results.page).isPositive();
        assertThat(results.total_pages).isPositive();
        assertThat(results.total_results).isPositive();
    }

}
