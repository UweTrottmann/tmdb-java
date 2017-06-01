package com.uwetrottmann.tmdb2;

import com.google.common.util.concurrent.RateLimiter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.text.ParseException;

import static com.uwetrottmann.tmdb2.TmdbTestSuite.DEBUG;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.authenticatedInstance;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.destructEnvironment;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.initializeEnvironment;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.initialized;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.suiteRunning;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.unauthenticatedInstance;

public abstract class BaseTestCase {

    protected final Tmdb getUnauthenticatedInstance() {
        return unauthenticatedInstance;
    }

    protected final Tmdb getAuthenticatedInstance() {
        return authenticatedInstance;
    }


    static int testClassToRun = 0;

    @BeforeClass
    public static void ensureEnvironmentIsSet() throws IOException, ParseException, InterruptedException {
        if (initialized) {
            return;
        }

        initializeEnvironment();

        initialized = true;
    }

    @AfterClass
    public static void ensureEnvironmentDestruct() throws IOException, InterruptedException {
        if (suiteRunning) {
            return;
        }

        if (--testClassToRun != 0) {
            return;
        }

        destructEnvironment();
    }

    protected static class TestTmdb extends Tmdb {
        public TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            final Tmdb instance = this;
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    rateLimiter.acquire();
                    return TmdbInterceptor.handleIntercept(chain, instance);
                }
            });
            if (DEBUG) {
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
                builder.authenticator(new TmdbAuthenticator(instance));
            }
        }
    }

    private static final RateLimiter rateLimiter = RateLimiter.create(3.5);

}
