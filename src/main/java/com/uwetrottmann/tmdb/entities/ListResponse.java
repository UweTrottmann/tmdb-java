
package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.enumerations.ListPrivacy;

public class ListResponse extends Response {
    private static final long serialVersionUID = 5368378936105337182L;

    public String name;
    public String slug;
    public ListPrivacy privacy;

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

    /** @deprecated Use {@link #privacy} */
    @Deprecated
    public ListPrivacy getPrivacy() {
        return this.privacy;
    }
}
