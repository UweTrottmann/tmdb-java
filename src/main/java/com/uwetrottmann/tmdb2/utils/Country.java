package com.uwetrottmann.tmdb2.utils;

public class Country {
    private final TmdbLocale locale;

    public Country(TmdbLocale locale) {
        if (locale == null) {
            this.locale = new TmdbLocale("");
        }
        else {
            this.locale = locale;
        }
    }

    public Country(String country) {
        this.locale = new TmdbLocale("",country);
    }

    public String getCountryCode() {
        return locale.getCountryCode();
    }

    public String getCountry() {
        return locale.getCountry();
    }

    public TmdbLocale getAsTmdbLocale() {
        return locale;
    }
}
