package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import retrofit2.Call;

public class DiscoverMovieBuilder {

    private final DiscoverService discoverService;

    @Nullable private String language;
    @Nullable private String region;
    @Nullable private SortBy sort_by;
    @Nullable private String certification_country;
    @Nullable private String certification;
    @Nullable private String certification_lte;
    @Nullable private Boolean include_adult;
    @Nullable private Boolean include_video;
    @Nullable private Integer page;
    @Nullable private Integer primary_release_year;
    @Nullable private TmdbDate primary_release_date_gte;
    @Nullable private TmdbDate primary_release_date_lte;
    @Nullable private TmdbDate release_date_gte;
    @Nullable private TmdbDate release_date_lte;
    @Nullable private Integer vote_count_gte;
    @Nullable private Integer vote_count_lte;
    @Nullable private Float vote_average_gte;
    @Nullable private Float vote_average_lte;
    @Nullable private DiscoverFilter with_cast;
    @Nullable private DiscoverFilter with_crew;
    @Nullable private DiscoverFilter with_companies;
    @Nullable private DiscoverFilter with_genres;
    @Nullable private DiscoverFilter with_keywords;
    @Nullable private DiscoverFilter with_people;
    @Nullable private Integer year;
    @Nullable private DiscoverFilter without_genres;
    @Nullable private Integer with_runtime_gte;
    @Nullable private Integer with_runtime_lte;
    @Nullable private DiscoverFilter with_release_type;
    @Nullable private String with_original_language;
    @Nullable private DiscoverFilter without_keywords;

    public DiscoverMovieBuilder(@Nonnull DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    public DiscoverMovieBuilder language(@Nullable String value) {
        this.language = value;
        return this;
    }

    public DiscoverMovieBuilder region(@Nullable String value) {
        this.region = value;
        return this;
    }

    public DiscoverMovieBuilder sort_by(@Nullable SortBy value) {
        this.sort_by = value;
        return this;
    }

    public DiscoverMovieBuilder certification_country(@Nullable String value) {
        this.certification_country = value;
        return this;
    }

    public DiscoverMovieBuilder certification(@Nullable String value) {
        this.certification = value;
        return this;
    }

    public DiscoverMovieBuilder certification_lte(@Nullable String value) {
        this.certification_lte = value;
        return this;
    }

    public DiscoverMovieBuilder includeAdult() {
        this.include_adult = true;
        return this;
    }

    public DiscoverMovieBuilder includeVideo() {
        this.include_video = true;
        return this;
    }

    public DiscoverMovieBuilder page(@Nullable Integer value) {
        this.page = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_year(@Nullable Integer value) {
        this.primary_release_year = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_date_gte(@Nullable TmdbDate value) {
        this.primary_release_date_gte = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_date_lte(@Nullable TmdbDate value) {
        this.primary_release_date_lte = value;
        return this;
    }

    public DiscoverMovieBuilder release_date_gte(@Nullable TmdbDate value) {
        this.release_date_gte = value;
        return this;
    }

    public DiscoverMovieBuilder release_date_lte(@Nullable TmdbDate value) {
        this.release_date_lte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_count_gte(@Nullable Integer value) {
        this.vote_count_gte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_count_lte(@Nullable Integer value) {
        this.vote_count_lte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_average_gte(@Nullable Float value) {
        this.vote_average_gte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_average_lte(@Nullable Float value) {
        this.vote_average_lte = value;
        return this;
    }

    public DiscoverMovieBuilder with_cast(@Nullable DiscoverFilter value) {
        this.with_cast = value;
        return this;
    }

    public DiscoverMovieBuilder with_crew(@Nullable DiscoverFilter value) {
        this.with_crew = value;
        return this;
    }

    public DiscoverMovieBuilder with_companies(@Nullable DiscoverFilter value) {
        this.with_companies = value;
        return this;
    }

    public DiscoverMovieBuilder with_genres(@Nullable DiscoverFilter value) {
        this.with_genres = value;
        return this;
    }

    public DiscoverMovieBuilder with_keywords(@Nullable DiscoverFilter value) {
        this.with_keywords = value;
        return this;
    }

    public DiscoverMovieBuilder with_people(@Nullable DiscoverFilter value) {
        this.with_people = value;
        return this;
    }

    public DiscoverMovieBuilder year(@Nullable Integer value) {
        this.year = value;
        return this;
    }

    public DiscoverMovieBuilder without_genres(@Nullable DiscoverFilter value) {
        this.without_genres = value;
        return this;
    }

    public DiscoverMovieBuilder with_runtime_gte(@Nullable Integer value) {
        this.with_runtime_gte = value;
        return this;
    }

    public DiscoverMovieBuilder with_runtime_lte(@Nullable Integer value) {
        this.with_runtime_lte = value;
        return this;
    }

    public DiscoverMovieBuilder with_release_type(@Nullable DiscoverFilter value) {
        this.with_release_type = value;
        return this;
    }

    public DiscoverMovieBuilder with_original_language(@Nullable String value) {
        this.with_original_language = value;
        return this;
    }

    public DiscoverMovieBuilder without_keywords(@Nullable DiscoverFilter keywords) {
        this.without_keywords = keywords;
        return this;
    }

    public Call<MovieResultsPage> build() {
        return discoverService.discoverMovie(
                language,
                region,
                sort_by,
                certification_country,
                certification,
                certification_lte,
                include_adult,
                include_video,
                page,
                primary_release_year,
                primary_release_date_gte,
                primary_release_date_lte,
                release_date_gte,
                release_date_lte,
                vote_count_gte,
                vote_count_lte,
                vote_average_gte,
                vote_average_lte,
                with_cast,
                with_crew,
                with_companies,
                with_genres,
                with_keywords,
                with_people,
                year,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                with_release_type,
                with_original_language,
                without_keywords
        );
    }

}
