/*
 * Copyright 2012 Uwe Trottmann
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
    public String overview;
    public Double popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public Date release_date;
    public Integer revenue;
    public Integer runtime;
    public List<SpokenLanguage> spoken_languages;
    public String tagline;
    public String title;
    public Double vote_average;
    public Integer vote_count;

    // Following are used with append_to_response
    public Videos videos;
    public ReleaseDatesResults release_dates;
    public Credits credits;
    public MovieResultsPage similar;
}
