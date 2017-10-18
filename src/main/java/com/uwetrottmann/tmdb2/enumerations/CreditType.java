package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

public enum CreditType {
    @SerializedName("cast")
    CAST,
    @SerializedName("crew")
    CREW;
}
