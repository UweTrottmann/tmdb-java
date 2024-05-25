package com.uwetrottmann.tmdb2.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum EpisodeType {
    STANDARD("standard"),
    MID_SEASON_FINALE("mid_season"),
    FINALE("finale");

    private final String value;

    EpisodeType(String value) { this.value = value; }

    private static final Map<String, EpisodeType> STRING_MAPPING = new HashMap<>();

    static {
        for (EpisodeType via : EpisodeType.values()) {
            STRING_MAPPING.put(via.toString().toUpperCase(), via);
        }
    }

    public static EpisodeType fromValue(String value) {
        return STRING_MAPPING.get(value.toUpperCase());
    }

    @Override
    public String toString() { return value; }
}
