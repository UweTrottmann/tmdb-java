// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class BasePerson {

    public String profile_path;
    public Boolean adult;
    public Integer id;
    public String name;
    public Double popularity;
    public List<Media> known_for;

}
