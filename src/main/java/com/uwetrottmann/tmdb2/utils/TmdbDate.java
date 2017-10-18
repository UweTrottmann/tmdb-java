package com.uwetrottmann.tmdb2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TmdbDate {
    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";

    public static final ThreadLocal<DateFormat> TMDB_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            return new SimpleDateFormat(TMDB_DATE_PATTERN);
        }
    };

    private final Date date;

    public TmdbDate() {
        this(new Date());
    }

    public TmdbDate(Date date) {
        if (TMDB_DATE_FORMAT.get() == null) {
            TMDB_DATE_FORMAT.set(new SimpleDateFormat(TMDB_DATE_PATTERN));
        }
        this.date = date;
    }

    public TmdbDate(String date, String format) {
        this(date,new SimpleDateFormat(format));
    }

    public TmdbDate(String date, DateFormat format) {
        Date parsedDate;
        try {
            parsedDate = format.parse(date);
        } catch (NullPointerException | ParseException e) {
            parsedDate = null;
        }
        this.date = parsedDate;
    }

    public TmdbDate(String date) {
        DateFormat format;
        if ((format = TMDB_DATE_FORMAT.get()) == null) {
            TMDB_DATE_FORMAT.set(format = new SimpleDateFormat(TMDB_DATE_PATTERN));
        }
        Date parsedDate;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            parsedDate = null;
        }
        this.date = parsedDate;
    }

    @Override
    public String toString() {
        return getAsString();
    }

    public Date getAsDate() {
        return date;
    }

    public Long getAsUnixTimestamp() {
        if (date == null)
            return null;
        return date.getTime();
    }

    public String getAsString() {
        return getAsString(TMDB_DATE_FORMAT.get());
    }

    public String getAsString(DateFormat format) {
        if (date == null)
            return null;

        return format.format(date);
    }

    public String getAsString(String format) {
        return getAsString(new SimpleDateFormat(format));
    }

}
