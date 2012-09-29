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

package com.uwetrottmann.tmdb;

import com.google.gson.JsonObject;
import com.jakewharton.apibuilder.ApiException;
import com.uwetrottmann.tmdb.entities.Response;

public final class TmdbException extends RuntimeException {
    private static final long serialVersionUID = -5280024879091580667L;
    
    private final String url;
    private final JsonObject postBody;
    private final Response response;

    public TmdbException(String url, JsonObject postBody, ApiException cause) {
        super(cause);
        this.url = url;
        this.postBody = postBody;
        this.response = null;
    }

    public TmdbException(String url, JsonObject postBody, ApiException cause, Response response) {
        super(response.status_message, cause);
        this.url = url;
        this.postBody = postBody;
        this.response = response;
    }

    public String getUrl() {
        return this.url;
    }

    public JsonObject getPostBody() {
        return this.postBody;
    }

    public Response getResponse() {
        return this.response;
    }
}
