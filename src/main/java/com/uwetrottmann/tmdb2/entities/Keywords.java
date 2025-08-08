// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Keywords {

    public Integer id;

    @SerializedName(value = "keywords", alternate = {"results"})
    public List<BaseKeyword> keywords;

}
