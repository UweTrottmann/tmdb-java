package com.uwetrottmann.tmdb2.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum ReleaseType {

    PREMIERE(1),
    THEATRICAL_LIMITED(2),
    THEATRICAL(3),
    DIGITAL(4),
    PHYSICAL(5),
    TV(6);

    public final int id;

    ReleaseType(int id) {
        this.id = id;
    }

    private static final Map<Integer, ReleaseType> lookup = prepareLookup();

    private static Map<Integer, ReleaseType> prepareLookup() {
        Map<Integer, ReleaseType> atMap = new HashMap<>();
        for (ReleaseType type : ReleaseType.values()) {
            atMap.put(type.id, type);
        }
        return atMap;
    }

    public static ReleaseType get(int value) {
        return lookup.get(value);
    }

}
