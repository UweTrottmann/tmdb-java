package com.uwetrottmann.tmdb2.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum Status {

    RUMORED("Rumored"),
    PLANNED("Planned"),
    IN_PRODUCTION("In Production"),
    POST_PRODUCTION("Post Production"),
    RELEASED("Released"),
    CANCELLED("Cancelled");

    public final String value;

    Status(String value) {
        this.value = value;
    }

    private static final Map<String, Status> STRING_MAPPING = new HashMap<>();

    static {
        for (Status via : Status.values()) {
            STRING_MAPPING.put(via.toString().toUpperCase(), via);
        }
    }

    public static Status fromValue(String value) {
        return STRING_MAPPING.get(value.toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }

}
