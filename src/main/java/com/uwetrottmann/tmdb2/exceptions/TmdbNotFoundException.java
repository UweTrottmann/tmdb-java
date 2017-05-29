package com.uwetrottmann.tmdb2.exceptions;

public class TmdbNotFoundException extends TmdbException {
    public TmdbNotFoundException(int code, String message) {
        super(code, message);
    }
}
