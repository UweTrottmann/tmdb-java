package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.enumerations.ReleaseType;
import com.uwetrottmann.tmdb2.utils.Language;
import com.uwetrottmann.tmdb2.utils.TmdbDate;

public class ReleaseDate {
    public String certification;

    @SerializedName(value = "iso_639_1")
    public Language language;

    public String note;
    public TmdbDate release_date;
    public ReleaseType type;
}
