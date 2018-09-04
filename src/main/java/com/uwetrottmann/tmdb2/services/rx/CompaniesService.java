package com.uwetrottmann.tmdb2.services.rx;

import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.DiscoverFilter;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.enumerations.SortBy;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CompaniesService {
    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId A Company TMDb id.
     */
    @GET("company/{company_id}")
    Observable<Company> summary(
            @Path("company_id") int companyId
    );

    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId        A Company TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     */
    @GET("company/{company_id}")
    Observable<Company> summary(
            @Path("company_id") int companyId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic company information for a specific A Company TMDb id.
     *
     * @param companyId        A Company TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movies
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("company/{company_id}")
    Observable<Company> summary(
            @Path("company_id") int companyId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the movies for a specific A Company TMDb id.
     * <p>
     * Is highly recommend using {@link DiscoverService#discoverMovie(
     *String, String, SortBy, String, String, String, Boolean, Boolean,
     * Integer, Integer, TmdbDate, TmdbDate, TmdbDate, TmdbDate, Integer,
     * Integer, Float, Float, DiscoverFilter, DiscoverFilter, DiscoverFilter,
     * DiscoverFilter, DiscoverFilter, DiscoverFilter, Integer, DiscoverFilter,
     * Integer, Integer, DiscoverFilter, String, DiscoverFilter) discoverMovie}
     * instead of this method as it is much more flexible.
     *
     * @param companyId A Company TMDb id.
     */
    @GET("company/{company_id}/movies")
    Observable<MovieResultsPage> movies(
            @Path("company_id") int companyId
    );
}

