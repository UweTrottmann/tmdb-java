package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.interfaces.ITmdbIdentifiedEntity;

public class Network implements ITmdbIdentifiedEntity {

    public Integer id;
    public String name;

    @Override
    public Integer getId() {
        return id;
    }
}
