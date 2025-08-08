// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

public class Account {

    public static class Avatar {

        public static class GRAvatar {

            public String hash;

        }

        public GRAvatar gravatar;

    }

    public Integer id;
    public String iso_639_1;
    public String iso_3166_1;
    public String name;
    public Boolean include_adult;
    public String username;
    public Avatar avatar;

}
