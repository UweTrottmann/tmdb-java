// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class Collection extends BaseCollection {

    public List<BaseMovie> parts;
    public String overview;

    public Images images;

}
