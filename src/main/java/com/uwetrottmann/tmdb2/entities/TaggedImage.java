package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.utils.TmdbDate;

public class TaggedImage extends Image {

    public String id;
    public String media_type;
    public TmdbDate release_date;
    public Media media;

}