package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.exceptions.TmdbInvalidParametersException;
import com.uwetrottmann.tmdb2.services.AccountService;
import com.uwetrottmann.tmdb2.services.AuthenticationService;
import com.uwetrottmann.tmdb2.services.CertificationsService;
import com.uwetrottmann.tmdb2.services.ChangesService;
import com.uwetrottmann.tmdb2.services.CollectionsService;
import com.uwetrottmann.tmdb2.services.CompaniesService;
import com.uwetrottmann.tmdb2.services.ConfigurationService;
import com.uwetrottmann.tmdb2.services.CreditsService;
import com.uwetrottmann.tmdb2.services.DiscoverService;
import com.uwetrottmann.tmdb2.services.FindService;
import com.uwetrottmann.tmdb2.services.GenresService;
import com.uwetrottmann.tmdb2.services.GuestSessionService;
import com.uwetrottmann.tmdb2.services.JobsService;
import com.uwetrottmann.tmdb2.services.KeywordsService;
import com.uwetrottmann.tmdb2.services.ListsService;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.NetworksService;
import com.uwetrottmann.tmdb2.services.PeopleService;
import com.uwetrottmann.tmdb2.services.ReviewsService;
import com.uwetrottmann.tmdb2.services.SearchService;
import com.uwetrottmann.tmdb2.services.TimezonesService;
import com.uwetrottmann.tmdb2.services.TvEpisodesService;
import com.uwetrottmann.tmdb2.services.TvSeasonsService;
import com.uwetrottmann.tmdb2.services.TvShowService;
import com.uwetrottmann.tmdb2.utils.DiscoverMovieBuilder;
import com.uwetrottmann.tmdb2.utils.DiscoverTvBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 * <p>
 * <p>Create an instance of this class and then call any of the service methods.
 * <p>
 * <p>The service methods take care of constructing the required {@link Retrofit} instance and creating the service. It
 * is recommended you use your existing OkHttp client instance by overriding {@link #okHttpClient()}.
 * <p>
 * <p>Only one {@link Retrofit} instance is created upon the first and re-used for any consequent service method call.
 */
public class Tmdb {

    /**
     * API Related Information
     */
    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";

    /**
     * API key, Session and Guest Session query parameter names.
     */
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_SESSION_ID = "session_id";
    public static final String PARAM_GUEST_SESSION_ID = "guest_session_id";

    /**
     * Authentication and Guest Session end point paths.
     */
    public static final String PATH_AUTHENTICATION = "authentication";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private String apiKey;

    private Boolean hasGuestSession = false;
    private Boolean hasAccountSession = false;

    Boolean isLoggedIn = false;

    String username;
    String password;
    String sessionId;
    String guestSessionId;

    /**
     * Create a new manager instance.
     *
     * @param apiKey Your TMDB API key.
     */
    public Tmdb(String apiKey) {
        this.apiKey = apiKey;
    }

    public void accountSession(String username, String password) throws TmdbInvalidParametersException {
        if (username == null || password == null) {
            throw new TmdbInvalidParametersException(401, "Username and Password may not be null");
        }

        this.username = username;
        this.password = password;
        hasAccountSession = true;
    }

    public void guestSession() {
        hasGuestSession = true;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Boolean hasGuestSession() {
        return hasGuestSession;
    }

    public Boolean hasAccountSession() {
        return hasAccountSession;
    }

    public void apiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiKey() {
        return apiKey;
    }

    /**
     * Creates a {@link Retrofit.Builder} that sets the base URL, adds a Gson converter and sets {@link #okHttpClient()}
     * as its client.
     *
     * @see #okHttpClient()
     */
    protected Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.getGsonBuilder().create()))
                .client(okHttpClient());
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see #setOkHttpClientDefaults(OkHttpClient.Builder)
     */
    protected synchronized OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            setOkHttpClientDefaults(builder);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TmdbInterceptor(this)).authenticator(new TmdbAuthenticator(this));
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, auth changed), builds a new one.
     * <p>When building, sets the base url and a custom client with an {@link Interceptor} which supplies authentication
     * data.
     */
    protected Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build();
        }
        return retrofit;
    }

    public AccountService accountService() {
        return getRetrofit().create(AccountService.class);
    }

    public AuthenticationService authenticationService() {
        return getRetrofit().create(AuthenticationService.class);
    }

    public CertificationsService certificationsService() {
        return getRetrofit().create(CertificationsService.class);
    }

    public ChangesService changesService() {
        return getRetrofit().create(ChangesService.class);
    }

    public CollectionsService collectionService() {
        return getRetrofit().create(CollectionsService.class);
    }

    public CompaniesService companiesService() {
        return getRetrofit().create(CompaniesService.class);
    }

    public ConfigurationService configurationService() {
        return getRetrofit().create(ConfigurationService.class);
    }

    public CreditsService creditsService() {
        return getRetrofit().create(CreditsService.class);
    }

    public DiscoverService discoverService() {
        return getRetrofit().create(DiscoverService.class);
    }

    public FindService findService() {
        return getRetrofit().create(FindService.class);
    }

    public GenresService genreService() {
        return getRetrofit().create(GenresService.class);
    }

    public GuestSessionService guestSessionService() {
        return getRetrofit().create(GuestSessionService.class);
    }

    public JobsService jobsService() {
        return getRetrofit().create(JobsService.class);
    }

    public KeywordsService keywordsService() {
        return getRetrofit().create(KeywordsService.class);
    }

    public ListsService listsService() {
        return getRetrofit().create(ListsService.class);
    }

    public MoviesService moviesService() {
        return getRetrofit().create(MoviesService.class);
    }

    public NetworksService networksService() {
        return getRetrofit().create(NetworksService.class);
    }

    public PeopleService personService() {
        return getRetrofit().create(PeopleService.class);
    }

    public ReviewsService reviewsService() {
        return getRetrofit().create(ReviewsService.class);
    }

    public SearchService searchService() {
        return getRetrofit().create(SearchService.class);
    }

    public TimezonesService timezonesService() {
        return getRetrofit().create(TimezonesService.class);
    }

    public TvShowService tvService() {
        return getRetrofit().create(TvShowService.class);
    }

    public TvSeasonsService tvSeasonsService() {
        return getRetrofit().create(TvSeasonsService.class);
    }

    public TvEpisodesService tvEpisodesService() {
        return getRetrofit().create(TvEpisodesService.class);
    }


    public DiscoverMovieBuilder discoverMovie() {
        return new DiscoverMovieBuilder(discoverService());
    }

    public DiscoverTvBuilder discoverTv() {
        return new DiscoverTvBuilder(discoverService());
    }

}
