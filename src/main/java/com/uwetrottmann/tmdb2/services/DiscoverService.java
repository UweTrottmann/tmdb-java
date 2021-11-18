package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverService {

    /**
     * <p>Discover movies by different types of data like average rating, number of votes, genres and certifications.
     *
     * @see <a href="https://developers.themoviedb.org/3/discover/movie-discover">Movie Discover</a>
     */
    @GET("discover/movie")
    Call<MovieResultsPage> discoverMovie(
            @Query("language") String language,
            @Query("region") String region,
            @Query("sort_by") SortBy sort_by,
            @Query("certification_country") String certification_country,
            @Query("certification") String certification,
            @Query("certification_lte") String certification_lte,
            @Query("include_adult") Boolean include_adult,
            @Query("include_video") Boolean include_video,
            @Query("page") Integer page,
            @Query("primary_release_year") Integer primary_release_year,
            @Query("primary_release_date.gte") TmdbDate primary_release_date_gte,
            @Query("primary_release_date.lte") TmdbDate primary_release_date_lte,
            @Query("release_date.gte") TmdbDate release_date_gte,
            @Query("release_date.lte") TmdbDate release_date_lte,
            @Query("vote_count.gte") Integer vote_count_gte,
            @Query("vote_count.lte") Integer vote_count_lte,
            @Query("vote_average.gte") Float vote_average_gte,
            @Query("vote_average.lte") Float vote_average_lte,
            @Query("with_cast") DiscoverFilter with_cast,
            @Query("with_crew") DiscoverFilter with_crew,
            @Query("with_companies") DiscoverFilter with_companies,
            @Query("with_genres") DiscoverFilter with_genres,
            @Query("with_keywords") DiscoverFilter with_keywords,
            @Query("with_people") DiscoverFilter with_people,
            @Query("year") Integer year,
            @Query("without_genres") DiscoverFilter without_genres,
            @Query("with_runtime.gte") Integer with_runtime_gte,
            @Query("with_runtime.lte") Integer with_runtime_lte,
            @Query("with_release_type") DiscoverFilter with_release_type,
            @Query("with_original_language") String with_original_language,
            @Query("without_keywords") DiscoverFilter without_keywords,
            @Query("with_watch_providers") DiscoverFilter with_watch_providers,
            @Query("watch_region") String watch_region,
            @Query("with_watch_monetization_types") String with_watch_monetization_types
    );

    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres, the network they aired
     * on and air dates.
     *
     * @see <a href="https://developers.themoviedb.org/3/discover/tv-discover">TV Discover</a>
     */
    @GET("discover/tv")
    Call<TvShowResultsPage> discoverTv(
            @Query("language") String language,
            @Query("sort_by") SortBy sort_by,
            @Query("air_date.gte") TmdbDate air_date_gte,
            @Query("air_date.lte") TmdbDate air_date_lte,
            @Query("first_air_date.gte") TmdbDate first_air_date_gte,
            @Query("first_air_date.lte") TmdbDate first_air_date_lte,
            @Query("first_air_date_year") Integer first_air_date_year,
            @Query("page") Integer page,
            @Query("timezone") String timezone,
            @Query("vote_average.gte") Float vote_average_gte,
            @Query("vote_count.gte") Integer vote_count_gte,
            @Query("with_genres") DiscoverFilter with_genres,
            @Query("with_networks") DiscoverFilter with_networks,
            @Query("without_genres") DiscoverFilter without_genres,
            @Query("with_runtime.gte") Integer with_runtime_gte,
            @Query("with_runtime.lte") Integer with_runtime_lte,
            @Query("include_null_first_air_dates") Boolean include_null_first_air_dates,
            @Query("with_original_language") String with_original_language,
            @Query("without_keywords") DiscoverFilter without_keywords,
            @Query("screened_theatrically") Boolean screened_theatrically,
            @Query("with_companies") DiscoverFilter with_companies,
            @Query("with_keywords") DiscoverFilter with_keywords,
            @Query("with_watch_providers") DiscoverFilter with_watch_providers,
            @Query("watch_region") String watch_region,
            @Query("with_watch_monetization_types") String with_watch_monetization_types
    );

}
