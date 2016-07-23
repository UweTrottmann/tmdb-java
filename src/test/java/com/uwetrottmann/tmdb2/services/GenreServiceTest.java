package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.TvAlternativeTitles;
import com.uwetrottmann.tmdb2.entities.TvKeywords;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvShowComplete;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreServiceTest extends BaseTestCase {

    @Test
    public void test_movie() throws IOException {
        Call<GenreResults> call = getManager().genreService().movie(null);
        GenreResults results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.genres).isNotEmpty();
    }

    @Test
    public void test_tv() throws IOException {
        Call<GenreResults> call = getManager().genreService().tv(null);
        GenreResults results = call.execute().body();
        assertThat(results).isNotNull();
        assertThat(results.genres).isNotEmpty();
    }

}
