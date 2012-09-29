
package com.uwetrottmann.tmdb.services;

import com.google.gson.reflect.TypeToken;
import com.uwetrottmann.tmdb.TmdbApiBuilder;
import com.uwetrottmann.tmdb.TraktApiService;
import com.uwetrottmann.tmdb.entities.CalendarDate;

import java.util.Date;
import java.util.List;

public class CalendarService extends TraktApiService {
    /**
     * Returns all shows premiering during the time period specified.
     * 
     * @return Builder instance.
     */
    public PremieresBuilder premieres() {
        return new PremieresBuilder(this);
    }

    /**
     * Returns all shows airing during the time period specified.
     * 
     * @return Builder instance.
     */
    public ShowsBuilder shows() {
        return new ShowsBuilder(this);
    }

    public static final class PremieresBuilder extends TmdbApiBuilder<List<CalendarDate>> {
        private static final int DEFAULT_DAYS = 7;
        private static final String URI = "/calendar/premieres.json/" + FIELD_API_KEY + "/"
                + FIELD_DATE + "/" + FIELD_DAYS;

        private PremieresBuilder(CalendarService service) {
            super(service, new TypeToken<List<CalendarDate>>() {
            }, URI);
        }

        /**
         * Start date for the calendar.
         * 
         * @param date Value.
         * @return Builder instance.
         */
        public PremieresBuilder date(Date date) {
            this.field(FIELD_DATE, date);

            if (!this.hasField(FIELD_DAYS)) {
                // Set default.
                this.field(FIELD_DAYS, DEFAULT_DAYS);
            }

            return this;
        }

        /**
         * Number of days to display starting from the date.
         * 
         * @param days Value.
         * @return Builder instance.
         */
        public PremieresBuilder days(int days) {
            this.field(FIELD_DAYS, days);

            if (!this.hasField(FIELD_DATE)) {
                // Set default (today).
                this.field(FIELD_DATE, new Date());
            }

            return this;
        }
    }

    public static final class ShowsBuilder extends TmdbApiBuilder<List<CalendarDate>> {
        private static final int DEFAULT_DAYS = 7;
        private static final String URI = "/calendar/shows.json/" + FIELD_API_KEY + "/"
                + FIELD_DATE + "/" + FIELD_DAYS;

        private ShowsBuilder(CalendarService service) {
            super(service, new TypeToken<List<CalendarDate>>() {
            }, URI);
        }

        /**
         * Start date for the calendar.
         * 
         * @param date Value.
         * @return Builder instance.
         */
        public ShowsBuilder date(Date date) {
            this.field(FIELD_DATE, date);

            if (!this.hasField(FIELD_DAYS)) {
                // Set default.
                this.field(FIELD_DAYS, DEFAULT_DAYS);
            }

            return this;
        }

        /**
         * Number of days to display starting from the date.
         * 
         * @param days Value.
         * @return Builder instance.
         */
        public ShowsBuilder days(int days) {
            this.field(FIELD_DAYS, days);

            if (!this.hasField(FIELD_DATE)) {
                // Set default (today).
                this.field(FIELD_DATE, new Date());
            }

            return this;
        }
    }
}
