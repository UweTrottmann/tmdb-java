package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Country;

public class ContentRating {

    @SerializedName(value = "iso_3166_1")
    public Country country;

    public String rating;

}
