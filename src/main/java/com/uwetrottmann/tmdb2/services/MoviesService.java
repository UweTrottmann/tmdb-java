package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.AlternativeTitles;
import com.uwetrottmann.tmdb2.utils.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.Keywords;
import com.uwetrottmann.tmdb2.entities.ListResultsPage;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.ReleaseDate;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResults;
import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.utils.TmdbDate;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface MoviesService {

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Values:</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     */
    @Deprecated
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Values:</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Values:</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @Deprecated
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Grab the following account states for a session:
     * <p>
     * * Movie rating
     * * If it belongs to your watchlist
     * * If it belongs to your favorite list
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/account_states")
    Call<AccountStates> accountStates(
            @Path("movie_id") int movieId
    );

    /**
     * Get the alternative titles for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     * @param country <em>Optional.</em> ISO 3166-1 code.
     */
    @GET("movie/{movie_id}/alternative_titles")
    Call<AlternativeTitles> alternativeTitles(
            @Path("movie_id") int movieId,
            @Query("country") String country
    );

    /**
     * Get the alternative titles for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/alternative_titles")
    Call<AlternativeTitles> alternativeTitles(
            @Path("movie_id") int movieId
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("start_date") TmdbDate start_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("start_date") TmdbDate start_date
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param page       <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId
    );

    /**
     * Get the cast and crew information for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/credits")
    Call<Credits> credits(
            @Path("movie_id") int movieId
    );

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/images")
    Call<Images> images(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/images")
    Call<Images> images(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/images")
    Call<Images> images(
            @Path("movie_id") int movieId
    );

    /**
     * Get the plot keywords for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/keywords")
    Call<Keywords> keywords(
            @Path("movie_id") int movieId
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/lists")
    Call<ListResultsPage> lists(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/lists")
    Call<ListResultsPage> lists(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/lists")
    Call<ListResultsPage> lists(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/lists")
    Call<ListResultsPage> lists(
            @Path("movie_id") int movieId,
            @Query("page") Integer page
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/lists")
    Call<ListResultsPage> lists(
            @Path("movie_id") int movieId
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/similar")
    Call<MovieResultsPage> similar(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/similar")
    Call<MovieResultsPage> similar(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/similar")
    Call<MovieResultsPage> similar(
            @Path("movie_id") int movieId,
            @Query("page") Integer page
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/similar")
    Call<MovieResultsPage> similar(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/similar")
    Call<MovieResultsPage> similar(
            @Path("movie_id") int movieId
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId,
            @Query("page") Integer page
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId
    );

    /**
     * Get the release dates, certifications and related information by country for a specific movie id.
     * <p>
     * The results are keyed by iso_3166_1 code and contain a type value which on our system, maps to:
     * {@link ReleaseDate#TYPE_PREMIERE}, {@link ReleaseDate#TYPE_THEATRICAL_LIMITED},
     * {@link ReleaseDate#TYPE_THEATRICAL}, {@link ReleaseDate#TYPE_DIGITAL}, {@link ReleaseDate#TYPE_PHYSICAL},
     * {@link ReleaseDate#TYPE_TV}
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/release_dates")
    Call<ReleaseDatesResults> releaseDates(
            @Path("movie_id") int movieId
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId,
            @Query("page") Integer page
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId
    );

    /**
     * Get the translations for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/translations")
    Call<Translations> translations(
            @Path("movie_id") int movieId
    );

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/{movie_id}/videos")
    Call<Videos> videos(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/videos")
    Call<Videos> videos(
            @Path("movie_id") int movieId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     */
    @GET("movie/{movie_id}/videos")
    Call<Videos> videos(
            @Path("movie_id") int movieId
    );

    /**
     * Get the latest movie id.
     */
    @GET("movie/latest")
    Call<Movie> latest();

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/now_playing")
    Call<MovieResultsPage> nowPlaying(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Call<MovieResultsPage> nowPlaying(
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/now_playing")
    Call<MovieResultsPage> nowPlaying(
            @Query("page") Integer page
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Call<MovieResultsPage> nowPlaying(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     */
    @GET("movie/now_playing")
    Call<MovieResultsPage> nowPlaying();

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/popular")
    Call<MovieResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/popular")
    Call<MovieResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/popular")
    Call<MovieResultsPage> popular(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/popular")
    Call<MovieResultsPage> popular(
            @Query("page") Integer page
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     */
    @GET("movie/popular")
    Call<MovieResultsPage> popular();

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/top_rated")
    Call<MovieResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/top_rated")
    Call<MovieResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/top_rated")
    Call<MovieResultsPage> topRated(
            @Query("page") Integer page
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/top_rated")
    Call<MovieResultsPage> topRated(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     */
    @GET("movie/top_rated")
    Call<MovieResultsPage> topRated();

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("movie/upcoming")
    Call<MovieResultsPage> upcoming(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/upcoming")
    Call<MovieResultsPage> upcoming(
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/upcoming")
    Call<MovieResultsPage> upcoming(
            @Query("page") Integer page
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/upcoming")
    Call<MovieResultsPage> upcoming(
            @Query("language") TmdbLocale language
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     */
    @GET("movie/upcoming")
    Call<MovieResultsPage> upcoming();

    /**
     * Sets the Rating for the movie with the specified id.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param movieId            A Movie TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     * @param body               <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("movie/{movie_id}/rating")
    Call<Status> addRating(
            @Path("movie_id") Integer movieId,
            @Query("authentication") AuthenticationType authenticationType,
            @Body RatingObject body
    );

    /**
     * Sets the Rating for the movie with the specified id.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     * @param body    <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("movie/{movie_id}/rating")
    Call<Status> addRating(
            @Path("movie_id") Integer movieId,
            @Body RatingObject body
    );

    /**
     * Deletes the Rating for the movie with the specified id.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param movieId            A Movie TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     */
    @DELETE("movie/{movie_id}/rating")
    Call<Status> deleteRating(
            @Path("movie_id") Integer movieId,
            @Query("authentication") AuthenticationType authenticationType
    );

    /**
     * Deletes the Rating for the movie with the specified id.
     * <p>
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     */
    @DELETE("movie/{movie_id}/rating")
    Call<Status> deleteRating(
            @Path("movie_id") Integer movieId
    );

}
