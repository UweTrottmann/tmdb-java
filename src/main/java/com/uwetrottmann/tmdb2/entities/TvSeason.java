package com.uwetrottmann.tmdb2.entities;

import java.util.Date;
import java.util.List;

public class TvSeason {

    public Integer id;
    public Date air_date;
    public List<TvEpisode> episodes;
    public Integer episode_count;
    public String name;
    public String overview;
    public String poster_path;
    public Integer season_number;
    public Credits credits;
    public Images images;
    public ExternalIds external_ids;

}
