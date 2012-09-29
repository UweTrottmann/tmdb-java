
package com.uwetrottmann.tmdb;

import com.uwetrottmann.tmdb.services.MoviesService;

/**
 * Class to manage service creation with default settings.
 */
public class ServiceManager {
    /** API key. */
    private String apiKeyValue;
    /** Connection timeout (in milliseconds). */
    private Integer connectionTimeout;
    /** Read timeout (in milliseconds). */
    private Integer readTimeout;

    /** Create a new manager instance. */
    public ServiceManager() {
    }

    /**
     * Set default API key.
     * 
     * @param value API key value.
     * @return Current instance for builder pattern.
     */
    public ServiceManager setApiKey(String value) {
        this.apiKeyValue = value;
        return this;
    }

    /**
     * Set default connection timeout.
     * 
     * @param connectionTimeout Timeout (in milliseconds).
     * @return Current instance for builder pattern.
     */
    public ServiceManager setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Set default read timeout.
     * 
     * @param readTimeout Timeout (in milliseconds).
     * @return Current instance for builder pattern.
     */
    public ServiceManager setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Set up a new service with the defaults.
     * 
     * @param service Service to set up.
     */
    private void setupService(TmdbApiService service) {
        if (this.apiKeyValue != null) {
            service.setApiKey(this.apiKeyValue);
        }
        if (this.connectionTimeout != null) {
            service.setConnectTimeout(this.connectionTimeout);
        }
        if (this.readTimeout != null) {
            service.setReadTimeout(this.readTimeout);
        }
    }

    public MoviesService moviesService() {
        MoviesService service = ServiceManager.createMoviesService();
        this.setupService(service);
        return service;
    }

    public static final MoviesService createMoviesService() {
        return new MoviesService();
    }
}
