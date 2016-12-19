package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.CastMember;
import com.uwetrottmann.tmdb2.entities.CrewMember;
import com.uwetrottmann.tmdb2.entities.Image;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.Videos;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseTestCase {

    // Do NOT use this API key in your application, it is solely for testing tmdb-java!
    private static final String API_KEY = "25da90e9f8f0b3892d8bdeb6c3d6267d";

    private static final boolean DEBUG = true;

    private static final Tmdb manager = new TestTmdb(API_KEY);

    static class TestTmdb extends Tmdb {

        public TestTmdb(String apiKey) {
            super(apiKey);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            super.setOkHttpClientDefaults(builder);
            if (DEBUG) {
                // add logging
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String s) {
                        // standard output is easier to read
                        System.out.println(s);
                    }
                });
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
        }
    }

    protected final Tmdb getManager() {
        return manager;
    }

    protected static void assertCrewCredits(List<CrewMember> crew) {
        assertThat(crew).isNotNull();
        assertThat(crew).isNotEmpty();

        for (CrewMember member : crew) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.department).isNotNull();
            assertThat(member.job).isNotNull();
        }
    }

    protected static void assertCastCredits(List<CastMember> cast) {
        assertThat(cast).isNotNull();
        assertThat(cast).isNotEmpty();

        for (CastMember member : cast) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.character).isNotNull();
            assertThat(member.order).isNotNull();
        }
    }

    protected static void assertImages(List<Image> images){
        assertThat(images).isNotNull();
        assertThat(images).isNotEmpty();

        for(Image image : images) {
            assertThat(image.file_path).isNotNull();
            assertThat(image.width).isNotNull();
            assertThat(image.height).isNotNull();
            assertThat(image.aspect_ratio).isGreaterThan(0);
            assertThat(image.vote_average).isGreaterThanOrEqualTo(0);
            assertThat(image.vote_count).isGreaterThanOrEqualTo(0);
        }
    }

    public static void assertMedia(List<Media> list) {
        for (Media media : list) {
            if ("movie".equals(media.media_type)) {
                assertThat(media.adult).isNotNull();
                assertThat(media.release_date).isNotNull();
                assertThat(media.original_title).isNotNull();
                assertThat(media.title).isNotNull();
            }
            assertThat(media.backdrop_path).isNotNull();
            assertThat(media.id).isNotNull();
            assertThat(media.poster_path).isNotNull();
            assertThat(media.popularity).isNotNull().isGreaterThan(0);
            assertThat(media.vote_average).isNotNull().isGreaterThan(0);
            assertThat(media.vote_count).isNotNull().isGreaterThan(0);
            assertThat(media.media_type).isNotNull();
        }
    }

    protected static void assertVideos(Videos videos) {
        assertThat(videos.id).isNotNull();
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
}
