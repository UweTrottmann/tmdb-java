package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Country;

import java.util.List;

public class ReleaseDatesResult {

    @SerializedName("iso_3166_1")
    public Country country;
    public List<ReleaseDate> release_dates;

}
