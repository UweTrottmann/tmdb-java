// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.exceptions;

import java.io.IOException;

public class TmdbException extends IOException {
    private int code;

    public int getCode() {
        return code;
    }

    public TmdbException(String message) {
        super(message);
    }

    public TmdbException(int code, String message) {
        super(message);
        this.code = code;
    }
}
