// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.exceptions;

public class TmdbDuplicateEntryException extends TmdbException {
    public TmdbDuplicateEntryException(int code, String message) {
        super(code, message);
    }
}
