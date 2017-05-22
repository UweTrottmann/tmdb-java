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

import java.util.List;

public class MovieListResultsPage extends BaseResultsPage {

    public static class Result {
        public Integer id;
        public String description;
        public Integer favorite_count;
        public Integer item_count;
        public String iso_639_1;
        public String list_type;
        public String name;
        public String poster_path;
    }

    public Integer id;
    public List<Result> results;
}
