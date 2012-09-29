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

package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.TmdbEntity;

import java.util.List;

public class ResultsPage implements TmdbEntity {
    private static final long serialVersionUID = -5167211774471598391L;

    public Integer page;
    public List<Movie> results;
    public Integer total_pages;
    public Integer total_results;
}
