// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.Date;
import java.util.List;

public class TvShow extends BaseTvShow {

    public List<Person> created_by;
    public List<Network> networks;
    public List<Integer> episode_run_time;
    public List<Genre> genres;
    public String homepage;
    public boolean in_production;
    public List<String> languages;
    public Date last_air_date;
    public Integer number_of_episodes;
    public Integer number_of_seasons;
    public List<BaseCompany> production_companies;
    public List<TvSeason> seasons;
    public String status;
    public String tagline;
    public String type;

    // Following are used with append_to_response
    public Images images;
    public Credits credits;
    public TvExternalIds external_ids;
    public AlternativeTitles alternative_titles;
    public Changes changes;
    public Keywords keywords;
    public TvShowResultsPage recommendations;
    public Translations translations;
    public ContentRatings content_ratings;
    public TvShowResultsPage similar;
    public Videos videos;
    
    public BaseTvEpisode last_episode_to_air;
    public BaseTvEpisode next_episode_to_air;
}
