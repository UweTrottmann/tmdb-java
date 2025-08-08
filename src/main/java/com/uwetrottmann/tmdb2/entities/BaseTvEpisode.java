// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.EpisodeType;
import java.util.Date;

public class BaseTvEpisode extends BaseTvEpisodeRatingObject {

    public String name;
    public String overview;
    public String production_code;
    public Integer runtime;
    public Integer season_number;
    public String still_path;
    public Double vote_average;
    public Integer vote_count;
    public Integer id;

    public Date air_date;
    public Integer episode_number;
    public EpisodeType episode_type;

}
