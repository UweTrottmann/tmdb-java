// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AlternativeTitles {

    @SerializedName(value = "titles", alternate = {"results"})
    public List<AlternativeTitle> titles;

    public Integer id;

}
