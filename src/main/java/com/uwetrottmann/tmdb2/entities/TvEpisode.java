package com.uwetrottmann.tmdb2.entities;

import java.util.Date;
import java.util.List;

public class TvEpisode {

    public Integer id;

    public Date air_date;
    public List<CrewMember> crew;
    public Integer episode_number;
    public List<CastMember> guest_stars;
    public String name;
    public String overview;
    public String production_code;
    public Integer season_number;
    public String still_path;
    public Double vote_average;
    public Integer vote_count;
    public Images images;
    public ExternalIds external_ids;
    public Credits credits;

}
