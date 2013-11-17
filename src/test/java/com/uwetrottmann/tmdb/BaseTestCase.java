package com.uwetrottmann.tmdb;

import junit.framework.TestCase;

public abstract class BaseTestCase extends TestCase {

    protected static final String API_KEY = "<FILL-ME-IN>";

    private static final boolean DEBUG = false;

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
