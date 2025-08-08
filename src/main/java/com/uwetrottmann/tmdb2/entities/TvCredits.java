// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TvCredits {

    public Integer id;

    public List<TvCastCredit> cast;
    public List<TvCrewCredit> crew;

}
