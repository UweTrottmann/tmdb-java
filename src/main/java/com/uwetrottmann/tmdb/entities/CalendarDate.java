
package com.uwetrottmann.tmdb.entities;

import com.uwetrottmann.tmdb.Entity;

import java.util.Date;
import java.util.List;

public class CalendarDate implements Entity {
    private static final long serialVersionUID = 5985118362541597172L;

    public static class CalendarTvShowEpisode implements Entity {
        private static final long serialVersionUID = -7066863350641449761L;

        public TvShow show;
        public TvShowEpisode episode;

        /** @deprecated Use {@link #show} */
        @Deprecated
        public TvShow getShow() {
            return this.show;
        }

        /** @deprecated Use {@link #episode} */
        @Deprecated
        public TvShowEpisode getEpisode() {
            return this.episode;
        }
    }

    public Date date;
    public List<CalendarTvShowEpisode> episodes;

    /** @deprecated Use {@link #date} */
    @Deprecated
    public Date getDate() {
        return this.date;
    }

    /** @deprecated Use {@link #episodes} */
    @Deprecated
    public List<CalendarTvShowEpisode> getEpisodes() {
        return this.episodes;
    }
}
