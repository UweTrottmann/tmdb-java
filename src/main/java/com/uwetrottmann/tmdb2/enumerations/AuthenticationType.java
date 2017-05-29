package com.uwetrottmann.tmdb2.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum AuthenticationType {
    ACCOUNT("account"),
    GUEST("guest");

    private static final Map<String, AuthenticationType> lookup = prepareLookup();

    private static Map<String,AuthenticationType> prepareLookup() {
        Map<String,AuthenticationType> atMap = new HashMap<>();
        for (AuthenticationType lang : AuthenticationType.values()) {
            atMap.put(lang.value, lang);
        }
        return atMap;
    }

    private final String value;

    public static AuthenticationType get(String value) {
        return lookup.get(value);
    }

    AuthenticationType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
