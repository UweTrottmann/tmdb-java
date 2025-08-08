// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.enumerations;

public enum TimeWindow {
    DAY("day"),
    WEEK("week");

    private final String value;

    TimeWindow(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
