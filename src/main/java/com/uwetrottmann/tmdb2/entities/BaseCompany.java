package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.interfaces.ITmdbIdentifiedEntity;

public class BaseCompany implements ITmdbIdentifiedEntity {

    public Integer id;
    public String name;
    public String logo_path;

    @Override
    public Integer getId() {
        return id;
    }
}
