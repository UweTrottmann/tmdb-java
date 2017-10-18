package com.uwetrottmann.tmdb2.utils;

import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import retrofit2.Call;

public class DiscoverTvBuilder {

    protected final DiscoverService discoverService;

    private TmdbLocale language;
    private SortBy sort_by;
    private TmdbDate air_date_gte;
    private TmdbDate air_date_lte;
    private TmdbDate first_air_date_gte;
    private TmdbDate first_air_date_lte;
    private Integer first_air_date_year;
    private Integer page;
    private String timezone;
    private Float vote_average_gte;
    private Integer vote_count_gte;
    private DiscoverFilter with_genres;
    private DiscoverFilter with_networks;
    private DiscoverFilter without_genres;
    private Integer with_runtime_gte;
    private Integer with_runtime_lte;
    private Boolean include_null_first_air_dates;
    private String with_original_language;
    private DiscoverFilter without_keywords;

    public DiscoverTvBuilder(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    public DiscoverTvBuilder language(TmdbLocale value) {
        this.language = value;
        return this;
    }

    public DiscoverTvBuilder sort_by(SortBy value) {
        this.sort_by = value;
        return this;
    }

    public DiscoverTvBuilder air_date_gte(TmdbDate value) {
        this.air_date_gte = value;
        return this;
    }

    public DiscoverTvBuilder air_date_lte(TmdbDate value) {
        this.air_date_lte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_gte(TmdbDate value) {
        this.first_air_date_gte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_lte(TmdbDate value) {
        this.first_air_date_lte = value;
        return this;
    }

    public DiscoverTvBuilder first_air_date_year(Integer value) {
        this.first_air_date_year = value;
        return this;
    }

    public DiscoverTvBuilder page(Integer value) {
        this.page = value;
        return this;
    }

    public DiscoverTvBuilder timezone(String value) {
        this.timezone = value;
        return this;
    }

    public DiscoverTvBuilder vote_average_gte(Float value) {
        this.vote_average_gte = value;
        return this;
    }

    public DiscoverTvBuilder vote_count_gte(Integer value) {
        this.vote_count_gte = value;
        return this;
    }

    public DiscoverTvBuilder with_genres(DiscoverFilter value) {
        this.with_genres = value;
        return this;
    }

    public DiscoverTvBuilder with_networks(DiscoverFilter value) {
        this.with_networks = value;
        return this;
    }

    public DiscoverTvBuilder without_genres(DiscoverFilter value) {
        this.without_genres = value;
        return this;
    }

    public DiscoverTvBuilder with_runtime_gte(Integer value) {
        this.with_runtime_gte = value;
        return this;
    }

    public DiscoverTvBuilder with_runtime_lte(Integer value) {
        this.with_runtime_lte = value;
        return this;
    }

    public DiscoverTvBuilder include_null_first_air_dates() {
        this.include_null_first_air_dates = true;
        return this;
    }

    public DiscoverTvBuilder with_original_language(String value) {
        this.with_original_language = value;
        return this;
    }

    public DiscoverTvBuilder without_keywords(DiscoverFilter keywords) {
        this.without_keywords = keywords;
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
                vote_count_gte,
                with_genres,
                with_networks,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                include_null_first_air_dates,
                with_original_language,
                without_keywords
        );
    }

}
