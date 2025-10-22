// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

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
        // Assert content to detect languages getting added or removed and note them in changelog
        assertThat(languages).containsExactlyInAnyOrder(
                "af-ZA",
                "ar-AE",
                "ar-BH",
                "ar-EG",
                "ar-IQ",
                "ar-JO",
                "ar-LY",
                "ar-MA",
                "ar-QA",
                "ar-SA",
                "ar-TD",
                "ar-YE",
                "be-BY",
                "bg-BG",
                "bn-BD",
                "bn-IN",
                "br-FR",
                "ca-AD",
                "ca-ES",
                "ch-GU",
                "cs-CZ",
                "cy-GB",
                "da-DK",
                "de-AT",
                "de-CH",
                "de-DE",
                "el-CY",
                "el-GR",
                "en-AG",
                "en-AU",
                "en-BB",
                "en-BZ",
                "en-CA",
                "en-CM",
                "en-GB",
                "en-GG",
                "en-GH",
                "en-GI",
                "en-GY",
                "en-IE",
                "en-JM",
                "en-KE",
                "en-LC",
                "en-MW",
                "en-NZ",
                "en-PG",
                "en-TC",
                "en-US",
                "en-ZM",
                "en-ZW",
                "eo-EO",
                "es-AR",
                "es-CL",
                "es-DO",
                "es-EC",
                "es-ES",
                "es-GQ",
                "es-GT",
                "es-HN",
                "es-MX",
                "es-NI",
                "es-PA",
                "es-PE",
                "es-PY",
                "es-SV",
                "es-UY",
                "et-EE",
                "eu-ES",
                "fa-IR",
                "fi-FI",
                "fr-BF",
                "fr-CA",
                "fr-CD",
                "fr-CI",
                "fr-FR",
                "fr-GF",
                "fr-GP",
                "fr-MC",
                "fr-ML",
                "fr-MU",
                "fr-PF",
                "ga-IE",
                "gd-GB",
                "gl-ES",
                "he-IL",
                "hi-IN",
                "hr-HR",
                "hu-HU",
                "id-ID",
                "it-IT",
                "it-VA",
                "ja-JP",
                "ka-GE",
                "kk-KZ",
                "kn-IN",
                "ko-KR",
                "ku-TR",
                "ky-KG",
                "lt-LT",
                "lv-LV",
                "ml-IN",
                "mr-IN",
                "ms-MY",
                "ms-SG",
                "nb-NO",
                "ne-NP",
                "nl-BE",
                "nl-NL",
                "no-NO",
                "oc-FR",
                "pa-IN",
                "pl-PL",
                "pt-AO",
                "pt-BR",
                "pt-MZ",
                "pt-PT",
                "ro-MD",
                "ro-RO",
                "ru-RU",
                "si-LK",
                "sk-SK",
                "sl-SI",
                "so-SO",
                "sq-AL",
                "sq-XK",
                "sr-ME",
                "sr-RS",
                "sv-SE",
                "sw-TZ",
                "ta-IN",
                "te-IN",
                "th-TH",
                "tl-PH",
                "tr-TR",
                "uk-UA",
                "ur-PK",
                "uz-UZ",
                "vi-VN",
                "zh-CN",
                "zh-HK",
                "zh-SG",
                "zh-TW",
                "zu-ZA"
        );
    }

}
