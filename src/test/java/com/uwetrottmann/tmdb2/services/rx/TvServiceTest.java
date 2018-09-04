package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesEndDate;
import static com.uwetrottmann.tmdb2.TestData.testTvShowChangesStartDate;
import static com.uwetrottmann.tmdb2.assertions.ChangeAssertions.assertContentChanges;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertAlternativeTitles;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertContentRatings;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertTranslations;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertVideos;
import static com.uwetrottmann.tmdb2.assertions.KeywordAssertions.assertKeywords;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShow;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowDataIntegrity;
import static com.uwetrottmann.tmdb2.assertions.TvAssertions.assertTvShowResultsPage;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.assertions.TvAssertions;
import com.uwetrottmann.tmdb2.entities.AlternativeTitles;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Changes;
import com.uwetrottmann.tmdb2.entities.ContentRatings;
import com.uwetrottmann.tmdb2.entities.Credits;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.entities.Keywords;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import com.uwetrottmann.tmdb2.entities.Translations;
import com.uwetrottmann.tmdb2.entities.TvExternalIds;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Ignore;
import org.junit.Test;

public class TvServiceTest extends BaseTestCase {

    @Test
    public void test_tvShow() throws IOException {
        Observable<TvShow> call = getUnauthenticatedInstance().rx.tvService().tv(
                testTvShow.id
        );
        TvShow show = call.singleOrError().blockingGet();

        assertTvShow(show);
    }

    @Test
    public void test_tvShow_with_append_to_response() throws IOException {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", new TmdbDate(testTvShowChangesStartDate).toString());
        opts.put("end_date", new TmdbDate(testTvShowChangesEndDate).toString());

        Observable<TvShow> call = getUnauthenticatedInstance().rx.tvService().tv(
                testTvShow.id,
                new AppendToResponse(
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.CONTENT_RATINGS,
                        AppendToResponseItem.RECOMMENDATIONS,
                        AppendToResponseItem.CHANGES,
                        AppendToResponseItem.TRANSLATIONS,
                        AppendToResponseItem.KEYWORDS
                ),
                opts
        );

        TvShow show = call.singleOrError().blockingGet();

        //Tv Show Assertions & Data Verifications
        assertTvShowDataIntegrity(show);

        //Credits Assertions
        assertCredits(show.credits);

        //Images Assertions
        assertThat(show.images).isNotNull();
        assertImages(show.images.backdrops);
        assertImages(show.images.posters);

        //External Ids Assertions & Data Verifications
        TvAssertions.assertTvShowExternalIdsMatch(show.external_ids);

        //Similar Movies Assertions
        assertTvShowResultsPage(show.similar);

        //Recommended Tv Shows Assertions
        assertTvShowResultsPage(show.recommendations);

        //Translations Assertions
        assertTranslations(show.translations);

        //Changes Assertions
        assertContentChanges(show.changes);

        //Keywords Assertions
        assertKeywords(show.keywords);

        //Videos Assertions
        assertVideos(show.videos);

        //Alternative Titles Assertions
        //assertAlternativeTitles(show.alternative_titles);

        //Content Ratings Assertions
        assertContentRatings(show.content_ratings);
    }

    @Test
    public void test_content_ratings() throws IOException {
        Observable<ContentRatings> call = getUnauthenticatedInstance().rx.tvService().content_ratings(
                testTvShow.id
        );

        ContentRatings ratings = call.singleOrError().blockingGet();

        assertContentRatings(ratings);
    }

    @Test
    @Ignore
    public void test_alternative_titles() throws IOException {
        Observable<AlternativeTitles> call = getUnauthenticatedInstance().rx.tvService().alternativeTitles(
                testTvShow.id
        );

        AlternativeTitles titles = call.singleOrError().blockingGet();

        assertAlternativeTitles(titles);
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> call = getUnauthenticatedInstance().rx.tvService().credits(
                testTvShow.id,
                null
        );

        Credits credits = call.singleOrError().blockingGet();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Observable<Changes> call = getUnauthenticatedInstance().rx.tvService().changes(
                testTvShow.id,
                new TmdbDate(testTvShowChangesStartDate),
                new TmdbDate(testTvShowChangesEndDate),
                null
        );

        Changes changes = call.singleOrError().blockingGet();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Observable<TvExternalIds> call = getUnauthenticatedInstance().rx.tvService().externalIds(
                testTvShow.id,
                null
        );

        TvExternalIds ids = call.singleOrError().blockingGet();

        assertThat(ids.id).isEqualTo(testTvShow.id);
        TvAssertions.assertTvShowExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> call = getUnauthenticatedInstance().rx.tvService().images(
                testTvShow.id,
                null
        );

        Images images = call.singleOrError().blockingGet();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.backdrops);
        assertImages(images.posters);
    }

    @Test
    public void test_keywords() throws IOException {
        Observable<Keywords> call = getUnauthenticatedInstance().rx.tvService().keywords(
                testTvShow.id
        );

        Keywords keywords = call.singleOrError().blockingGet();

        assertKeywords(keywords);
    }

    @Test
    public void test_similar() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().similar(
                testTvShow.id,
                1,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_recommendations() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().recommendations(
                testTvShow.id,
                1,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> call = getUnauthenticatedInstance().rx.tvService().videos(
                testTvShow.id,
                null
        );

        Videos videos = call.singleOrError().blockingGet();

        assertVideos(videos);
    }

    @Test
    public void test_translations() throws IOException {
        Observable<Translations> call = getUnauthenticatedInstance().rx.tvService().translations(
                testTvShow.id,
                null
        );

        Translations translations = call.singleOrError().blockingGet();

        assertTranslations(translations);
    }

    @Test
    public void test_latest() throws IOException {
        Observable<TvShow> call = getUnauthenticatedInstance().rx.tvService().latest();
        TvShow show = call.singleOrError().blockingGet();

        // Latest show might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(show).isNotNull();
        assertThat(show.id).isPositive();
        assertThat(show.name).isNotEmpty();
    }

    @Test
    public void test_onTheAir() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().onTheAir(
                null,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_airingToday() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().airingToday(
                null,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_topRated() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().topRated(
                null,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_popular() throws IOException {
        Observable<TvShowResultsPage> call = getUnauthenticatedInstance().rx.tvService().popular(
                null,
                null
        );

        TvShowResultsPage results = call.singleOrError().blockingGet();

        assertTvShowResultsPage(results);
    }

}
