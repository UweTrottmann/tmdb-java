package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.utils.TmdbDate;

public class BaseTvEpisode extends BaseTvEpisodeRatingObject {

    public String name;
    public String overview;
    public String production_code;
    public Integer season_number;
    public String still_path;
    public Double vote_average;
    public Integer vote_count;
    public Integer id;

    public TmdbDate air_date;
    public Integer episode_number;

}
