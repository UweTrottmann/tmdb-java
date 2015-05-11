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

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.AppendToResponse;
import com.uwetrottmann.tmdb.entities.CastMember;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.CrewMember;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import com.uwetrottmann.tmdb.entities.Image;
import com.uwetrottmann.tmdb.entities.TvEpisode;
import com.uwetrottmann.tmdb.entities.Images;
import com.uwetrottmann.tmdb.entities.Videos;
import com.uwetrottmann.tmdb.entities.Videos.Video;
import com.uwetrottmann.tmdb.enumerations.AppendToResponseItem;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() {
        TvEpisode episode = getManager().tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null, null);
        assertTvEpisode(episode);
    }

    @Test
    public void test_episode_with_append_to_response() {
        TvEpisode episode = getManager().tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null,
                new AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS, AppendToResponseItem.CREDITS));
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
    public void test_credits() {
        Credits credits = getManager().tvEpisodesService().credits(TestData.TVSHOW_ID, 1, 1);
        assertThat(credits.id).isNotNull();
        assertCrewCredits(credits.crew);
        assertCastCredits(credits.cast);
        assertCastCredits(credits.guest_stars);
    }

    @Test
    public void test_externalIds() {
        ExternalIds ids = getManager().tvEpisodesService().externalIds(TestData.TVSHOW_ID, 1, 1);
        assertThat(ids.id).isNotNull();
        assertThat(ids.freebase_id).isNotNull();
        assertThat(ids.freebase_mid).isNotNull();
        assertThat(ids.tvdb_id).isNotNull();
        assertThat(ids.imdb_id).isNotNull();
        assertThat(ids.tvrage_id).isNotNull();
    }

    @Test
    public void test_images() {
        Images images = getManager().tvEpisodesService().images(TestData.TVSHOW_ID, 1, 1);
        assertThat(images.id).isNotNull();
        assertImages(images.stills);
    }

    @Test
    public void test_videos() {
        Videos videos = getManager().tvEpisodesService().videos(TestData.TVSHOW_ID, 1, 1);
        assertThat(videos.id).isNotNull();

        for (Video video : videos.results) {
            assertThat(video).isNotNull();
            assertThat(video.id).isNotNull();
            assertThat(video.iso_639_1).isNotNull();
            assertThat(video.key).isNotNull();
            assertThat(video.name).isNotNull();
            assertThat(video.site).isNotNull();
            assertThat(video.size).isNotNull();
            assertThat(video.type).isNotNull();
        }
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

    private void assertCrewCredits(List<CrewMember> crew) {
        assertThat(crew).isNotNull();
        assertThat(crew).isNotEmpty();

        for (CrewMember member : crew) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.department).isNotNull();
            assertThat(member.job).isNotNull();
        }
    }

    private void assertCastCredits(List<CastMember> cast) {
        assertThat(cast).isNotNull();
        assertThat(cast).isNotEmpty();

        for (CastMember member : cast) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.character).isNotNull();
            assertThat(member.order).isNotNull();
        }
    }

    private void assertImages(List<Image> images){
        assertThat(images).isNotNull();
        assertThat(images).isNotEmpty();

        for (Image image : images) {
            assertThat(image.file_path).isNotEmpty();
            assertThat(image.width).isNotNull();
            assertThat(image.height).isNotNull();
            assertThat(image.aspect_ratio).isGreaterThan(0);
            assertThat(image.vote_average).isGreaterThanOrEqualTo(0);
            assertThat(image.vote_count).isGreaterThanOrEqualTo(0);
        }
    }
}
