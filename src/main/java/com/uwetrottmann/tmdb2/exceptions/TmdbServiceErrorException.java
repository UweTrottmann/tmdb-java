package com.uwetrottmann.tmdb2.exceptions;

public class TmdbServiceErrorException extends TmdbException {
    public TmdbServiceErrorException(int code, String message) {
        super(code, message);
    }
}
