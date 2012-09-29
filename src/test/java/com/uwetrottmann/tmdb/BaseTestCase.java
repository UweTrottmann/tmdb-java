package com.uwetrottmann.tmdb;

import com.uwetrottmann.tmdb.ServiceManager;

import junit.framework.TestCase;

public abstract class BaseTestCase extends TestCase {
	protected static final String API_KEY = "<FILL-ME-IN>";
	
	private final ServiceManager manager = new ServiceManager();
	
	@Override
	public void setUp() {
		manager.setApiKey(API_KEY);
	}
	
	protected final ServiceManager getManager() {
		return manager;
	}
}
