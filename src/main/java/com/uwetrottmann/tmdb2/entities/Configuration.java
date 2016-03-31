/*
 * Copyright 2012 Uwe Trottmann
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

import java.util.List;

public class Configuration {

    public static class ImagesConfiguration {

        public String base_url;
        public String secure_base_url;
        public List<String> poster_sizes;
        public List<String> backdrop_sizes;
        public List<String> profile_sizes;
        public List<String> logo_sizes;
    }

    public ImagesConfiguration images;
    public List<String> change_keys;
}
