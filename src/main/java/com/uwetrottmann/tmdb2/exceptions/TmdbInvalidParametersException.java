package com.uwetrottmann.tmdb2.exceptions;

public class TmdbInvalidParametersException extends TmdbException {
    public TmdbInvalidParametersException(int code, String message) {
        super(code, message);
    }
}
