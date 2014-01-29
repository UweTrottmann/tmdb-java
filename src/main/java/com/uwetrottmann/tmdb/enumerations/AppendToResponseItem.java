package com.uwetrottmann.tmdb.enumerations;


public enum AppendToResponseItem {

    TRAILERS("trailers"),
    RELEASES("releases"),
    CREDITS("credits"),
    SIMILAR("similar_movies");

    private final String value;

    private AppendToResponseItem(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
