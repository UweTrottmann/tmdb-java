package com.uwetrottmann.tmdb2.utils;

import java.util.Locale;

public class TmdbLocale {

    private Locale locale;

    public TmdbLocale(String language, String country) {
        if ((language.length() == 0 || language.length() == 2) && (country.length() == 2)) {
            locale = new Locale(language, country);
        }
        else {
            locale = new Locale("");
        }
    }

    public TmdbLocale(String language) {
        String[] lang;
        if ((lang = language.split("_")).length > 1 && lang[0].length() == 2 && lang[1].length() == 2) {
            locale = new Locale(lang[0], lang[1]);
        } else if ((lang = language.split("-")).length > 1 && lang[0].length() == 2 && lang[1].length() == 2) {
            locale = new Locale(lang[0], lang[1]);
        } else if (language.length() <= 2) {
            locale = new Locale(language);
        }
        else {
            locale = new Locale("");
        }
    }

    public TmdbLocale(Locale language) {
        locale = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguageCode() {
        return locale.getLanguage();
    }

    public String getCountryCode() {
        return locale.getCountry();
    }

    public String getLanguage() {
        return locale.getDisplayLanguage();
    }

    public String getLocalizedLanguage() {
        return locale.getDisplayLanguage(locale);
    }

    public String getCountry() {
        return locale.getDisplayCountry();
    }

    public String getLocalizedCountry() {
        return locale.getDisplayCountry(locale);
    }

    public Boolean hasLanguage() {
        return getLanguageCode() != null && !getLanguageCode().equals("");
    }

    public Boolean hasCountry() {
        return getCountryCode() != null && !getCountryCode().equals("");
    }

    @Override
    public String toString() {
        return !getLanguageCode().equals("")?(getLanguageCode()+(!getCountryCode().equals("")?"-"+getCountryCode():"")):!getCountryCode().equals("")?getCountryCode():"";
    }

}
