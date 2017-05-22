/*
 * Copyright 2015 Miguel Teixeira
 *
 * Modifications Copyright 2016 Nikolas Mavropoylos
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

public class TvShow extends BaseTvShow {

    public List<Person> created_by;
    public List<Network> networks;
    public List<Integer> episode_run_time;
    public List<Genre> genres;
    public String homepage;
    public boolean in_production;
    public List<String> languages;
    public Date last_air_date;
    public Integer number_of_episodes;
    public Integer number_of_seasons;
    public List<ProductionCompany> production_companies;
    public List<TvSeason> seasons;
    public String status;
    public String type;
    public Images images;
    public Credits credits;

    // Following are used with append_to_response
    public ExternalIds external_ids;
    public TvAlternativeTitles alternative_titles;
    public TvShowChanges changes;
    public KeywordsResultPage keywords;
    public TvResultsPage recommendations;
    public Translations translations;
    public TvContentRatings content_ratings;
    public TvResultsPage similar;
    public Videos videos;
}
