package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.TmdbEntity;

import java.util.List;

public class ReleasesResult implements TmdbEntity {

    public int id;
    public List<CountryRelease> countries;

}
