package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class Translations {

    public static class Translation {

        public String iso_3166_1;
        public String iso_639_1;
        public String name;
        public String english_name;
        public Data data;

        public static class Data {

            public String title;
            public String overview;
            public String homepage;
        }
    }

    public Integer id;
    public List<Translation> translations;
}
