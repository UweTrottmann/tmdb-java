package com.uwetrottmann.tmdb2;

import com.google.common.util.concurrent.RateLimiter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Vector;

import static com.uwetrottmann.tmdb2.TmdbTestSuite.authenticatedInstance;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.initialized;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.suiteRunning;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.unauthenticatedInstance;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.DEBUG;

public abstract class BaseTestCase {

    protected final Tmdb getUnauthenticatedInstance() {
        return unauthenticatedInstance;
    }

    protected final Tmdb getAuthenticatedInstance() {
        return authenticatedInstance;
    }

    private static int testClassToRun = 0;

    @BeforeClass
    public static void ensureEnvironmentIsSet() throws IOException, ParseException {
        if (suiteRunning)
            return;

        if (initialized)
            return;

        try {
            Field field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);

            System.out.println("Tests Running Standalone.");

            System.out.println("Test Classes To Run:");

            @SuppressWarnings({ "unchecked", "rawtypes" })
            Vector<Class> classes = (Vector<Class>) field.get(BaseTestCase.class.getClassLoader());
            for (Class<?> clazz : classes) {
                if (!clazz.getSimpleName().equals("Test") && clazz.getName().endsWith("Test")) {
                    System.out.println("\t"+clazz.getSimpleName());
                    System.out.println("\t\tTests:");
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(Test.class)) {
                            System.out.println("\t\t\t"+method.getName());
                        }
                    }
                    testClassToRun++;
                }
            }
        } catch (Exception ignore) {
        }

        TmdbTestSuite.initializeEnvironment();
    }

    @AfterClass
    public static void ensureEnvironmentDestruct() throws IOException {
        if (suiteRunning)
            return;

        if (--testClassToRun != 0)
            return;

        TmdbTestSuite.destructEnvironment();
    }

    protected static class TestTmdb extends Tmdb {
        public TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            //super.setOkHttpClientDefaults(builder);
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
