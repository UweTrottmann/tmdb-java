package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisode;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.TvAssertions;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvEpisodeExternalIds;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() throws IOException {
        Observable<TvEpisode> call = getUnauthenticatedInstance().rx.tvEpisodesService().episode(
                testTvEpisode.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        TvEpisode tvEpisode = call.singleOrError().blockingGet();

        assertTvEpisode(tvEpisode);
    }

    @Test
    public void test_episode_with_append_to_response() throws IOException {
        Observable<TvEpisode> call = getUnauthenticatedInstance().rx.tvEpisodesService().episode(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS
                )
        );

        TvEpisode tvEpisode = call.singleOrError().blockingGet();

        assertTvEpisodeDataIntegrity(tvEpisode);

        // credits
        assertCredits(tvEpisode.credits);

        // images
        assertThat(tvEpisode.images).isNotNull();
        assertImages(tvEpisode.images.stills);

        // external ids
        TvAssertions.assertTvEpisodeExternalIdsMatch(tvEpisode.external_ids);
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> call = getUnauthenticatedInstance().rx.tvEpisodesService().credits(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Credits credits = call.singleOrError().blockingGet();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Observable<Changes> call = getUnauthenticatedInstance().rx.tvEpisodesService().changes(
                testTvEpisode.id,
                new TmdbDate(testTvEpisodeChangesStartDate),
                new TmdbDate(testTvEpisodeChangesEndDate),
                null
        );

        Changes changes = call.singleOrError().blockingGet();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Observable<TvEpisodeExternalIds> call = getUnauthenticatedInstance().rx.tvEpisodesService().externalIds(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        TvEpisodeExternalIds ids = call.singleOrError().blockingGet();

        TvAssertions.assertTvEpisodeExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> call = getUnauthenticatedInstance().rx.tvEpisodesService().images(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Images images = call.singleOrError().blockingGet();
        assertThat(images.id).isNotNull();
        assertImages(images.stills);
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> call = getUnauthenticatedInstance().rx.tvEpisodesService().videos(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                null
        );

        Videos videos = call.singleOrError().blockingGet();
        assertVideos(videos);
    }

}
