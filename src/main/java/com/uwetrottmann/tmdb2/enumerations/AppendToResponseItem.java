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

    // Applies to Movies, TV Shows, TV Episodes, TV Seasons, People
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
