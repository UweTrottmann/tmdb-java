// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.uwetrottmann.tmdb2.entities.AlternativeTitle;
import com.uwetrottmann.tmdb2.entities.AlternativeTitles;
import com.uwetrottmann.tmdb2.entities.BaseRatingObject;
import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import com.uwetrottmann.tmdb2.entities.ContentRating;
import com.uwetrottmann.tmdb2.entities.ContentRatings;
import com.uwetrottmann.tmdb2.entities.Image;
import com.uwetrottmann.tmdb2.entities.NetworkImage;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TaggedImage;
import com.uwetrottmann.tmdb2.entities.TaggedImagesResultsPage;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.entities.WatchProviders;
import java.util.List;

public class GenericAssertions {


    public static void assertBaseResultsPage(BaseResultsPage results) {
        assertThat(results).isNotNull();
        assertThat(results.page).isNotNull();
        assertThat(results.total_pages).isNotNull();
        assertThat(results.total_results).isNotNull();
        assertThat(results.results).isNotNull();
        assertThat(results.results).isNotEmpty();


    }

    public static void assertVideos(Videos videos) {
        for (Videos.Video video : videos.results) {
            assertThat(video).isNotNull();
            assertThat(video.id).isNotNull();
            assertThat(video.iso_639_1).isNotNull();
            assertThat(video.key).isNotNull();
            assertThat(video.name).isNotNull();
            assertThat(video.site).isNotNull();
            assertThat(video.size).isNotNull();
            assertThat(video.type).isNotNull();
        }
    }

    public static void assertWatchProviderCountryInfo(WatchProviders.CountryInfo info) {
        assertWatchProviders(info.flatrate);
        assertWatchProviders(info.free);
        assertWatchProviders(info.ads);
        assertWatchProviders(info.buy);
    }

    private static void assertWatchProviders(List<WatchProviders.WatchProvider> providers) {
        for (WatchProviders.WatchProvider provider : providers) {
            assertThat(provider).isNotNull();
            assertThat(provider.display_priority).isGreaterThanOrEqualTo(0);
            assertThat(provider.logo_path).isNotEmpty();
            assertThat(provider.provider_name).isNotEmpty();
            assertThat(provider.provider_id).isNotNull();
        }
    }

    public static void assertAlternativeTitles(AlternativeTitles alternativeTitles) {
        assertThat(alternativeTitles).isNotNull();
        assertThat(alternativeTitles.titles).isNotNull();
        assertThat(alternativeTitles.titles).isNotEmpty();
        for (AlternativeTitle alternativeTitle : alternativeTitles.titles) {
            assertThat(alternativeTitle).isNotNull();
            assertThat(alternativeTitle.iso_3166_1).isNotNull();
            assertThat(alternativeTitle.title).isNotNull();
        }
    }

    public static void assertTranslations(Translations translations) {
        assertThat(translations).isNotNull();
        assertThat(translations.translations).isNotNull();
        assertThat(translations.translations).isNotEmpty();
        for (Translations.Translation translation : translations.translations) {
            assertThat(translation.english_name).isNotNull();
            assertThat(translation.name).isNotNull();
            assertThat(translation.iso_639_1).isNotNull();
            assertThat(translation.iso_3166_1).isNotNull();
            assertThat(translation.data).isNotNull();
        }
    }

    public static void assertImage(Image image) {
        assertThat(image).isNotNull();
        assertThat(image.file_path).isNotNull();
        assertThat(image.width).isGreaterThan(0);
        assertThat(image.height).isGreaterThan(0);
        assertThat(image.aspect_ratio).isGreaterThan(0);
        assertThat(image.vote_average).isGreaterThanOrEqualTo(0);
        assertThat(image.vote_count).isGreaterThanOrEqualTo(0);
    }


    public static void assertTaggedImage(TaggedImage image) {
        assertImage(image);
        assertThat(image.media_type).isNotNull();
        assertThat(image.media).isNotNull();
        switch(image.media_type) {
            case "movie":
                assertThat(image.media.movie).isNotNull();
                break;
            case "tv":
                assertThat(image.media.tvShow).isNotNull();
                break;
        }
    }

    public static void assertNetworkImage(NetworkImage image) {
        assertImage(image);
        assertThat(image.file_type).isNotNull();
    }

    public static void assertImages(List<Image> images) {
        assertThat(images).isNotNull();
        assertThat(images).isNotEmpty();
        for (Image image : images) {
            assertImage(image);
        }
    }

    public static void assertTaggedImages(TaggedImagesResultsPage images) {
        assertBaseResultsPage(images);

        for (TaggedImage image : images.results) {
            assertTaggedImage(image);
        }
    }

    public static void assertNetworkImages(List<NetworkImage> images) {
        assertThat(images).isNotNull();
        assertThat(images).isNotEmpty();
        for (NetworkImage image : images) {
            assertNetworkImage(image);
        }
    }

    public static void assertContentRatings(ContentRatings contentRatings) {
        assertThat(contentRatings).isNotNull();

        assertThat(contentRatings.results).isNotNull();
        assertThat(contentRatings.results).isNotEmpty();
        for (ContentRating contentRating : contentRatings.results) {
            assertThat(contentRating).isNotNull();
            assertThat(contentRating.iso_3166_1).isNotNull();
            assertThat(contentRating.rating).isNotNull();
        }
    }

    public static void assertBaseRatingObject(BaseRatingObject baseRatingObject) {
        assertThat(baseRatingObject).isNotNull();
        assertThat(baseRatingObject.rating).isNotNull();
    }

    public static void assertStatus(Status status) {
        assertThat(status).isNotNull();
        assertThat(status.status_code).isNotNull();
        if (status.status_code != 1 && status.status_code != 12 && status.status_code != 13) {
            fail("Status Code is not a success one.");
        }
        assertThat(status.status_message).isNotNull();
    }
}
