package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Language;

public class BaseList {

    public Integer id;
    public String description;
    public Integer favorite_count;
    public Integer item_count;

    @SerializedName(value = "iso_639_1")
    public Language language;

    public String name;
    public String poster_path;
    public String list_type;

}
