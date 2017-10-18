package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("Rumored")
    RUMORED,
    @SerializedName("Planned")
    PLANNED,
    @SerializedName("In Production")
    IN_PRODUCTION,
    @SerializedName("Post Production")
    POST_PRODUCTION,
    @SerializedName("Released")
    RELEASED,
    @SerializedName("Canceled")
    CANCELLED
}
