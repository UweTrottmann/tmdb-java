// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.exceptions;

public class TmdbAuthenticationFailedException extends TmdbException {
    public TmdbAuthenticationFailedException(int code, String message) {
        super(code, message);
    }
}
