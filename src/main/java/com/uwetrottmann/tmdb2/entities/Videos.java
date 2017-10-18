package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.VideoType;
import com.uwetrottmann.tmdb2.utils.TmdbLocale;

import java.util.List;

public class Videos {

    public static class Video {

        public String id;
        public TmdbLocale locale;
        public String key;
        public String name;
        public String site;
        public Integer size;
        public VideoType type;

    }

    public Integer id;
    public List<Video> results;

}
