// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.enumerations;

public enum ExternalSource {

    IMDB_ID("imdb_id"),
    FREEBASE_MID("freebase_mid"),
    FREEBASE_ID("freebase_id"),
    TVRAGE_ID("tvrage_id"),
    TVDB_ID("tvdb_id"),
    WIKIDATA_ID("wikidata_id");

    private final String value;

    ExternalSource(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
