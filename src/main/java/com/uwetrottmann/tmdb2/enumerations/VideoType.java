package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

public enum VideoType {
    @SerializedName("Trailer")
    TRAILER,
    @SerializedName("Teaser")
    TEASER,
    @SerializedName("Clip")
    CLIP,
    @SerializedName("Featurette")
    FEATURETTE,
    @SerializedName("Opening Credits")
    OPENING_CREDITS
}
