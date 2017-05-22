/*
 * Modifications Copyright 2017 Nikolas Mavropoylos
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

import com.uwetrottmann.tmdb2.entities.FindResults;
import com.uwetrottmann.tmdb2.enumerations.ExternalSource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FindService {

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.
     *
     * @param externalId An External Media Object Id.
     * @param source <em>Required.</em> An External source to search. Allowed Values: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * @param language <em>Optional.</em> ISO 639-1 code.

     */
    @GET("find/{external_id}")
    Call<FindResults> find(
            @Path("external_id") String externalId,
            @Query("external_source") ExternalSource source,
            @Query("language") String language
    );

}
