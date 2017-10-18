package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

public enum MediaType {
    @SerializedName("movie")
    MOVIE,
    @SerializedName("tv")
    TV,
    @SerializedName("person")
    PERSON
}
