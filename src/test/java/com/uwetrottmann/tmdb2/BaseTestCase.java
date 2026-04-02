// SPDX-License-Identifier: Apache-2.0
// Copyright 2012 Uwe Trottmann

package com.uwetrottmann.tmdb2;

import com.google.common.util.concurrent.RateLimiter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class BaseTestCase {

    private static final String TEST_API_KEY;

    static {
        Properties secrets = tryToloadSecrets();
        TEST_API_KEY = getVarFromEnvOrProperties(secrets, "TEST_API_KEY");
        checkVarNotEmpty(TEST_API_KEY, "TEST_API_KEY");
    }

    private static final boolean LOG_BODY = System.getenv("CI") == null;
    // limit requests for tests to avoid hitting TMDb rate limit (40 requests/10 seconds)
    @SuppressWarnings("UnstableApiUsage") private static final RateLimiter rateLimiter = RateLimiter.create(5);

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
            builder.addInterceptor(chain -> {
                rateLimiter.acquire();
                return TmdbInterceptor.handleIntercept(chain, instance);
            });
            // add logging, standard output is easier to read
            // but only log headers on CI to keep logs short
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(System.out::println);
            logging.setLevel(LOG_BODY ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);
            builder.addNetworkInterceptor(logging);
        }
    }

    protected static Properties tryToloadSecrets() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("secrets.properties")) {
            properties.load(input);
        } catch (IOException e) {
            // File not found or cannot be read, will try environment variables
        }

        return properties;
    }

    protected static String getVarFromEnvOrProperties(Properties properties, String key) {
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return properties.getProperty(key);
    }

    private static void checkVarNotEmpty(String value, String name) {
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException(name + " must be set via environment variable or secrets.properties file");
        }
    }

}
