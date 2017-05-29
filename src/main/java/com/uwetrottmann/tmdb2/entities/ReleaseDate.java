package com.uwetrottmann.tmdb2.entities;

import java.util.Date;

public class ReleaseDate {

    public static Integer TYPE_PREMIERE           = 1;
    public static Integer TYPE_THEATRICAL_LIMITED = 2;
    public static Integer TYPE_THEATRICAL         = 3;
    public static Integer TYPE_DIGITAL            = 4;
    public static Integer TYPE_PHYSICAL           = 5;
    public static Integer TYPE_TV                 = 6;

    public String certification;
    public String iso_639_1;
    public String note;
    public Date release_date;
    public Integer type;

}
