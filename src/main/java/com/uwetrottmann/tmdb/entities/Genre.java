package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.TraktEntity;

public class Genre implements TraktEntity {
    private static final long serialVersionUID = -7818541411651542895L;

    public String name;
    public String slug;

    /** @deprecated Use {@link #name} */
    @Deprecated
    public String getName() {
        return this.name;
    }
    /** @deprecated Use {@link #slug} */
    @Deprecated
    public String getSlug() {
        return this.slug;
    }
}
