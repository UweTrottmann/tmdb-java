/*
 * Copyright 2014 Jose Luis Franconetti
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

package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TvServiceAsync {

    /**
     * Get the cast and crew information about a TV series. Just like the website,
     * we pull this information from the last season of the series.
     *
     * @param tmdbId
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param callback {@link retrofit.Callback}
     */
    @GET("/tv/{id}/credits")
    void credits(
            @Path("id") int tmdbId,
            @Query("language") String language,
            Callback<Credits> callback
    );

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param callback {@link retrofit.Callback}
     */
    @GET("/tv/{id}/external_ids")
    void externalIds(
            @Path("id") int tmdbId,
            @Query("language") String language,
            Callback<ExternalIds> callback
    );


}
