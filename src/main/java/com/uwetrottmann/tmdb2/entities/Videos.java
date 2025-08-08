// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.VideoType;

import java.util.List;

public class Videos {

    public static class Video {

        public String id;
        public String iso_639_1;
        public String iso_3166_1;
        public String key;
        public String name;
        public String site;
        public Integer size;
        public VideoType type;

    }

    public Integer id;
    public List<Video> results;

}
