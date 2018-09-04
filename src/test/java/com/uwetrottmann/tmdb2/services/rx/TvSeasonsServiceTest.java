package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvSeasonChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvSeasonChangesStartDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvSeason;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvSeasonDataIntegrity;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.TvAssertions;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvSeasonExternalIds;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class TvSeasonsServiceTest extends BaseTestCase {

    @Test
    public void test_season() throws IOException {
        Observable<TvSeason> call = getUnauthenticatedInstance().rx.tvSeasonsService().season(
                testTvShow.id,
                testTvSeason.season_number
        );

        TvSeason tvSeason = call.singleOrError().blockingGet();

        assertTvSeason(tvSeason);
    }

    @Test
    public void test_season_with_append_to_response() throws IOException {
        Observable<TvSeason> call = getUnauthenticatedInstance().rx.tvSeasonsService().season(
                testTvShow.id,
                testTvSeason.season_number,
                new AppendToResponse(
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS
                )
        );

        TvSeason tvSeason = call.singleOrError().blockingGet();

        assertTvSeasonDataIntegrity(tvSeason);

        // credits
        assertCredits(tvSeason.credits);

        // images
        assertThat(tvSeason.images).isNotNull();
        assertImages(tvSeason.images.posters);

        // external ids
        TvAssertions.assertTvSeasonExternalIdsMatch(tvSeason.external_ids);
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> call = getUnauthenticatedInstance().rx.tvSeasonsService().credits(
                testTvShow.id,
                testTvSeason.season_number
        );

        Credits credits = call.singleOrError().blockingGet();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Observable<Changes> call = getUnauthenticatedInstance().rx.tvSeasonsService().changes(
                testTvSeason.id,
                new TmdbDate(testTvSeasonChangesStartDate),
                new TmdbDate(testTvSeasonChangesEndDate),
                null
        );

        Changes changes = call.singleOrError().blockingGet();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Observable<TvSeasonExternalIds> call = getUnauthenticatedInstance().rx.tvSeasonsService().externalIds(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );

        TvSeasonExternalIds ids = call.singleOrError().blockingGet();

        TvAssertions.assertTvSeasonExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> call = getUnauthenticatedInstance().rx.tvSeasonsService().images(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );

        Images images = call.singleOrError().blockingGet();
        assertThat(images.id).isNotNull();
        assertImages(images.posters);
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> call = getUnauthenticatedInstance().rx.tvSeasonsService().videos(
                testTvShow.id,
                testTvSeason.season_number,
                null
        );
        Videos videos = call.singleOrError().blockingGet();

        assertVideos(videos);
    }

}
