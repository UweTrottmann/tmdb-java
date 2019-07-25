package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.CollectionResultsPage;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb2.entities.MediaResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    /**
     * Search for companies.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-companies">Documentation</a>
     */
    @GET("search/company")
    Call<CompanyResultsPage> company(
            @Query("query") String query,
            @Query("page") Integer page
    );

    /**
     * Search for collections.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-collections">Documentation</a>
     */
    @GET("search/collection")
    Call<CollectionResultsPage> collection(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Search for keywords.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-keywords">Documentation</a>
     */
    @GET("search/keyword")
    Call<KeywordResultsPage> keyword(
            @Query("query") String query,
            @Query("page") Integer page
    );

    /**
     * Search for movies.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-movies">Documentation</a>
     */
    @GET("search/movie")
    Call<MovieResultsPage> movie(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("region") String region,
            @Query("include_adult") Boolean includeAdult,
            @Query("year") Integer year,
            @Query("primary_release_year") Integer primaryReleaseYear
    );

    /**
     * Search multiple models in a single request.
     * Multi search currently supports searching for movies,
     * tv shows and people in a single request.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/multi-search">Documentation</a>
     */
    @GET("search/multi")
    Call<MediaResultsPage> multi(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("region") String region,
            @Query("include_adult") Boolean includeAdult
    );

    /**
     * Search for people.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-people">Documentation</a>
     */
    @GET("search/person")
    Call<PersonResultsPage> person(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("region") String region,
            @Query("include_adult") Boolean includeAdult
    );

    /**
     * Search for TV shows.
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-tv-shows">Documentation</a>
     */
    @GET("search/tv")
    Call<TvShowResultsPage> tv(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("first_air_date_year") Integer firstAirDateYear
    );
}
