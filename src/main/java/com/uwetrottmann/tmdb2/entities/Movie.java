package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.Status;

import java.util.List;

public class Movie extends BaseMovie {

    public Collection belongs_to_collection;
    public Integer budget;
    public String homepage;
    public String imdb_id;
    public List<BaseCompany> production_companies;
    public List<Country> production_countries;
    public Integer revenue;
    public Integer runtime;
    public List<SpokenLanguage> spoken_languages;
    public Status status;
    public String tagline;

    // Following are used with append_to_response
    public AlternativeTitles alternative_titles;
    public Changes changes;
    public Keywords keywords;
    public ListResultsPage lists;
    public Images images;
    public Translations translations;
    public Credits credits;
    public MovieExternalIds external_ids;
    public ReleaseDatesResults release_dates;
    public MovieResultsPage similar;
    public MovieResultsPage recommendations;
    public ReviewResultsPage reviews;
    public Videos videos;

}
