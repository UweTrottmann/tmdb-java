/*
 * Copyright 2014 Uwe Trottmann
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
 */

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.TvSeason;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("/tv/{id}/season/{season}")
    TvSeason season(
            @Path("id") int showId,
            @Path("season") int seasonNumber,
            @Query("language") String language
    );

}
