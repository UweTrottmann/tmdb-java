package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonElement;

import java.util.List;

public class Changes {

    public static class Change {

        public String id;
        public String action;
        public String time;
        public String iso_639_1;
        public JsonElement value;
        public JsonElement original_value;
    }

    public static class Entries {
        public String key;
        public List<Change> items;
    }

    public List<Entries> changes;

}
