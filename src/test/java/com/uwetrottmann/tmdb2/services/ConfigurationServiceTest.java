
package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Configuration;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationServiceTest extends BaseTestCase {

    @Test
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
