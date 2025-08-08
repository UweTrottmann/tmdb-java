// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.Date;
import java.util.List;

public class BaseTvShow extends BaseRatingObject {

    public Integer id;
    public String original_name;
    public String original_language;
    public String overview;
    public String name;
    public List<String> origin_country;
    public List<Integer> genre_ids;
    public Date first_air_date;
    public String backdrop_path;
    public String poster_path;
    public Double popularity;
    public Double vote_average;
    public Integer vote_count;

    public String media_type;
}
