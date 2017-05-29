package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class CreditMedia {

    public Integer id;
    public String name;
    public String original_name;
    public String character;
    //Provides Not data at the moment. API Related bug.
    //public List<JsonPrimitive> episodes;
    public List<TvSeason> seasons;

}
