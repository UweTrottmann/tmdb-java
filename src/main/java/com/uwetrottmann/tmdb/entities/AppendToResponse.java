/*
 * Copyright 2014 Chris Banes
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

import com.uwetrottmann.tmdb.enumerations.AppendToResponseItem;

public class AppendToResponse {

    private final AppendToResponseItem[] items;

    public AppendToResponse(AppendToResponseItem... items) {
        this.items = items;
    }

    @Override
    public String toString() {
        if (items != null && items.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < items.length ; i++) {
                sb.append(items[i]);

                if (i < items.length - 1) {
                    sb.append(',');
                }
            }

            return sb.toString();
        }

        return null;
    }
}
