package com.uwetrottmann.tmdb;

import junit.framework.TestCase;

public abstract class BaseTestCase extends TestCase {

    // Do NOT use this API key in your application, it is solely for testing tmdb-java!
    protected static final String API_KEY = "25da90e9f8f0b3892d8bdeb6c3d6267d";

    private static final boolean DEBUG = true;

    private final Tmdb manager = new Tmdb();

    @Override
    public void setUp() {
        manager.setApiKey(API_KEY);
        manager.setIsDebug(DEBUG);
    }

    protected final Tmdb getManager() {
        return manager;
    }
}
