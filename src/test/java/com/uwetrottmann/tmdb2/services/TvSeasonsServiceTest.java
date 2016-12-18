package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TvSeasonsServiceTest extends BaseTestCase {

    @Test
    public void test_season() throws IOException {
        Call<TvSeason> call = getManager().tvSeasonsService().season(TestData.TVSHOW_ID, 1, null, null);
        TvSeason season = call.execute().body();
        assertTvSeason(season);
    }

    @Test
    public void test_season_with_append_to_response() throws IOException {
        Call<TvSeason> call = getManager().tvSeasonsService().season(
                TestData.TVSHOW_ID, 1, null,
                new AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        );
        TvSeason season = call.execute().body();
        assertTvSeason(season);

        // credits
        assertThat(season.credits).isNotNull();
        assertCrewCredits(season.credits.crew);
        assertCastCredits(season.credits.cast);

        // images
        assertThat(season.images).isNotNull();
        assertImages(season.images.posters);

        // external ids
        assertThat(season.external_ids).isNotNull();
        assertThat(season.external_ids.freebase_id).isNotNull();
        assertThat(season.external_ids.freebase_mid).isNotNull();
        assertThat(season.external_ids.tvdb_id).isNotNull();
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getManager().tvSeasonsService().credits(TestData.TVSHOW_ID, 1);
        Credits credits = call.execute().body();
        assertThat(credits.id).isNotNull();
        assertCrewCredits(credits.crew);
        assertCastCredits(credits.cast);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<ExternalIds> call = getManager().tvSeasonsService().externalIds(TestData.TVSHOW_ID, 1, null);
        ExternalIds ids = call.execute().body();
        assertThat(ids.id).isNotNull();
        assertThat(ids.freebase_id).isNotNull();
        assertThat(ids.freebase_mid).isNotNull();
        assertThat(ids.tvdb_id).isNotNull();
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getManager().tvSeasonsService().images(TestData.TVSHOW_ID, 1, null);
        Images images = call.execute().body();
        assertThat(images.id).isNotNull();
        assertImages(images.posters);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getManager().tvSeasonsService().videos(TestData.TVSHOW_ID, 1, null);
        Videos videos = call.execute().body();
        assertVideos(videos);
    }

    private void assertTvSeason(TvSeason season) {
        assertThat(season.air_date).isNotNull();
        assertThat(season.name).isEqualTo("Season 1");
        assertThat(season.overview).isNotNull();
        assertThat(season.id).isNotNull();
        assertThat(season.poster_path).isNotEmpty();
        assertThat(season.season_number).isEqualTo(1);
        assertThat(season.episodes).isNotEmpty();

        for (TvEpisode episode : season.episodes) {
            assertCrewCredits(episode.crew);
            assertCastCredits(episode.guest_stars);
            assertThat(episode.air_date).isNotNull();
            assertThat(episode.episode_number).isPositive();
            assertThat(episode.name).isNotNull();
            assertThat(episode.overview).isNotNull();
            assertThat(episode.id).isNotNull();
            assertThat(episode.season_number).isEqualTo(1);
            assertThat(episode.still_path).isNotNull();
            assertThat(episode.vote_average).isGreaterThanOrEqualTo(0);
            assertThat(episode.vote_count).isGreaterThanOrEqualTo(0);
        }
    }

}
