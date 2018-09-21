package com.uwetrottmann.tmdb2.services;

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
import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;
import retrofit2.Call;

public class TvServiceTest extends BaseTestCase {

    @Test
    public void test_tvShow() throws IOException {
        Call<TvShow> call = getUnauthenticatedInstance().tvService().tv(
                testTvShow.id,
                "en"
        );
        TvShow show = call.execute().body();

        assertTvShow(show);
    }

    @Test
    public void test_tvShow_with_append_to_response() throws IOException {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("start_date", new TmdbDate(testTvShowChangesStartDate).toString());
        opts.put("end_date", new TmdbDate(testTvShowChangesEndDate).toString());

        Call<TvShow> call = getUnauthenticatedInstance().tvService().tv(
                testTvShow.id,
                "en",
                new AppendToResponse(
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
//                        AppendToResponseItem.ALTERNATIVE_TITLES,
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

        TvShow show = call.execute().body();

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

        // seems currently broken for The Simpsons
        //Alternative Titles Assertions
//        assertAlternativeTitles(show.alternative_titles);

        //Content Ratings Assertions
        assertContentRatings(show.content_ratings);
    }

    @Test
    public void test_content_ratings() throws IOException {
        Call<ContentRatings> call = getUnauthenticatedInstance().tvService().content_ratings(
                testTvShow.id
        );

        ContentRatings ratings = call.execute().body();

        assertContentRatings(ratings);
    }

    @Test
    public void test_alternative_titles() throws IOException {
        Call<AlternativeTitles> call = getUnauthenticatedInstance().tvService().alternativeTitles(1399);

        AlternativeTitles titles = call.execute().body();

        assertAlternativeTitles(titles);
    }

    @Test
    public void test_credits() throws IOException {
        Call<Credits> call = getUnauthenticatedInstance().tvService().credits(
                testTvShow.id,
                null
        );

        Credits credits = call.execute().body();

        assertCredits(credits);
    }

    @Test
    public void test_changes() throws IOException {
        Call<Changes> call = getUnauthenticatedInstance().tvService().changes(
                testTvShow.id,
                new TmdbDate(testTvShowChangesStartDate),
                new TmdbDate(testTvShowChangesEndDate),
                null
        );

        Changes changes = call.execute().body();

        assertContentChanges(changes);
    }

    @Test
    public void test_externalIds() throws IOException {
        Call<TvExternalIds> call = getUnauthenticatedInstance().tvService().externalIds(
                testTvShow.id,
                null
        );

        TvExternalIds ids = call.execute().body();

        assertThat(ids.id).isEqualTo(testTvShow.id);
        TvAssertions.assertTvShowExternalIdsMatch(ids);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = getUnauthenticatedInstance().tvService().images(
                testTvShow.id,
                null
        );

        Images images = call.execute().body();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.backdrops);
        assertImages(images.posters);
    }

    @Test
    public void test_keywords() throws IOException {
        Call<Keywords> call = getUnauthenticatedInstance().tvService().keywords(
                testTvShow.id
        );

        Keywords keywords = call.execute().body();

        assertKeywords(keywords);
    }

    @Test
    public void test_similar() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().similar(
                testTvShow.id,
                1,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_recommendations() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().recommendations(
                testTvShow.id,
                1,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_videos() throws IOException {
        Call<Videos> call = getUnauthenticatedInstance().tvService().videos(
                testTvShow.id,
                null
        );

        Videos videos = call.execute().body();

        assertVideos(videos);
    }

    @Test
    public void test_translations() throws IOException {
        Call<Translations> call = getUnauthenticatedInstance().tvService().translations(
                testTvShow.id,
                null
        );

        Translations translations = call.execute().body();

        assertTranslations(translations);
    }

    @Test
    public void test_latest() throws IOException {
        Call<TvShow> call = getUnauthenticatedInstance().tvService().latest();
        TvShow show = call.execute().body();

        // Latest show might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(show).isNotNull();
        assertThat(show.id).isPositive();
        assertThat(show.name).isNotEmpty();
    }

    @Test
    public void test_onTheAir() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().onTheAir(
                null,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_airingToday() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().airingToday(
                null,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_topRated() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().topRated(
                null,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

    @Test
    public void test_popular() throws IOException {
        Call<TvShowResultsPage> call = getUnauthenticatedInstance().tvService().popular(
                null,
                null
        );

        TvShowResultsPage results = call.execute().body();

        assertTvShowResultsPage(results);
    }

}
