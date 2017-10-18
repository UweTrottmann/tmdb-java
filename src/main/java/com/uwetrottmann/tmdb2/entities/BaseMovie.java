package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.utils.Language;
import com.uwetrottmann.tmdb2.utils.TmdbDate;

import java.util.List;

public class BaseMovie extends BaseRatingObject {

    public Integer id;

    public Boolean adult;
    public String backdrop_path;
    public List<Genre> genres;
    public List<Integer> genre_ids;
    public String original_title;
    public Language original_language;
    public String overview;
    public Double popularity;
    public String poster_path;
    public TmdbDate release_date;
    public String title;
    public Double vote_average;
    public Integer vote_count;

    public String media_type;

}
