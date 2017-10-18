package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.utils.TmdbLocale;

public class Account {

    public static class Avatar {

        public static class GRAvatar {

            public String hash;

        }

        public GRAvatar gravatar;

    }

    public Integer id;
    public TmdbLocale locale;
    public String name;
    public Boolean include_adult;
    public String username;
    public Avatar avatar;

}
