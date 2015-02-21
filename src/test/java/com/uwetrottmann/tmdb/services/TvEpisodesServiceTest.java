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

import java.util.List;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.CastMember;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.CrewMember;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import com.uwetrottmann.tmdb.entities.Image;
import com.uwetrottmann.tmdb.entities.TvEpisode;
import com.uwetrottmann.tmdb.entities.TvEpisodeImages;
import com.uwetrottmann.tmdb.entities.Videos;
import com.uwetrottmann.tmdb.entities.Videos.Video;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() {
        TvEpisode episode = getManager().tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null);
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
    
    @Test
    public void test_credits() {
        Credits credits = getManager().tvEpisodesService().credits(TestData.TVSHOW_ID, 1, 1);
        assertThat(credits.id).isNotNull();
        assertCrewCredits(credits.crew);
        assertCastCredits(credits.cast);
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
        TvEpisodeImages images = getManager().tvEpisodesService().images(TestData.TVSHOW_ID, 1, 1);
        assertThat(images.id).isNotNull();
        
        for (Image image : images.stills) {
            assertThat(image.file_path).isNotEmpty();
            assertThat(image.width).isNotNull();
            assertThat(image.height).isNotNull();
            assertThat(image.aspect_ratio).isGreaterThan(0);
            assertThat(image.vote_average).isGreaterThanOrEqualTo(0);
            assertThat(image.vote_count).isGreaterThanOrEqualTo(0);
        }
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
    
    private void assertCrewCredits(List<CrewMember> crew) {
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
        assertThat(cast).isNotEmpty();
        
        for (CastMember member : cast) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.character).isNotNull();
            assertThat(member.order).isNotNull();
        }
    }

}
