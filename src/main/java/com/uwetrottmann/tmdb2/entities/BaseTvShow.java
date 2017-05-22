/*
 * Copyright 2017 Nikolas Mavropoylos
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

public class BaseTvShow {

    public Integer id;
    public String original_name;
    public String original_language;
    public String overview;
    public String name;
    public List<String> origin_country;
    public List<Integer> genre_ids;
    public Date first_air_date;
    public String backdrop_path;
    public String poster_path;
    public Double popularity;
    public Double vote_average;
    public Integer vote_count;

}
