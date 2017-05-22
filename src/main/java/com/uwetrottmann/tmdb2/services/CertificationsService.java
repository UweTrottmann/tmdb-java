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

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Certifications;
import retrofit2.Call;
import retrofit2.http.GET;


public interface CertificationsService {
    /**
     * Get the Certifications for Movies.
     */
    @GET("certification/movie/list")
    Call<Certifications> movie();

    /**
     * Get the Certifications for TV Shows.
     */
    @GET("certification/tv/list")
    Call<Certifications> tv();
}
