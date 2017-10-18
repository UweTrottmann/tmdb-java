package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.interfaces.ITmdbIdentifiedEntity;

import java.util.List;

public class BasePerson implements ITmdbIdentifiedEntity {

    public String profile_path;
    public Boolean adult;
    public Integer id;
    public String name;
    public Integer popularity;
    public List<Media> known_for;

    @Override
    public Integer getId() {
        return id;
    }
}
