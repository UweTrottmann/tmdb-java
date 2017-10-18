package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.interfaces.ITmdbIdentifiedEntity;

public abstract class BaseMember implements ITmdbIdentifiedEntity {

    public Integer id;
    public String credit_id;
    public String name;
    public String profile_path;

    @Override
    public Integer getId() {
        return id;
    }
}
