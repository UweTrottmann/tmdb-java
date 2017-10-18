package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.utils.Language;
import com.uwetrottmann.tmdb2.utils.TmdbDate;

import java.util.List;

public class BaseTvShow extends BaseRatingObject {

    public Integer id;
    public String original_name;
    public Language original_language;
    public String overview;
    public String name;
    public List<String> origin_country;
    public List<Integer> genre_ids;
    public TmdbDate first_air_date;
    public String backdrop_path;
    public String poster_path;
    public Double popularity;
    public Double vote_average;
    public Integer vote_count;

    public String media_type;
}
