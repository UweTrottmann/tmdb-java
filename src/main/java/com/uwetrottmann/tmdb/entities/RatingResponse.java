
package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.Entity;
import com.uwetrottmann.tmdb.enumerations.Rating;
import com.uwetrottmann.tmdb.enumerations.RatingType;

public class RatingResponse extends Response implements Entity {
    private static final long serialVersionUID = 8424378149600617021L;

    public RatingType type;
    public Rating rating;
    public Ratings ratings;
    public Boolean facebook;
    public Boolean twitter;
    public Boolean tumblr;

    /** @deprecated Use {@link #type} */
    @Deprecated
    public RatingType getType() {
        return this.type;
    }

    /** @deprecated Use {@link #rating} */
    @Deprecated
    public Rating getRating() {
        return this.rating;
    }

    /** @deprecated Use {@link #ratings} */
    @Deprecated
    public Ratings getRatings() {
        return this.ratings;
    }

    /** @deprecated Use {@link #facebook} */
    @Deprecated
    public Boolean getFacebook() {
        return this.facebook;
    }

    /** @deprecated Use {@link #twitter} */
    @Deprecated
    public Boolean getTwitter() {
        return this.twitter;
    }

    /** @deprecated Use {@link #tumblr} */
    @Deprecated
    public Boolean getTumblr() {
        return this.tumblr;
    }
}
