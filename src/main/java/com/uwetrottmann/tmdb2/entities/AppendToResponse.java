// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann
// Copyright 2014 Chris Banes

package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;

public class AppendToResponse {

    private final AppendToResponseItem[] items;

    public AppendToResponse(AppendToResponseItem... items) {
        this.items = items;
    }

    @Override
    public String toString() {
        if (items != null && items.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.length; i++) {
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
