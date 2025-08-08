// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class Configuration {

    public static class ImagesConfiguration {

        public String base_url;
        public String secure_base_url;
        public List<String> poster_sizes;
        public List<String> backdrop_sizes;
        public List<String> profile_sizes;
        public List<String> logo_sizes;
        public List<String> still_sizes;

    }

    public ImagesConfiguration images;
    public List<String> change_keys;

}
