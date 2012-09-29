
package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.Entity;

public class Movie implements Entity {
    private static final long serialVersionUID = 4604935751051141456L;

    public Integer id;

    public Boolean adult;
    public String backdrop_path;
    // TODO belongs_to_collection
    public Integer budget;
    // TODO genres
    public String homepage;
    public String imdb_id;
    public String original_title;
    public String overview;
    public Double popularity;
    public String poster_path;
    // TODO production_companies
    // TODO production_countries
    public String release_date;
    public Integer revenue;
    public Integer runtime;
    // TODO spoken_languages
    public String tagline;
    public String title;
    public Double vote_average;
    public Integer vote_count;
}
