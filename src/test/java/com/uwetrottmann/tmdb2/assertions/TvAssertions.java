package com.uwetrottmann.tmdb2.assertions;

import static com.uwetrottmann.tmdb2.TestData.testTvEpisode;
import static com.uwetrottmann.tmdb2.TestData.testTvSeason;
import static com.uwetrottmann.tmdb2.TestData.testTvShow;
import static com.uwetrottmann.tmdb2.assertions.CompanyAssertions.assertBaseCompany;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCastCredits;
import static com.uwetrottmann.tmdb2.assertions.CreditAssertions.assertCrewCredits;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseExternalIds;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertBaseResultsPage;
import static com.uwetrottmann.tmdb2.assertions.GenreAssertions.assertGenre;
import static com.uwetrottmann.tmdb2.assertions.NetworkAssertions.assertNetwork;
import static com.uwetrottmann.tmdb2.assertions.PersonAssertions.assertBasePerson;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.entities.BaseCompany;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvEpisode;
import com.uwetrottmann.tmdb2.entities.BaseTvSeason;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.Network;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvEpisodeResultsPage;
import com.uwetrottmann.tmdb2.entities.TvExternalIds;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvSeasonExternalIds;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;

public class TvAssertions {


    public static void assertBaseTvShow(BaseTvShow tvShow) {
        assertThat(tvShow).isNotNull();
        assertThat(tvShow.id).isNotNull();
        assertThat(tvShow.name).isNotNull();
        assertThat(tvShow.origin_country).isNotNull();
        assertThat(tvShow.original_language).isNotNull();
        assertThat(tvShow.original_name).isNotNull();
        assertThat(tvShow.vote_count).isNotNull();
        assertThat(tvShow.vote_count).isGreaterThanOrEqualTo(0);
        assertThat(tvShow.vote_average).isNotNull();
        assertThat(tvShow.vote_average).isGreaterThanOrEqualTo(0);
        if (tvShow.popularity != null) {
            assertThat(tvShow.popularity).isGreaterThanOrEqualTo(0);
        }

        try {
            TvShow _tvShow = (TvShow) tvShow;
            for (Genre genre : _tvShow.genres) {
                assertGenre(genre);
            }
        } catch (Exception exc) {
            assertThat(tvShow.genre_ids).isNotNull();
        }
    }

    public static void assertTvShow(TvShow tvShow) {
        assertBaseTvShow(tvShow);

        assertThat(tvShow.homepage).isNotNull();
        assertThat(tvShow.in_production).isNotNull();
        assertThat(tvShow.number_of_episodes).isNotNull();
        assertThat(tvShow.number_of_episodes).isGreaterThanOrEqualTo(0);
        assertThat(tvShow.number_of_seasons).isNotNull();
        assertThat(tvShow.number_of_seasons).isGreaterThanOrEqualTo(0);

        assertThat(tvShow.created_by).isNotNull();
        assertThat(tvShow.created_by).isNotEmpty();
        for (BasePerson person : tvShow.created_by) {
            assertBasePerson(person, false);
        }

        assertThat(tvShow.type).isNotNull();
        assertThat(tvShow.status).isNotNull();


        assertThat(tvShow.networks).isNotNull();
        assertThat(tvShow.networks).isNotEmpty();
        for (Network network : tvShow.networks) {
            assertNetwork(network);
        }

        assertThat(tvShow.episode_run_time).isNotNull();
        assertThat(tvShow.episode_run_time).isNotEmpty();
        for (Integer runTime : tvShow.episode_run_time) {
            assertThat(runTime).isNotNull();
            assertThat(runTime).isGreaterThanOrEqualTo(0);
        }

        for (String language : tvShow.languages) {
            assertThat(language).isNotNull();
        }

        for (BaseCompany company : tvShow.production_companies) {
            assertBaseCompany(company, false);
        }

        assertThat(tvShow.seasons).isNotNull();
        assertThat(tvShow.seasons).isNotEmpty();
        assertThat(tvShow.seasons.size()).isGreaterThanOrEqualTo(tvShow.number_of_seasons);
        for (BaseTvSeason tvSeason : tvShow.seasons) {
            assertBaseTvSeason(tvSeason);
            // seasons include episode count ONLY when getting show summary
            assertThat(tvSeason.episode_count).isGreaterThan(0);
        }

    }

    public static void assertTvShowDataIntegrity(TvShow tvShow) {
        assertTvShow(tvShow);
        assertThat(tvShow.name).isEqualTo(testTvShow.name);
        assertThat(tvShow.first_air_date).isEqualTo(testTvShow.first_air_date);
        assertThat(tvShow.id).isEqualTo(testTvShow.id);
        assertThat(tvShow.homepage).isEqualTo(testTvShow.homepage);
        assertThat(tvShow.original_language).isEqualTo(testTvShow.original_language);
        assertThat(tvShow.original_name).isEqualTo(testTvShow.original_name);
        assertThat(tvShow.overview).isEqualTo(testTvShow.overview);
        assertThat(tvShow.type).isEqualTo(testTvShow.type);
    }

    public static void assertTvShowResultsPage(TvShowResultsPage tvShowResultsPage) {
        assertBaseResultsPage(tvShowResultsPage);
        for (BaseTvShow baseTvShow : tvShowResultsPage.results) {
            assertBaseTvShow(baseTvShow);
        }
    }


    public static void assertBaseTvSeason(BaseTvSeason tvSeason) {
        assertThat(tvSeason).isNotNull();
        assertThat(tvSeason.air_date).isNotNull();
        assertThat(tvSeason.id).isNotNull();
        assertThat(tvSeason.season_number).isNotNull();
        assertThat(tvSeason.season_number).isGreaterThanOrEqualTo(0);
        assertThat(tvSeason.poster_path).isNotNull();
    }

    public static void assertTvSeason(TvSeason tvSeason) {
        assertBaseTvSeason(tvSeason);
        assertThat(tvSeason._id).isNotNull();
        assertThat(tvSeason.name).isNotNull();
        assertThat(tvSeason.overview).isNotNull();

        assertThat(tvSeason.episodes).isNotNull();
        assertThat(tvSeason.episodes).isNotEmpty();
        for (TvEpisode tvEpisode : tvSeason.episodes) {
            assertTvEpisode(tvEpisode);
        }
    }

    public static void assertTvSeasonDataIntegrity(TvSeason tvSeason) {
        assertTvSeason(tvSeason);
        assertThat(tvSeason.overview).isEqualTo(testTvSeason.overview);
        assertThat(tvSeason.name).isEqualTo(testTvSeason.name);
        assertThat(tvSeason.air_date).isEqualTo(testTvSeason.air_date);
        assertThat(tvSeason.season_number).isEqualTo(testTvSeason.season_number);
        assertThat(tvSeason.id).isEqualTo(testTvSeason.id);
        assertThat(tvSeason._id).isEqualTo(testTvSeason._id);
        assertThat(tvSeason.episodes.size()).isEqualTo(testTvSeason.episodes.size());
        assertThat(tvSeason.poster_path).isEqualTo(testTvSeason.poster_path);

    }

    public static void assertBaseTvEpisode(BaseTvEpisode tvEpisode) {
        assertThat(tvEpisode).isNotNull();
        assertThat(tvEpisode.id).isNotNull();
        assertThat(tvEpisode.episode_number).isNotNull();
        assertThat(tvEpisode.episode_number).isGreaterThanOrEqualTo(0);
        assertThat(tvEpisode.name).isNotNull();
        assertThat(tvEpisode.season_number).isNotNull();
        assertThat(tvEpisode.season_number).isGreaterThanOrEqualTo(0);
        assertThat(tvEpisode.vote_average).isNotNull();
        assertThat(tvEpisode.vote_count).isNotNull();
    }

    public static void assertTvEpisode(TvEpisode tvEpisode) {
        assertBaseTvEpisode(tvEpisode);
        assertThat(tvEpisode.overview).isNotNull();
        assertCrewCredits(tvEpisode.crew);

        if (tvEpisode.guest_stars == null)
            return;

        assertCastCredits(tvEpisode.guest_stars);

    }

    public static void assertTvEpisodeDataIntegrity(TvEpisode tvEpisode) {
        assertTvEpisode(tvEpisode);
        assertThat(tvEpisode.name).isEqualTo(testTvEpisode.name);
        assertThat(tvEpisode.id).isEqualTo(testTvEpisode.id);
        assertThat(tvEpisode.season_number).isEqualTo(testTvEpisode.season_number);
        assertThat(tvEpisode.episode_number).isEqualTo(testTvEpisode.episode_number);
        assertThat(tvEpisode.air_date).isEqualTo(testTvEpisode.air_date);
    }

    public static void assertTvEpisodeResultsPage(TvEpisodeResultsPage tvEpisodeResultsPage) {
        assertBaseResultsPage(tvEpisodeResultsPage);
        for (BaseTvEpisode tvEpisode : tvEpisodeResultsPage.results) {
            assertBaseTvEpisode(tvEpisode);
        }
    }

    public static void assertTvExternalIds(TvExternalIds externalIds) {
        assertBaseExternalIds(externalIds);
        assertThat(externalIds.tvdb_id).isNotNull();
        assertThat(externalIds.imdb_id).isNotNull();
    }

    public static void assertTvSeasonExternalIds(TvSeasonExternalIds externalIds) {
        assertBaseExternalIds(externalIds);
        assertThat(externalIds.tvdb_id).isNotNull();
    }

    public static void assertTvSeasonExternalIdsDataIntegrity(TvSeasonExternalIds externalIds) {
        assertTvSeasonExternalIds(externalIds);
        assertThat(externalIds.tvrage_id).isEqualTo(testTvSeason.external_ids.tvrage_id);
        assertThat(externalIds.tvdb_id).isEqualTo(testTvSeason.external_ids.tvdb_id);
        assertThat(externalIds.freebase_id).isEqualTo(testTvSeason.external_ids.freebase_id);
        assertThat(externalIds.freebase_mid).isEqualTo(testTvSeason.external_ids.freebase_mid);
    }

    public static void assertTvExternalIdsDataIntegrity(TvExternalIds externalIds) {
        assertTvExternalIds(externalIds);
        assertThat(externalIds.imdb_id).isEqualTo(testTvShow.external_ids.imdb_id);
        assertThat(externalIds.tvrage_id).isEqualTo(testTvShow.external_ids.tvrage_id);
        assertThat(externalIds.tvdb_id).isEqualTo(testTvShow.external_ids.tvdb_id);
        assertThat(externalIds.freebase_id).isEqualTo(testTvShow.external_ids.freebase_id);
        assertThat(externalIds.freebase_mid).isEqualTo(testTvShow.external_ids.freebase_mid);
    }

    public static void assertTvEpisodeExternalIdsDataIntegrity(TvExternalIds externalIds) {
        assertBaseExternalIds(externalIds);
        assertThat(externalIds.tvrage_id).isEqualTo(testTvEpisode.external_ids.tvrage_id);
        assertThat(externalIds.tvdb_id).isEqualTo(testTvEpisode.external_ids.tvdb_id);
        assertThat(externalIds.freebase_id).isEqualTo(testTvEpisode.external_ids.freebase_id);
        assertThat(externalIds.freebase_mid).isEqualTo(testTvEpisode.external_ids.freebase_mid);
    }

}
