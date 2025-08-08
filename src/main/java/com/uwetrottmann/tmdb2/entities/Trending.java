// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.MediaType;

public class Trending {

    public BaseTvShow tvShow;
    public BaseMovie movie;
    public BasePerson person;
    
    public MediaType media_type;
    
}
