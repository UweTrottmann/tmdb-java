// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum CreditType {
    @SerializedName("cast")
    CAST("cast"),
    @SerializedName("crew")
    CREW("crew");

    private static final Map<String, CreditType> lookup = prepareLookup();

    private static Map<String, CreditType> prepareLookup() {
        Map<String, CreditType> ctMap = new HashMap<>();
        for (CreditType creditType : CreditType.values()) {
            ctMap.put(creditType.value, creditType);
        }
        return ctMap;
    }

    private final String value;

    public static CreditType get(String value) {
        return lookup.get(value);
    }

    CreditType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
