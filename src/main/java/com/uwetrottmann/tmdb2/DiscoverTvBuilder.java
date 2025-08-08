// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import javax.annotation.Nullable;
import retrofit2.Call;

public class DiscoverTvBuilder {

    protected final DiscoverService discoverService;

    @Nullable private String language;
    @Nullable private SortBy sort_by;
    @Nullable private TmdbDate air_date_gte;
    @Nullable private TmdbDate air_date_lte;
    @Nullable private TmdbDate first_air_date_gte;
    @Nullable private TmdbDate first_air_date_lte;
    @Nullable private Integer first_air_date_year;
    @Nullable private Integer page;
    @Nullable private String timezone;
    @Nullable private Float vote_average_gte;
    @Nullable private Float vote_average_lte;
    @Nullable private Integer vote_count_gte;
    @Nullable private Integer vote_count_lte;
    @Nullable private DiscoverFilter with_genres;
    @Nullable private DiscoverFilter with_networks;
    @Nullable private DiscoverFilter without_genres;
    @Nullable private Integer with_runtime_gte;
    @Nullable private Integer with_runtime_lte;
    @Nullable private Boolean include_null_first_air_dates;
    @Nullable private String with_original_language;
    @Nullable private DiscoverFilter without_keywords;
    @Nullable private Boolean screened_theatrically;
    @Nullable private DiscoverFilter with_companies;
    @Nullable private DiscoverFilter with_keywords;
    @Nullable private DiscoverFilter with_watch_providers;
    @Nullable private String watch_region;
    @Nullable private String with_watch_monetization_types;

    public DiscoverTvBuilder(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    public DiscoverTvBuilder language(@Nullable String value) {
        this.language = value;
        return this;
    }

    public DiscoverTvBuilder sort_by(@Nullable SortBy value) {
        this.sort_by = value;
        return this;
    }

    public DiscoverTvBuilder air_date_gte(@Nullable TmdbDate value) {
        this.air_date_gte = value;
        return this;
    }

    public DiscoverTvBuilder air_date_lte(@Nullable TmdbDate value) {
        this.air_date_lte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_gte(@Nullable TmdbDate value) {
        this.first_air_date_gte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_lte(@Nullable TmdbDate value) {
        this.first_air_date_lte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_year(@Nullable Integer value) {
        this.first_air_date_year = value;
        return this;
    }

    public DiscoverTvBuilder page(@Nullable Integer value) {
        this.page = value;
        return this;
    }

    public DiscoverTvBuilder timezone(@Nullable String value) {
        this.timezone = value;
        return this;
    }

    public DiscoverTvBuilder vote_average_gte(@Nullable Float value) {
        this.vote_average_gte = value;
        return this;
    }

    public DiscoverTvBuilder vote_average_lte(@Nullable Float value) {
        this.vote_average_lte = value;
        return this;
    }

    public DiscoverTvBuilder vote_count_gte(@Nullable Integer value) {
        this.vote_count_gte = value;
        return this;
    }

    public DiscoverTvBuilder vote_count_lte(@Nullable Integer value) {
        this.vote_count_lte = value;
        return this;
    }

    public DiscoverTvBuilder with_genres(@Nullable DiscoverFilter value) {
        this.with_genres = value;
        return this;
    }

    public DiscoverTvBuilder with_networks(@Nullable DiscoverFilter value) {
        this.with_networks = value;
        return this;
    }

    public DiscoverTvBuilder without_genres(@Nullable DiscoverFilter value) {
        this.without_genres = value;
        return this;
    }

    public DiscoverTvBuilder with_runtime_gte(@Nullable Integer value) {
        this.with_runtime_gte = value;
        return this;
    }

    public DiscoverTvBuilder with_runtime_lte(@Nullable Integer value) {
        this.with_runtime_lte = value;
        return this;
    }

    public DiscoverTvBuilder include_null_first_air_dates() {
        this.include_null_first_air_dates = true;
        return this;
    }

    public DiscoverTvBuilder with_original_language(@Nullable String value) {
        this.with_original_language = value;
        return this;
    }

    public DiscoverTvBuilder without_keywords(@Nullable DiscoverFilter keywords) {
        this.without_keywords = keywords;
        return this;
    }

    public DiscoverTvBuilder screened_theatrically(@Nullable Boolean screened_theatrically) {
        this.screened_theatrically = screened_theatrically;
        return this;
    }

    public DiscoverTvBuilder with_companies(@Nullable DiscoverFilter with_companies) {
        this.with_companies = with_companies;
        return this;
    }

    public DiscoverTvBuilder with_keywords(@Nullable DiscoverFilter with_keywords) {
        this.with_keywords = with_keywords;
        return this;
    }

    public DiscoverTvBuilder with_watch_providers(@Nullable DiscoverFilter with_watch_providers) {
        this.with_watch_providers = with_watch_providers;
        return this;
    }

    public DiscoverTvBuilder watch_region(@Nullable String watch_region) {
        this.watch_region = watch_region;
        return this;
    }

    public DiscoverTvBuilder with_watch_monetization_types(@Nullable String with_watch_monetization_types) {
        this.with_watch_monetization_types = with_watch_monetization_types;
        return this;
    }

    public Call<TvShowResultsPage> build() {
        return discoverService.discoverTv(
                language,
                sort_by,
                air_date_gte,
                air_date_lte,
                first_air_date_gte,
                first_air_date_lte,
                first_air_date_year,
                page,
                timezone,
                vote_average_gte,
                vote_average_lte,
                vote_count_gte,
                vote_count_lte,
                with_genres,
                with_networks,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                include_null_first_air_dates,
                with_original_language,
                without_keywords,
                screened_theatrically,
                with_companies,
                with_keywords,
                with_watch_providers,
                watch_region,
                with_watch_monetization_types
        );
    }

}
