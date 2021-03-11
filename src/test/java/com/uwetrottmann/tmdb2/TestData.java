package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.BaseCompany;
import com.uwetrottmann.tmdb2.entities.BaseKeyword;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.Credit;
import com.uwetrottmann.tmdb2.entities.CreditMedia;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.Network;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.Review;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvEpisodeExternalIds;
import com.uwetrottmann.tmdb2.entities.TvExternalIds;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvSeasonExternalIds;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.enumerations.CreditType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestData {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    public static final Person testPerson = new Person();
    public static Date testPersonChangesStartDate;
    public static Date testPersonChangesEndDate;

    public static final Person testPersonCast = new Person();
    public static final Person testPersonCrew = new Person();

    public static final Movie testMovie = new Movie();
    public static Date testMovieChangesStartDate;
    public static Date testMovieChangesEndDate;

    public static final Credit testCredit = new Credit();

    public static final Collection testCollection = new Collection();

    public static final Genre testMovieGenreAdventure = new Genre();
    public static final Genre testMovieGenreRomance = new Genre();
    public static final Genre testTvGenreDrama = new Genre();
    public static final Genre testTvGenreSciFi = new Genre();

    public static final Network testNetwork = new Network();

    public static final Review testReview = new Review();

    public static final Company testMovieCompany = new Company();
    public static final BaseCompany testProductionCompany = testMovieCompany;
    public static final Company testCompany = new Company();


    public static final TvShow testTvShow = new TvShow();
    public static Date testTvShowChangesStartDate;
    public static Date testTvShowChangesEndDate;

    public static final TvSeason testTvSeason = new TvSeason();
    public static Date testTvSeasonChangesStartDate;
    public static Date testTvSeasonChangesEndDate;

    public static final TvEpisode testTvEpisode = new TvEpisode();
    public static Date testTvEpisodeChangesStartDate;
    public static Date testTvEpisodeChangesEndDate;

    public static final BaseKeyword testKeyword = new BaseKeyword();

    public static final List testList = new List();
    public static final Movie testListMovie = new Movie();

    public static final java.util.List<Integer> testListMovies = new ArrayList<>();

    static {
        try {
            initializeTestingIntegrityData();
        } catch (ParseException e) {
            throw new RuntimeException("TestData setup failed.", e);
        }
    }

    private static void initializeTestingIntegrityData() throws ParseException {
        testCompany.id = 5;
        testCompany.name = "Columbia Pictures";
        testCompany.origin_country = "US";

        testPerson.name = "Ben Affleck";
        testPerson.birthday = JSON_STRING_DATE.parse("1972-08-15");
        testPerson.gender = 2;
        testPerson.id = 880;
        testPerson.imdb_id = "nm0000255";
        testPerson.place_of_birth = "Berkeley, California, USA";
        testPerson.adult = false;

        testPersonChangesStartDate = JSON_STRING_DATE.parse("2016-12-16");
        testPersonChangesEndDate = JSON_STRING_DATE.parse("2016-12-28");

        testPersonCast.id = 287;
        testPersonCast.name = "Brad Pitt";

        testPersonCrew.id = 7467;
        testPersonCrew.name = "David Fincher";

        testMovieGenreAdventure.name = "Adventure";
        testMovieGenreAdventure.id = 12;

        testMovieGenreRomance.name = "Romance";
        testMovieGenreRomance.id = 10749;

        testTvGenreDrama.name = "Drama";
        testTvGenreDrama.id = 18;

        testTvGenreSciFi.name = "Sci-Fi & Fantasy";
        testTvGenreSciFi.id = 10765;

        testNetwork.id = 49;
        testNetwork.name = "HBO";

        testMovieCompany.name = "Marvel Studios";
        testMovieCompany.id = 420;

        testMovie.id = 24428;
        testMovie.title = "The Avengers";
        testMovie.original_title = "The Avengers";
        testMovie.status = Status.RELEASED;
        testMovie.runtime = 143;
        testMovie.revenue = 1519557910L;
        testMovie.release_date = JSON_STRING_DATE.parse("2012-04-25");
        testMovie.original_language = "en";
        testMovie.imdb_id = "tt0848228";
        testMovie.adult = false;

        testMovieChangesStartDate = JSON_STRING_DATE.parse("2017-3-24");
        testMovieChangesEndDate = JSON_STRING_DATE.parse("2017-4-2");

        testCollection.id = 86311;
        testCollection.name = "The Avengers Collection";

        testTvShow.name = "The Simpsons";
        testTvShow.first_air_date = JSON_STRING_DATE.parse("1989-12-17");
        testTvShow.id = 456;
        testTvShow.original_language = "en";
        testTvShow.original_name = "The Simpsons";
        testTvShow.type = "Scripted";
        testTvShow.external_ids = new TvExternalIds();
        testTvShow.external_ids.imdb_id = "tt0096697";
        testTvShow.external_ids.tvdb_id = 71663;
        testTvShow.external_ids.facebook_id = "TheSimpsons";
        testTvShow.external_ids.freebase_mid = "/m/07c72";
        testTvShow.external_ids.freebase_id = "/en/the_simpsons";
        testTvShow.external_ids.instagram_id= "thesimpsonsfox";
        testTvShow.external_ids.tvrage_id = 6190;
        testTvShow.external_ids.twitter_id = "thesimpsons";
        testTvShow.external_ids.id = testTvShow.id;
        testTvShowChangesStartDate = JSON_STRING_DATE.parse("2017-2-2");
        testTvShowChangesEndDate = JSON_STRING_DATE.parse("2017-2-4");

        testTvSeason._id = "5256c24719c2956ff600565b";
        testTvSeason.id = 3582;
        testTvSeason.season_number = 1;
        testTvSeason.name = "Season 1";
        testTvSeason.air_date = JSON_STRING_DATE.parse("1989-12-17");
        testTvSeason.episodes = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            testTvSeason.episodes.add(new TvEpisode());
        }
        testTvSeason.external_ids = new TvSeasonExternalIds();
        testTvSeason.external_ids.tvdb_id = 2727;
        testTvSeason.external_ids.tvrage_id = 0;
        testTvSeason.external_ids.freebase_id = "/en/the_simpsons_season_1";
        testTvSeason.external_ids.freebase_mid = "/m/0cmqz_";
        testTvSeasonChangesStartDate = JSON_STRING_DATE.parse("2015-10-10");
        testTvSeasonChangesEndDate = JSON_STRING_DATE.parse("2015-10-24");

        testTvEpisode.name = "Simpsons Roasting on an Open Fire";
        testTvEpisode.id = 62228;
        testTvEpisode.season_number = 1;
        testTvEpisode.episode_number = 1;
        testTvEpisode.air_date = JSON_STRING_DATE.parse("1989-12-17");
        testTvEpisode.external_ids = new TvEpisodeExternalIds();
        testTvEpisode.external_ids.imdb_id = "tt0348034";
        testTvEpisode.external_ids.freebase_mid = "/m/0129zr";
        testTvEpisode.external_ids.freebase_id = "/en/simpsons_roasting_on_an_open_fire";
        testTvEpisode.external_ids.tvdb_id = 55452;
        testTvEpisode.external_ids.tvrage_id = 206468;
        testTvEpisodeChangesStartDate = JSON_STRING_DATE.parse("2017-2-2");
        testTvEpisodeChangesEndDate = JSON_STRING_DATE.parse("2017-2-4");

        testCredit.credit_type = CreditType.CAST;
        testCredit.department = "Acting";
        testCredit.job = "Actor";
        testCredit.media_type = MediaType.TV;
        testCredit.id = "5256bdc319c2956ff6001554";
        testCredit.media = new CreditMedia();
        testCredit.media.id = 456;
        testCredit.media.name = "The Simpsons";
        testCredit.media.original_name = "The Simpsons";
        testCredit.media.character = "Bart Simpson";

        testReview.id = "581bbdbbc3a36805c60001f1";
        testReview.iso_639_1 = "en";
        testReview.media_id = 284052;
        testReview.media_title = "Doctor Strange";
        testReview.url = "https://www.themoviedb.org/review/581bbdbbc3a36805c60001f1";

        testKeyword.id = 1721;
        testKeyword.name = "fight";

        testList.id = 9883;
        testList.created_by = "banana_girl";
        testList.name = "YA adaptations";

        testListMovie.id = 9880;

        testListMovies.add(testMovie.id);
        testListMovies.add(550);
        testListMovies.add(99861);
        testListMovies.add(293660);
    }

}
