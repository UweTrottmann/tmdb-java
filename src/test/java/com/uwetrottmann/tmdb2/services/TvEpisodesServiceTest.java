package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.annotations.RequiresAccountSession;
import com.uwetrottmann.tmdb2.annotations.RequiresGuestSession;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Status;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvExternalIds;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import com.uwetrottmann.tmdb2.enumerations.AuthenticationType;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvEpisodeChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.TmdbTestSuite.accountDataInitialized;
import static com.uwetrottmann.tmdb2.assertions.AccountAssertions.assertBaseAccountStates;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisode;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvEpisodeExternalIdsDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvExternalIds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() throws IOException {
        Call<TvEpisode> call = getUnauthenticatedInstance().tvEpisodesService().episode(
                testTvEpisode.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        TvEpisode tvEpisode = call.execute().body();

        assertTvEpisode(tvEpisode);
    }

    @Test
    public void test_episode_with_append_to_response() throws IOException {
        Call<TvEpisode> call = getUnauthenticatedInstance().tvEpisodesService().episode(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS
                )
        );

        TvEpisode tvEpisode = call.execute().body();

        assertTvEpisodeDataIntegrity(tvEpisode);

        // credits
        assertCredits(tvEpisode.credits);

        // images
        assertThat(tvEpisode.images).isNotNull();
        assertImages(tvEpisode.images.stills);

        // external ids
        assertTvEpisodeExternalIdsDataIntegrity(tvEpisode.external_ids);
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getUnauthenticatedInstance().tvEpisodesService().credits(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Credits credits = call.execute().body();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().tvEpisodesService().changes(
                testTvEpisode.id,
                new TmdbDate(testTvEpisodeChangesStartDate),
                new TmdbDate(testTvEpisodeChangesEndDate),
                null
        );

        Changes changes = call.execute().body();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<TvExternalIds> call = getUnauthenticatedInstance().tvEpisodesService().externalIds(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        TvExternalIds ids = call.execute().body();

        assertTvExternalIds(ids);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getUnauthenticatedInstance().tvEpisodesService().images(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        Images images = call.execute().body();
        assertThat(images.id).isNotNull();
        assertImages(images.stills);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getUnauthenticatedInstance().tvEpisodesService().videos(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number,
                null
        );

        Videos videos = call.execute().body();
        assertVideos(videos);
    }

    @Test
    @RequiresAccountSession
    public void test_account_states_with_account() throws IOException {
        Call<BaseAccountStates> call = getAuthenticatedInstance().tvEpisodesService().accountStates(
                testTvShow.id,
                testTvSeason.season_number,
                testTvEpisode.episode_number
        );

        BaseAccountStates baseAccountStates = call.execute().body();

        assertBaseAccountStates(baseAccountStates);
    }

    @Test
    @RequiresAccountSession
    public void test_modify_rating_with_account() throws IOException {
        assumeTrue(accountDataInitialized);

        RatingObject obj = new RatingObject();
        obj.value = 10D;

        Call<Status> call = getAuthenticatedInstance().tvEpisodesService().addRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.ACCOUNT);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);

    }

    @Test
    @RequiresGuestSession
    public void test_modify_rating_as_guest() throws IOException {

        RatingObject obj = new RatingObject();
        obj.value = 5D;

        Call<Status> call = getAuthenticatedInstance().tvEpisodesService().addRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST, obj);
        Status status = call.execute().body();
        assertThat(status.status_code).isIn(1, 12);

        call = getAuthenticatedInstance().tvEpisodesService().deleteRating(testTvShow.id, testTvSeason.season_number, testTvEpisode.episode_number, AuthenticationType.GUEST);
        status = call.execute().body();
        assertThat(status.status_code).isEqualTo(13);

    }
}
