package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testMovieGenreRomance;
import static com.uwetrottmann.tmdb2.TestData.testNetwork;
import static com.uwetrottmann.tmdb2.TestData.testPersonCast;
import static com.uwetrottmann.tmdb2.TestData.testPersonCrew;
import static com.uwetrottmann.tmdb2.TestData.testTvGenreDrama;
import static com.uwetrottmann.tmdb2.TestData.testTvGenreSciFi;
import static com.uwetrottmann.tmdb2.assertions.MovieAssertions.assertMovieResultsPage;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowResultsPage;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class DiscoverServiceTest extends BaseTestCase {

    @Test
    public void test_discover_movie() throws IOException {
        Call<MovieResultsPage> call = getUnauthenticatedInstance().discoverMovie()
                .page(1)
                .primary_release_date_gte(new TmdbDate("1990-01-01"))
                .sort_by(SortBy.RELEASE_DATE_DESC)
                .with_cast(new DiscoverFilter(testPersonCast.id))
                .with_crew(new DiscoverFilter(testPersonCrew.id))
                .without_genres(new DiscoverFilter(testMovieGenreRomance.id))
                // 'cult film', 'insomnia' or 'based on short story'
                .with_keywords(new DiscoverFilter(DiscoverFilter.Separator.OR, 34117, 4142, 156866))
                // filter seems broken in combination with others, temporarily disable
//                .with_release_type(new DiscoverFilter(DiscoverFilter.Separator.OR,
//                        ReleaseType.THEATRICAL, ReleaseType.DIGITAL))
                .build();
        MovieResultsPage results = call.execute().body();

        assertMovieResultsPage(results);
    }

    @Test
    public void test_discover_tv() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().discoverTv()
                .sort_by(SortBy.VOTE_AVERAGE_DESC)
                .with_genres(new DiscoverFilter(testTvGenreDrama.id, testTvGenreSciFi.id))
                .with_networks(new DiscoverFilter(testNetwork.id))
                .first_air_date_gte(new TmdbDate("2010-01-01"))
                .first_air_date_lte(new TmdbDate("2017-01-01"))
                .build();
        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

}
