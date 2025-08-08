// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonPrimitive;

import java.util.Date;
import java.util.List;

public class Person extends BasePerson {

    public List<JsonPrimitive> also_known_as;
    public String biography;
    public Date birthday;
    public Date deathday;
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
