package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.TmdbEntity;

import java.util.Date;

public class CountryRelease implements TmdbEntity {

    public Date release_date;
    public String iso_3166_1;
    public String certification;

}
