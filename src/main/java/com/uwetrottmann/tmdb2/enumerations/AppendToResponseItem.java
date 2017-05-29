/*
 * Copyright 2014 Chris Banes
 *
 * Modifications Copyright 2017 Nikolas Mavropoylos
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

package com.uwetrottmann.tmdb2.enumerations;


public enum AppendToResponseItem {
    // Applies to Movies, TV Shows, TV Episodes, TV Seasons, People, Collections
    IMAGES("images"),

    // Applies to Movies, TV Shows, TV Episodes, TV Seasons, People
    CHANGES("changes"),

    // Applies to Movies, TV Shows, TV Episodes, TV Seasons
    CREDITS("credits"),
    VIDEOS("videos"),

    // Applies to Movies, TV Shows
    ALTERNATIVE_TITLES("alternative_titles"),
    KEYWORDS("keywords"),
    RECOMMENDATIONS("recommendations"),
    RELEASE_DATES("release_dates"),
    REVIEWS("reviews"),
    SIMILAR("similar"),
    TRANSLATIONS("translations"),

    // Applies to Movies
    LISTS("lists"),

    // Applies to TV Shows, TV Episodes, TV Seasons, People
    EXTERNAL_IDS("external_ids"),

    // Applies to TV Shows
    CONTENT_RATINGS("content_ratings"),

    // Applies to People
    MOVIE_CREDITS("movie_credits"),
    TV_CREDITS("tv_credits"),
    COMBINED_CREDITS("combined_credits"),
    TAGGED_IMAGES("tagged_images"),

    // Applies to Keywords, Companies
    MOVIES("movies");

    private final String value;

    AppendToResponseItem(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
