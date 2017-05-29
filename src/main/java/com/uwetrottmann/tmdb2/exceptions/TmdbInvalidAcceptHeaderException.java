package com.uwetrottmann.tmdb2.exceptions;

public class TmdbInvalidAcceptHeaderException extends TmdbException {
    public TmdbInvalidAcceptHeaderException(int code, String message) {
        super(code, message);
    }
}
