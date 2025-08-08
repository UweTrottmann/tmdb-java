// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum VideoType {

    TRAILER("Trailer"),
    TEASER("Teaser"),
    CLIP("Clip"),
    FEATURETTE("Featurette"),
    OPENING_CREDITS("Opening Credits");


    VideoType(String value) {
        this.value = value;
    }

    private static final Map<String, VideoType> lookup = prepareLookup();

    private static Map<String, VideoType> prepareLookup() {
        Map<String, VideoType> mtMap = new HashMap<>();
        for (VideoType videoType : VideoType.values()) {
            mtMap.put(videoType.value, videoType);
        }
        return mtMap;
    }

    private final String value;

    public static VideoType get(String value) {
        return lookup.get(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
