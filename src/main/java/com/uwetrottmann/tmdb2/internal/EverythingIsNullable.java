// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nullable;
import javax.annotation.meta.TypeQualifierDefault;

/**
 * Extends {@code ParametersAreNullableByDefault} to also apply to method results and fields.
 *
 * @see javax.annotation.ParametersAreNullableByDefault
 */
@Documented
@Nullable
@TypeQualifierDefault({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface EverythingIsNullable {
}
