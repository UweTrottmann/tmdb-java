/*
 * Copyright 2015 Miguel Teixeira
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

package com.uwetrottmann.tmdb.entities;

import java.util.List;

public class Images {

    public static class Image {

        public String file_path;
        public Integer width;
        public Integer height;
        public String iso_639_1;
        public Float aspect_ratio;
        public Float vote_average;
        public Integer vote_count;
        
    }
    
    public Integer id;
    public List<Image> backdrops;
    public List<Image> posters;

}
