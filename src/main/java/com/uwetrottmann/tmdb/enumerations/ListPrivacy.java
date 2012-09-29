
package com.uwetrottmann.tmdb.enumerations;

import com.uwetrottmann.tmdb.TraktEnumeration;

import java.util.HashMap;
import java.util.Map;

public enum ListPrivacy implements TraktEnumeration {
    Public("public"),
    Friends("friends"),
    Private("private");

    private final String value;

    private ListPrivacy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    private static final Map<String, ListPrivacy> STRING_MAPPING = new HashMap<String, ListPrivacy>();

    static {
        for (ListPrivacy via : ListPrivacy.values()) {
            STRING_MAPPING.put(via.toString().toUpperCase(), via);
        }
    }

    public static ListPrivacy fromValue(String value) {
        return STRING_MAPPING.get(value.toUpperCase());
    }
}
