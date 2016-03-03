package com.uwetrottmann.tmdb.entities;

import java.util.Date;

public class ReleaseDate {
    public static int TYPE_PREMIERE           = 1;
    public static int TYPE_THEATRICAL_LIMITED = 2;
    public static int TYPE_THEATRICAL         = 3;
    public static int TYPE_DIGITAL            = 4;
    public static int TYPE_PHYSICAL           = 5;
    public static int TYPE_TV                 = 6;

    public String certification;
    public String iso_639_1;
    public String note;
    public Date release_date;
    public int type;
}
