/*
 * Copyright 2013 Uwe Trottmann
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

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Configuration;

import retrofit.http.GET;

public interface ConfigurationService {

    /**
     * Get the system wide configuration information for images. Some elements of the API require
     * some knowledge of the configuration data which can be found here. The purpose of this is to
     * try and keep the actual API responses as light as possible. It is recommended you store this
     * data within your application and check for updates every so often.<br> <br> To build an image
     * URL, you will need 3 pieces of data. The base_url, size and file_path. Simply combine them
     * all and you will have a fully qualified URL. Here is an example URL:<br> <br> <a href=
     * "http://cf2.imgobject.com/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg"
     * >http://cf2.imgobject.com/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg</a>
     */
    @GET("/configuration")
    Configuration configuration();

}
