package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Language;

public class Review {

    public String id;
    public String author;
    public String content;
    @SerializedName("iso_639_1")
    public Language language;
    public Integer media_id;
    public String media_title;
    public String media_type;
    public String url;

}
