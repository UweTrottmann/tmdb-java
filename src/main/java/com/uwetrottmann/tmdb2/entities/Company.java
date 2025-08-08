// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

public class Company extends BaseCompany {

    public String description;
    public String headquarters;
    public String homepage;
    public Company parent_company;
    public String origin_country;

    public MovieResultsPage movies;

}
