// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

public class ChangeResultsPage extends BaseResultsPage<ChangeResultsPage.Change> {

    public static class Change {

        public Integer id;
        public Boolean adult;

    }

}
