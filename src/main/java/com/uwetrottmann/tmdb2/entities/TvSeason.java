package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TvSeason extends BaseTvSeason {

    public String _id;

    public List<TvEpisode> episodes;
    public String name;
    public String overview;

    public Credits credits;
    public Images images;
    public Videos videos;
    public TvSeasonExternalIds external_ids;

}
