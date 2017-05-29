/*
 * Copyright 2014 Chris Banes
 *
 * Modifications Copyright 2017 Nikolas Mavropoylos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

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
