package com.uwetrottmann.tmdb2;

import com.google.common.util.concurrent.RateLimiter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public abstract class BaseTestCase {

    private static final boolean PRINT_REQUESTS = true;

    // Do NOT use this API key in your application, it is only for testing tmdb-java!
    private static final String TEST_API_KEY = "25da90e9f8f0b3892d8bdeb6c3d6267d";
    // limit requests for tests to avoid hitting TMDb rate limit (40 requests/10 seconds)
    private static final RateLimiter rateLimiter = RateLimiter.create(4);

    private static final Tmdb unauthenticatedInstance = new BaseTestCase.TestTmdb(TEST_API_KEY);
    private static final Tmdb authenticatedInstance  = new BaseTestCase.TestTmdb(TEST_API_KEY);

    protected static Tmdb getUnauthenticatedInstance() {
        return unauthenticatedInstance;
    }

    protected static Tmdb getAuthenticatedInstance() {
        return authenticatedInstance;
    }

    private static class TestTmdb extends Tmdb {
        public TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            final Tmdb instance = this;
            builder.authenticator(new TmdbAuthenticator(instance));
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    rateLimiter.acquire();
                    return TmdbInterceptor.handleIntercept(chain, instance);
                }
            });
            if (PRINT_REQUESTS) {
                // add logging
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String s) {
                        // standard output is easier to read
                        System.out.println(s);
                    }
                });
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
        }
    }

}
