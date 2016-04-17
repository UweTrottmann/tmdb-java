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

import com.uwetrottmann.tmdb2.entities.GenreResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenreService {

    /**
     * Get the list of movie genres.
     *
    * @param query CGI escaped string
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Call<GenreResults> movie(
            @Query("language") String language
    );

    /**
     * Get the list of TV genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/tv/list")
    Call<GenreResults> tv(
            @Query("language") String language
    );
}
