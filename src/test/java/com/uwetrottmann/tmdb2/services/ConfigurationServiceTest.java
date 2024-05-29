package com.uwetrottmann.tmdb2.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Configuration;
import com.uwetrottmann.tmdb2.entities.Jobs;
import com.uwetrottmann.tmdb2.entities.TmdbLanguage;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import retrofit2.Call;

public class ConfigurationServiceTest extends BaseTestCase {

    @Test
    public void test_configuration() throws IOException {
        Call<Configuration> call = getUnauthenticatedInstance().configurationService().configuration();
        Configuration config = call.execute().body();
        assertThat(config).isNotNull();
        assertThat(config.images).isNotNull();
        assertThat(config.images.base_url).isNotEmpty();
        assertThat(config.images.secure_base_url).isNotEmpty();
        assertThat(config.images.poster_sizes).isNotEmpty();
        assertThat(config.images.backdrop_sizes).isNotEmpty();
        assertThat(config.images.profile_sizes).isNotEmpty();
        assertThat(config.images.logo_sizes).isNotEmpty();
        assertThat(config.images.still_sizes).isNotEmpty();
        assertThat(config.change_keys).isNotEmpty();
    }

    @Test
    public void test_jobs() throws IOException {
        Call<List<Jobs>> call = getUnauthenticatedInstance().configurationService().jobs();
        List<Jobs> jobs = call.execute().body();

        assertThat(jobs).isNotNull();
        assertThat(jobs).isNotEmpty();
        for (Jobs dept : jobs) {
            assertThat(dept).isNotNull();
            assertThat(dept.department).isNotEmpty();
            assertThat(dept.jobs).isNotEmpty();
            assertThat(dept.jobs.contains(null)).isFalse();
        }
    }

    @Test
    public void translations() throws IOException {
        Call<List<TmdbLanguage>> call = getUnauthenticatedInstance().configurationService().languages();
        List<TmdbLanguage> languages = call.execute().body();
        assertThat(languages).isNotEmpty();
        languages.forEach(tmdbLanguage -> assertThat(tmdbLanguage.iso_639_1).isNotEmpty());
    }

    @Test
    public void primary_translations() throws IOException {
        Call<List<String>> call = getUnauthenticatedInstance().configurationService().primary_translations();
        List<String> languages = call.execute().body();
        assertThat(languages).isNotEmpty();
        // Assert content to detect languages getting added or removed.
        assertThat(languages).hasSize(80);
        assertThat(languages).containsExactlyInAnyOrder(
                "af-ZA",
                "ar-AE",
                "ar-SA",
                "be-BY",
                "bg-BG",
                "bn-BD",
                "ca-ES",
                "ch-GU",
                "cn-CN",
                "cs-CZ",
                "cy-GB",
                "da-DK",
                "de-AT",
                "de-CH",
                "de-DE",
                "el-GR",
                "en-AU",
                "en-CA",
                "en-GB",
                "en-IE",
                "en-NZ",
                "en-US",
                "eo-EO",
                "es-ES",
                "es-MX",
                "et-EE",
                "eu-ES",
                "fa-IR",
                "fi-FI",
                "fr-CA",
                "fr-FR",
                "ga-IE",
                "gd-GB",
                "gl-ES",
                "he-IL",
                "hi-IN",
                "hr-HR",
                "hu-HU",
                "id-ID",
                "it-IT",
                "ja-JP",
                "ka-GE",
                "kk-KZ",
                "kn-IN",
                "ko-KR",
                "ky-KG",
                "lt-LT",
                "lv-LV",
                "ml-IN",
                "mr-IN",
                "ms-MY",
                "ms-SG",
                "nb-NO",
                "nl-BE",
                "nl-NL",
                "no-NO",
                "pa-IN",
                "pl-PL",
                "pt-BR",
                "pt-PT",
                "ro-RO",
                "ru-RU",
                "si-LK",
                "sk-SK",
                "sl-SI",
                "sq-AL",
                "sr-RS",
                "sv-SE",
                "ta-IN",
                "te-IN",
                "th-TH",
                "tl-PH",
                "tr-TR",
                "uk-UA",
                "vi-VN",
                "zh-CN",
                "zh-HK",
                "zh-SG",
                "zh-TW",
                "zu-ZA"
        );
    }

}
