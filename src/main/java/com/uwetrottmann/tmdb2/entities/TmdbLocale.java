package com.uwetrottmann.tmdb2.entities;

import java.util.Locale;

public class TmdbLocale {

    private Locale locale;

    public TmdbLocale(String language, String country, String variant) {
        locale = new Locale(language, country, variant);
    }

    public TmdbLocale(String language, String country) {
        locale = new Locale(language, country);
    }

    public TmdbLocale(String language) {
        if (language.split("_").length > 1) {
            String[] lang = language.split("_");
            locale = new Locale(lang[0],lang[1]);
        }
        else if (language.split("-").length > 1) {
            String[] lang = language.split("-");
            locale = new Locale(lang[0],lang[1]);
        }
        else {
            locale = new Locale(language);
        }
    }

    public TmdbLocale(Locale language) {
        locale = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage().toLowerCase().split("_")[0].split("-")[0]+(locale.getCountry()!=null&&!locale.getCountry().equals("")?"-"+locale.getCountry().toUpperCase():"");
    }

    @Override
    public String toString() {
        return getLanguage();
    }

}
