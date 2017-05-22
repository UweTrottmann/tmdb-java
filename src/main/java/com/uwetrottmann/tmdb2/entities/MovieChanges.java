/*
 * Copyright 2017 Nikolas Mavropoylos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonObject;

import java.util.List;

public class MovieChanges {

    public static class Item {

        public String id;
        public String action;
        public String time;
        public String iso_639_1;
        public JsonObject value;
        public JsonObject original_value;
    }

    public static class Change {
        public String key;
        public List<Item> items;
    }

    public List<Change> changes;
}
