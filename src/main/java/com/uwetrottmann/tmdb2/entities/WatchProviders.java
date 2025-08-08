// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class WatchProviders {

    public Integer id;
    /**
     * Mapped by ISO 3166-1 two-letter country code, like DE and US.
     */
    @Nonnull public Map<String, CountryInfo> results = new HashMap<>();

    public static class CountryInfo {

        /**
         * Link to this page to display all options and provide deep links to the actual providers (and to support
         * TMDB).
         */
        public String link;
        @Nonnull public java.util.List<WatchProvider> flatrate = new ArrayList<>();
        @Nonnull public java.util.List<WatchProvider> free = new ArrayList<>();
        @Nonnull public java.util.List<WatchProvider> ads = new ArrayList<>();
        @Nonnull public java.util.List<WatchProvider> buy = new ArrayList<>();
        /**
         * Only for movies.
         */
        @Nonnull public java.util.List<WatchProvider> rent = new ArrayList<>();

    }

    public static class WatchProvider {
        public Integer display_priority;
        public String logo_path;
        public Integer provider_id;
        public String provider_name;
    }

}
