/*
 * Copyright 2015 Miguel Teixeira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.ExternalIds;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() throws IOException {
        Call<TvEpisode> call = getManager().tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null, null);
        TvEpisode episode = call.execute().body();
        assertTvEpisode(episode);
    }

    @Test
    public void test_episode_with_append_to_response() throws IOException {
        Call<TvEpisode> call = getManager().tvEpisodesService().episode(
                TestData.TVSHOW_ID, 1, 1, null,
                new AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        );
        TvEpisode episode = call.execute().body();
        assertTvEpisode(episode);

        // credits
        assertThat(episode.credits).isNotNull();
        assertCrewCredits(episode.credits.crew);
        assertCastCredits(episode.credits.guest_stars);
        assertCastCredits(episode.credits.cast);

        assertThat(episode.crew).isNotNull();
        assertThat(episode.guest_stars).isNotNull();
        assertCrewCredits(episode.crew);
        assertCastCredits(episode.guest_stars);

        // images
        assertThat(episode.images).isNotNull();
        assertImages(episode.images.stills);

        // external ids
        assertThat(episode.external_ids.freebase_id).isNotNull();
        assertThat(episode.external_ids.freebase_mid).isNotNull();
        assertThat(episode.external_ids.tvdb_id).isNotNull();
        assertThat(episode.external_ids.imdb_id).isNotNull();
        assertThat(episode.external_ids.tvrage_id).isNotNull();
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getManager().tvEpisodesService().credits(TestData.TVSHOW_ID, 1, 1);
        Credits credits = call.execute().body();
        assertThat(credits.id).isNotNull();
        assertCrewCredits(credits.crew);
        assertCastCredits(credits.cast);
        assertCastCredits(credits.guest_stars);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<ExternalIds> call = getManager().tvEpisodesService().externalIds(TestData.TVSHOW_ID, 1, 1);
        ExternalIds ids = call.execute().body();
        assertThat(ids.id).isNotNull();
        assertThat(ids.freebase_id).isNotNull();
        assertThat(ids.freebase_mid).isNotNull();
        assertThat(ids.tvdb_id).isNotNull();
        assertThat(ids.imdb_id).isNotNull();
        assertThat(ids.tvrage_id).isNotNull();
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getManager().tvEpisodesService().images(TestData.TVSHOW_ID, 1, 1);
        Images images = call.execute().body();
        assertThat(images.id).isNotNull();
        assertImages(images.stills);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getManager().tvEpisodesService().videos(TestData.TVSHOW_ID, 1, 1);
        Videos videos = call.execute().body();
        assertVideos(videos);
    }

    private void assertTvEpisode(TvEpisode episode) {
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
