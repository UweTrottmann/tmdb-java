package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class Videos {

    public static class Video {

        public String id;
        public String iso_639_1;
        public String key;
        public String name;
        public String site;
        public Integer size;
        public String type;

    }

    public Integer id;
    public List<Video> results;

}
