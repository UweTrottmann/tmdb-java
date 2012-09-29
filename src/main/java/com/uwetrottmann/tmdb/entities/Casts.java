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

public class Casts implements TmdbEntity {
    private static final long serialVersionUID = -7947291466017850804L;

    public static class CastMember implements TmdbEntity {
        private static final long serialVersionUID = -6164786116196155740L;

        public Integer id;
        public String name;
        public String character;
        public Integer order;
        public String profile_path;
    }

    public static class CrewMember implements TmdbEntity {
        private static final long serialVersionUID = -6267166779666363892L;

        public Integer id;
        public String name;
        public String department;
        public String job;
        public String profile_path;
    }

    public Integer id;
    public List<CastMember> cast;
    public List<CrewMember> crew;

}
