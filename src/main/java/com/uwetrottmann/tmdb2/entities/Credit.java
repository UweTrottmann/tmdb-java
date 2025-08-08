// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.CreditType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;

public class Credit {

    public CreditType credit_type;
    public String department;
    public String job;
    public CreditMedia media;
    public MediaType media_type;
    public String id;
    public BasePerson person;

}
