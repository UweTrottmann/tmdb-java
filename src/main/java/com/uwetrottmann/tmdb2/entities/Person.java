package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonPrimitive;
import com.uwetrottmann.tmdb2.utils.TmdbDate;

import java.util.List;

public class Person extends BasePerson {

    public List<JsonPrimitive> also_known_as;
    public String biography;
    public TmdbDate birthday;
    public TmdbDate deathday;
    public Integer gender;
    public String homepage;
    public String imdb_id;
    public String place_of_birth;

    public PersonExternalIds external_ids;
    public PersonCredits combined_credits;
    public PersonCredits movie_credits;
    public PersonCredits tv_credits;
    public PersonImages images;
    public TaggedImagesResultsPage tagged_images;
    public Changes changes;

}
