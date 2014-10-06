/*
 * Copyright 2014 Uwe Trottmann
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
import com.uwetrottmann.tmdb.entities.TvEpisode;
import com.uwetrottmann.tmdb.entities.TvSeason;

import static org.fest.assertions.api.Assertions.assertThat;

public class TvSeasonsServiceTest extends BaseTestCase {

    public void test_season() {
        TvSeason season = getManager().tvSeasonsService().season(TestData.TVSHOW_ID, 1, null);
        assertThat(season.season_number).isEqualTo(1);
        assertThat(season.air_date).isNotNull();
        assertThat(season.episodes).isNotEmpty();
        for (TvEpisode episode : season.episodes) {
            assertThat(episode.season_number).isEqualTo(1);
            assertThat(episode.episode_number).isPositive();
        }
    }

}
