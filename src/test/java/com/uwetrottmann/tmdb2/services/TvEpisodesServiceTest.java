// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertTranslations;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisode;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.TvAssertions;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Image;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvEpisodeExternalIds;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import retrofit2.Call;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() throws IOException {
        Call<TvEpisode> call = getUnauthenticatedInstance().tvEpisodesService().episode(
                testTvEpisode.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                "en"
        );

        TvEpisode tvEpisode = call.execute().body();

        assertTvEpisode(tvEpisode);
    }

    @Test
    public void test_episode_with_append_to_response() throws IOException {
        Call<TvEpisode> call = getUnauthenticatedInstance().tvEpisodesService().episode(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                "en",
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.TRANSLATIONS
                )
        );

        TvEpisode tvEpisode = call.execute().body();

        assertTvEpisodeDataIntegrity(tvEpisode);

        // credits
        assertCredits(tvEpisode.credits);

        // images
        assertThat(tvEpisode.images).isNotNull();
        // Temporary TMDB API issue: stills is empty, despite the episode having them (see images test)
        List<Image> stills = tvEpisode.images.stills;
        if (stills != null && !stills.isEmpty()) {
            assertImages(stills);
        }

        // translations
        assertTranslations(tvEpisode.translations);

        // external ids
        TvAssertions.assertTvEpisodeExternalIdsMatch(tvEpisode.external_ids);
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getUnauthenticatedInstance().tvEpisodesService().credits(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Credits credits = call.execute().body();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().tvEpisodesService().changes(
                testTvEpisode.id,
                new TmdbDate(testTvEpisodeChangesStartDate),
                new TmdbDate(testTvEpisodeChangesEndDate),
                null
        );

        Changes changes = call.execute().body();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<TvEpisodeExternalIds> call = getUnauthenticatedInstance().tvEpisodesService().externalIds(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        TvEpisodeExternalIds ids = call.execute().body();

        TvAssertions.assertTvEpisodeExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getUnauthenticatedInstance().tvEpisodesService().images(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Images images = call.execute().body();
        assertThat(images.id).isNotNull();
        assertImages(images.stills);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getUnauthenticatedInstance().tvEpisodesService().videos(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                null
        );

        Videos videos = call.execute().body();
        assertVideos(videos);
    }

}
