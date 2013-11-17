
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Configuration;

public class ConfigurationServiceTest extends BaseTestCase {

    public void test_configuration() {
        Configuration config = getManager().configurationService().configuration();
        assertNotNull("Result was null.", config);
        assertNotNull("Config images was null.", config.images);
        assertNotNull("Config base_url was null.", config.images.base_url);
        assertNotNull("Config poster_sizes was null.", config.images.poster_sizes);
        assertNotNull("Config backdrop_sizes was null.", config.images.backdrop_sizes);
        assertNotNull("Config profile_sizes was null.", config.images.profile_sizes);
        assertNotNull("Config logo_sizes was null.", config.images.logo_sizes);
    }
}
