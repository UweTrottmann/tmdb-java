package com.uwetrottmann.tmdb2.utils;

public class Language {

    public String getLanguageCode() {
        return locale.getLanguageCode();
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public String getLocalizedLanguage() {
        return locale.getLocalizedLanguage();
    }

    public Language(TmdbLocale locale) {
        if (locale == null) {
            this.locale = new TmdbLocale("");
        }
        else {
            this.locale = locale;
        }
    }

    public Language(String language) {
        this.locale = new TmdbLocale(language);
    }

    final TmdbLocale locale;

    public TmdbLocale getAsTmdbLocale() {
        return locale;
    }
}
