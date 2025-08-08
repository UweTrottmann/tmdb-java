// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class PersonCredits {

    public Integer id;
    public List<PersonCastCredit> cast;
    public List<PersonCrewCredit> crew;

}
