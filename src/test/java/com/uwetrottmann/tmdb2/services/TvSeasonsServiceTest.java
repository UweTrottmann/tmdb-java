package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvSeasonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvSeasonChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvSeason;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvSeasonDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.TvAssertions;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvSeasonExternalIds;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import java.io.IOException;
import org.junit.Test;
import retrofit2.Call;

public class TvSeasonsServiceTest extends BaseTestCase {

    @Test
    public void test_season() throws IOException {
        Call<TvSeason> call = getUnauthenticatedInstance().tvSeasonsService().season(
                testTvShow.id,
                testTvSeason.season_number,
                "en"
        );

        TvSeason tvSeason = call.execute().body();

        assertTvSeason(tvSeason);
    }

    @Test
    public void test_season_with_append_to_response() throws IOException {
        Call<TvSeason> call = getUnauthenticatedInstance().tvSeasonsService().season(
                testTvShow.id,
                testTvSeason.season_number,
                "en",
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS
                )
        );

        TvSeason tvSeason = call.execute().body();

        assertTvSeasonDataIntegrity(tvSeason);

        // credits
        assertCredits(tvSeason.credits);

        // images
        assertThat(tvSeason.images).isNotNull();
        assertImages(tvSeason.images.posters);

        // external ids
        TvAssertions.assertTvSeasonExternalIdsMatch(tvSeason.external_ids);
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getUnauthenticatedInstance().tvSeasonsService().credits(
                testTvShow.id,
                testTvSeason.season_number
        );

        Credits credits = call.execute().body();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().tvSeasonsService().changes(
                testTvSeason.id,
                new TmdbDate(testTvSeasonChangesStartDate),
                new TmdbDate(testTvSeasonChangesEndDate),
                null
        );

        Changes changes = call.execute().body();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<TvSeasonExternalIds> call = getUnauthenticatedInstance().tvSeasonsService().externalIds(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );

        TvSeasonExternalIds ids = call.execute().body();

        TvAssertions.assertTvSeasonExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getUnauthenticatedInstance().tvSeasonsService().images(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );

        Images images = call.execute().body();
        assertThat(images.id).isNotNull();
        assertImages(images.posters);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getUnauthenticatedInstance().tvSeasonsService().videos(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );
        Videos videos = call.execute().body();

        assertVideos(videos);
    }

}
