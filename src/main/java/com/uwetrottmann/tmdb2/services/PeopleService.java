package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.utils.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonCredits;
import com.uwetrottmann.tmdb2.entities.PersonExternalIds;
import com.uwetrottmann.tmdb2.entities.PersonImages;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TaggedImagesResultsPage;
import com.uwetrottmann.tmdb2.utils.TmdbDate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface PeopleService {

    /**
     * Get the general person information for a specific id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId,
            @Path("language") TmdbLocale language
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     * @param language         <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId,
            @Path("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId,
            @Path("language") TmdbLocale language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
            @Path("person_id") int personId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );


    /**
     * Get the movie credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("person/{person_id}/movie_credits")
    Call<PersonCredits> movieCredits(
            @Path("person_id") int personId,
            @Query("language") String language
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/movie_credits")
    Call<PersonCredits> movieCredits(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/movie_credits")
    Call<PersonCredits> movieCredits(
            @Path("person_id") int personId
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("person/{person_id}/tv_credits")
    Call<PersonCredits> tvCredits(
            @Path("person_id") int personId,
            @Query("language") String language
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/tv_credits")
    Call<PersonCredits> tvCredits(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/tv_credits")
    Call<PersonCredits> tvCredits(
            @Path("person_id") int personId
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("person/{person_id}/combined_credits")
    Call<PersonCredits> combinedCredits(
            @Path("person_id") int personId,
            @Query("language") String language
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/combined_credits")
    Call<PersonCredits> combinedCredits(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/combined_credits")
    Call<PersonCredits> combinedCredits(
            @Path("person_id") int personId
    );

    /**
     * Get the external ids for a specific person id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/external_ids")
    Call<PersonExternalIds> externalIds(
            @Path("person_id") int personId
    );

    /**
     * Get the images for a specific person id.
     */
    @GET("person/{person_id}/images")
    Call<PersonImages> images(
            @Path("person_id") int personId
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     *
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") String language,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     *
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.r.
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language,
            @Query("start_date") TmdbDate start_date,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language,
            @Query("page") Integer page
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     */
    @GET("person/{person_id}/changes")
    Call<Changes> changes(
            @Path("person_id") int personId,
            @Query("page") Integer page
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @Deprecated
    @GET("person/{person_id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId,
            @Query("page") Integer page,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId,
            @Query("language") TmdbLocale language
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     */
    @GET("person/{person_id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId,
            @Query("page") Integer page
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.uwetrottmann.tmdb2.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/tagged_images")
    Call<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId
    );

    /**
     * Get the list of popular people on The Movie Database. This list refreshes every day.
     */
    @GET("person/popular")
    Call<PersonResultsPage> popular(
            @Query("page") Integer page
    );

    /**
     * Get the list of popular people on The Movie Database. This list refreshes every day.
     */
    @GET("person/popular")
    Call<PersonResultsPage> popular();

    /**
     * Get the latest person id.
     */
    @GET("person/latest")
    Call<Person> latest();

}
