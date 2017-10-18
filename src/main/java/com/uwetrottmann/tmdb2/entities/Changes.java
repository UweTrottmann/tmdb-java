package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.uwetrottmann.tmdb2.utils.Language;

import java.util.List;

public class Changes {

    public static class Change {

        public String id;
        public String action;
        public String time;

        @SerializedName(value = "iso_639_1")
        public Language language;

        public JsonElement value;
        public JsonElement original_value;
    }

    public static class Entries {
        public String key;
        public List<Change> items;
    }

    public List<Entries> changes;

}
