
package com.uwetrottmann.tmdb.services;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.entities.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationServiceTest extends BaseTestCase {

    public void test_configuration() {
        Configuration config = getManager().configurationService().configuration();
        assertThat(config).isNotNull();
        assertThat(config.images).isNotNull();
        assertThat(config.images.base_url).isNotEmpty();
        assertThat(config.images.secure_base_url).isNotEmpty();
        assertThat(config.images.poster_sizes).isNotEmpty();
        assertThat(config.images.backdrop_sizes).isNotEmpty();
        assertThat(config.images.profile_sizes).isNotEmpty();
        assertThat(config.images.logo_sizes).isNotEmpty();
        assertThat(config.change_keys).isNotEmpty();
    }
}
