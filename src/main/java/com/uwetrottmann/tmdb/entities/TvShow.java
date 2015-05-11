package com.uwetrottmann.tmdb.entities;

import java.util.Date;
import java.util.List;

public class TvShow {

    public Integer id;
    public String original_name;
    public String name;
    public List<String> origin_country;
    public Date first_air_date;
    public String backdrop_path;
    public String poster_path;
    public Double popularity;
    public Double vote_average;
    public Integer vote_count;

}
