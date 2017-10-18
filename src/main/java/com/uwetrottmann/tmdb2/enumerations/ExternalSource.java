package com.uwetrottmann.tmdb2.enumerations;

import com.google.gson.annotations.SerializedName;

public enum ExternalSource {
    @SerializedName("imdb_id")
    IMDB_ID,
    @SerializedName("freebase_mid")
    FREEBASE_MID,
    @SerializedName("freebase_id")
    FREEBASE_ID,
    @SerializedName("tvrage_id")
    TVRAGE_ID,
    @SerializedName("tvdb_id")
    TVDB_ID
}
