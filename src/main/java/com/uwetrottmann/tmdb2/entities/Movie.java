package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.Status;

import java.util.Date;
import java.util.List;

public class Movie {

    public Integer id;

    public Boolean adult;
    public String backdrop_path;
    public Collection belongs_to_collection;
    public Integer budget;
    public List<Genre> genres;
    public String homepage;
    public String imdb_id;
    public String original_title;
    public String original_language;
    public String overview;
    public Double popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public Date release_date;
    public Integer revenue;
    public Integer runtime;
    public List<SpokenLanguage> spoken_languages;
    public Status status;
    public String tagline;
    public String title;
    public Double vote_average;
    public Integer vote_count;

    // Following are used with append_to_response
    public MovieAlternativeTitles alternative_titles;
    public Translations translations;
    public Credits credits;
    public ReleaseDatesResults release_dates;
    public MovieResultsPage similar;
    public Videos videos;
}
