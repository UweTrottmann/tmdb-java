package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Language;

public class Image {

    public String file_path;
    public Integer width;
    public Integer height;

    @SerializedName(value = "iso_639_1")
    public Language language;

    public Double aspect_ratio;
    public Double vote_average;
    public Integer vote_count;

}
