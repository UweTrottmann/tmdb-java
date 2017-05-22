/*
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

import com.uwetrottmann.tmdb2.enumerations.Status;

import java.util.List;

public class Movie extends BaseMovie {

    public Collection belongs_to_collection;
    public Integer budget;
    public String homepage;
    public String imdb_id;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public Integer revenue;
    public Integer runtime;
    public List<SpokenLanguage> spoken_languages;
    public Status status;
    public String tagline;

    // Following are used with append_to_response
    public MovieAlternativeTitles alternative_titles;
    public MovieChanges changes;
    public KeywordsResultPage keywords;
    public MovieListResultsPage lists;
    public Images images;
    public Translations translations;
    public Credits credits;
    public ReleaseDatesResults release_dates;
    public MovieResultsPage similar;
    public MovieResultsPage recommendations;
    public ReviewResultsPage reviews;
    public Videos videos;
}
