package com.uwetrottmann.tmdb.services;

import java.util.List;

import com.uwetrottmann.tmdb.BaseTestCase;
import com.uwetrottmann.tmdb.TestData;
import com.uwetrottmann.tmdb.entities.CastMember;
import com.uwetrottmann.tmdb.entities.Credits;
import com.uwetrottmann.tmdb.entities.CrewMember;
import com.uwetrottmann.tmdb.entities.ExternalIds;
import com.uwetrottmann.tmdb.entities.Images;
import com.uwetrottmann.tmdb.entities.Person;
import com.uwetrottmann.tmdb.entities.TvAlternativeTitles;
import com.uwetrottmann.tmdb.entities.TvKeywords;
import com.uwetrottmann.tmdb.entities.TvResultsPage;
import com.uwetrottmann.tmdb.entities.TvSeason;
import com.uwetrottmann.tmdb.entities.TvShowComplete;
import com.uwetrottmann.tmdb.entities.Videos;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TvServiceTest extends BaseTestCase {

    @Test
    public void test_tvshow() {
        TvShowComplete show = getManager().tvService().tv(TestData.TVSHOW_ID, null);
        assertTvShow(show);
    }
    
    @Test
    public void test_alternative_titles() {
        TvAlternativeTitles titles = getManager().tvService().alternativeTitles(TestData.TVSHOW_ID);
        assertThat(titles).isNotNull();
        assertThat(titles.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(titles.results).isNotEmpty();
        assertThat(titles.results.get(0).iso_3166_1).isNotNull();
        assertThat(titles.results.get(0).title).isNotNull();
    }
    
    @Test
    public void test_credits() {
        Credits credits = getManager().tvService().credits(TestData.TVSHOW_ID, null);
        assertThat(credits.id).isNotNull();
        assertCrewCredits(credits.crew);
        assertCastCredits(credits.cast);
    }
    
    @Test
    public void test_externalIds() {
        ExternalIds ids = getManager().tvService().externalIds(TestData.TVSHOW_ID, null);
        assertThat(ids.id).isNotNull();
        assertThat(ids.freebase_id).isNotNull();
        assertThat(ids.freebase_mid).isNotNull();
        assertThat(ids.tvdb_id).isNotNull();
        assertThat(ids.imdb_id).isNotNull();
        assertThat(ids.tvrage_id).isNotNull();
    }
    
    @Test
    public void test_images() {
        Images images = getManager().tvService().images(TestData.TVSHOW_ID, null);
        assertThat(images).isNotNull();
        assertThat(images.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(images.backdrops).isNotEmpty();
        assertThat(images.backdrops.get(0).file_path).isNotNull();
        assertThat(images.backdrops.get(0).width).isNotNull();
        assertThat(images.backdrops.get(0).height).isNotNull();
        assertThat(images.backdrops.get(0).aspect_ratio).isGreaterThan(1.7f);
        assertThat(images.backdrops.get(0).vote_average).isPositive();
        assertThat(images.backdrops.get(0).vote_count).isPositive();
        assertThat(images.posters).isNotEmpty();
        assertThat(images.posters.get(0).file_path).isNotNull();
        assertThat(images.posters.get(0).width).isEqualTo(1000);
        assertThat(images.posters.get(0).height).isEqualTo(1500);
        assertThat(images.posters.get(0).iso_639_1).isEqualTo("en");
        assertThat(images.posters.get(0).aspect_ratio).isGreaterThan(0.6f);
        assertThat(images.posters.get(0).vote_average).isPositive();
        assertThat(images.posters.get(0).vote_count).isPositive();
    }
    
    @Test
    public void test_keywords() {
        TvKeywords keywords = getManager().tvService().keywords(TestData.TVSHOW_ID);
        assertThat(keywords).isNotNull();
        assertThat(keywords.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(keywords.results.get(0).id).isNotNull();
        assertThat(keywords.results.get(0).name).isNotNull();
    }
    
    @Test
    public void test_similar() {
        TvResultsPage results = getManager().tvService().similar(TestData.TVSHOW_ID, 1, null);
        assertThat(results).isNotNull();
        assertThat(results.page).isNotNull().isPositive();
        assertThat(results.total_pages).isNotNull().isPositive();
        assertThat(results.total_results).isNotNull().isPositive();
        assertThat(results.results).isNotEmpty();
        assertThat(results.results.get(0).backdrop_path).isNotNull();
        assertThat(results.results.get(0).id).isNotNull().isPositive();
        assertThat(results.results.get(0).original_name).isNotNull();
        assertThat(results.results.get(0).first_air_date).isNotNull();
        assertThat(results.results.get(0).poster_path).isNotNull();
        assertThat(results.results.get(0).popularity).isNotNull().isPositive();
        assertThat(results.results.get(0).name).isNotNull();
        assertThat(results.results.get(0).vote_average).isNotNull().isPositive();
        assertThat(results.results.get(0).vote_count).isNotNull().isPositive();
    }
    
    @Test
    public void test_videos() {
        Videos videos = getManager().tvService().videos(TestData.TVSHOW_ID, null);
        assertThat(videos).isNotNull();
        assertThat(videos.id).isEqualTo(TestData.TVSHOW_ID);
        assertThat(videos.results.get(0).id).isNotNull();
        assertThat(videos.results.get(0).iso_639_1).isNotNull();
        assertThat(videos.results.get(0).key).isNotNull();
        assertThat(videos.results.get(0).name).isNotNull();
        assertThat(videos.results.get(0).site).isEqualTo("YouTube");
        assertThat(videos.results.get(0).size).isNotNull();
        assertThat(videos.results.get(0).type).isEqualTo("Opening Credits");
    }
    
    @Test
    public void test_latest() {
        TvShowComplete show = getManager().tvService().latest();
        // Latest show might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(show).isNotNull();
        assertThat(show.id).isPositive();
        assertThat(show.name).isNotEmpty();
    }
    
    @Test
    public void test_onTheAir() {
        TvResultsPage results = getManager().tvService().onTheAir(null, null);
        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
    }
    
    @Test
    public void test_airingToday() {
        TvResultsPage results = getManager().tvService().airingToday(null, null);
        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
    }
    
    @Test
    public void test_topRated() {
        TvResultsPage results = getManager().tvService().topRated(null, null);
        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
    }
    
    @Test
    public void test_popular() {
        TvResultsPage results = getManager().tvService().popular(null, null);
        assertThat(results).isNotNull();
        assertThat(results.results).isNotEmpty();
    }
    
    private void assertTvShow(TvShowComplete show) {
        assertThat(show.first_air_date).isNotNull();
        assertThat(show.homepage).isNotNull();
        assertThat(show.id).isNotNull();
        assertThat(show.in_production).isNotNull();
        assertThat(show.languages).isNotEmpty();
        assertThat(show.last_air_date).isNotNull();
        assertThat(show.name).isNotNull();
        assertThat(show.number_of_seasons).isNotNull().isPositive();
        assertThat(show.original_language).isNotNull();
        assertThat(show.original_name).isNotNull();
        assertThat(show.overview).isNotNull();
        assertThat(show.popularity).isNotNull().isGreaterThanOrEqualTo(0);
        assertThat(show.poster_path).isNotNull();
        assertThat(show.status).isNotNull();
        assertThat(show.type).isNotNull();
        assertThat(show.vote_average).isNotNull().isGreaterThanOrEqualTo(0);
        assertThat(show.vote_count).isNotNull().isGreaterThanOrEqualTo(0);

        assertThat(show.created_by).isNotEmpty();
        for(Person person : show.created_by) {
            assertThat(person.id).isNotNull();
            assertThat(person.name).isNotNull();
            assertThat(person.profile_path).isNotNull();
        }
        
        assertThat(show.seasons).isNotEmpty();
        for(TvSeason company : show.seasons) {
            assertThat(company.id).isNotNull();
            assertThat(company.air_date).isNotNull();
            assertThat(company.episode_count).isNotNull();
            assertThat(company.season_number).isNotNull();
        }
        
    }

    private void assertCrewCredits(List<CrewMember> crew) {
        assertThat(crew).isNotEmpty();
        
        for (CrewMember member : crew) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.department).isNotNull();
            assertThat(member.job).isNotNull();
        }
    }
    
    private void assertCastCredits(List<CastMember> cast) {
        assertThat(cast).isNotEmpty();
        
        for (CastMember member : cast) {
            assertThat(member.id).isNotNull();
            assertThat(member.credit_id).isNotNull();
            assertThat(member.name).isNotNull();
            assertThat(member.character).isNotNull();
            assertThat(member.order).isNotNull();
        }
    }

}
