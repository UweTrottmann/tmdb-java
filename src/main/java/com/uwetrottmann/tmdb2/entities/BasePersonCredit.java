package com.uwetrottmann.tmdb2.entities;

import java.util.Date;

public abstract class BasePersonCredit {

    public String credit_id;
    public Integer id;
    public String media_type;

    // both
    public String poster_path;

    // movies
    public Boolean adult;
    public Date release_date;
    public String title;
    public String original_title;

    // tv
    public Date first_air_date;
    public String name;
    public String original_name;

}
