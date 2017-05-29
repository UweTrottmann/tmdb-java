package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.BaseCompany;
import com.uwetrottmann.tmdb2.entities.BaseKeyword;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.Credit;
import com.uwetrottmann.tmdb2.entities.CreditMedia;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.List;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.Network;
import com.uwetrottmann.tmdb2.entities.Person;
import com.uwetrottmann.tmdb2.entities.PersonExternalIds;
import com.uwetrottmann.tmdb2.entities.Review;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
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

    public static Person testPerson = new Person();
    public static BasePerson testBasePerson = (BasePerson) testPerson;
    public static Date testPersonChangesStartDate;
    public static Date testPersonChangesEndDate;

    public static Person testPersonCast = new Person();
    public static Person testPersonCrew = new Person();

    public static Movie testMovie = new Movie();
    public static BaseMovie testBaseMovie = (BaseMovie) testMovie;
    public static Date testMovieChangesStartDate;
    public static Date testMovieChangesEndDate;

    public static Credit testCredit = new Credit();

    public static Collection testCollection = new Collection();

    public static Genre testMovieGenreAdventure = new Genre();
    public static Genre testMovieGenreRomance = new Genre();
    public static Genre testTvGenreDrama = new Genre();
    public static Genre testTvGenreSciFi = new Genre();

    public static Network testNetwork = new Network();

    public static Review testReview = new Review();

    public static Company testMovieCompany = new Company();
    public static BaseCompany testProductionCompany = (BaseCompany) testMovieCompany;
    public static Company testCompany = new Company();


    public static TvShow testTvShow = new TvShow();
    public static Date testTvShowChangesStartDate;
    public static Date testTvShowChangesEndDate;

    public static TvSeason testTvSeason = new TvSeason();
    public static Date testTvSeasonChangesStartDate;
    public static Date testTvSeasonChangesEndDate;

    public static TvEpisode testTvEpisode = new TvEpisode();
    public static Date testTvEpisodeChangesStartDate;
    public static Date testTvEpisodeChangesEndDate;

    public static BaseKeyword testKeyword = new BaseKeyword();

    public static List testList = new List();

    public static java.util.List<Integer> testListMovies = new java.util.ArrayList<>();

    public static void initializeTestingIntegrityData() throws ParseException {


        testCompany.id = 5;
        testCompany.name = "Columbia Pictures";
        testCompany.description = "Columbia Pictures Industries, Inc. (CPII) is an American film production and distribution company. Columbia Pictures now forms part of the Columbia TriStar Motion Picture Group, owned by Sony Pictures Entertainment, a subsidiary of the Japanese conglomerate Sony. It is one of the leading film companies in the world, a member of the so-called Big Six. It was one of the so-called Little Three among the eight major film studios of Hollywood's Golden Age.";
        testCompany.headquarters = "Culver City, California";
        testCompany.logo_path = "/mjUSfXXUhMiLAA1Zq1TfStNSoLR.png";
        testCompany.homepage = "http://www.sonypictures.com/";

        testPerson.name = "Ben Affleck";
        testPerson.birthday = JSON_STRING_DATE.parse("1972-08-15");
        testPerson.gender = 2;
        testPerson.id = 880;
        testPerson.imdb_id = "nm0000255";
        testPerson.place_of_birth = "Berkeley, Californie, États-Unis";
        testPerson.adult = false;
        testPerson.external_ids = new PersonExternalIds();
        testPerson.external_ids.twitter_id = "BenAffleck";
        testPerson.external_ids.freebase_mid = "/m/0151w_";
        testPerson.external_ids.instagram_id = "benaffleck";
        testPerson.external_ids.freebase_id = "/en/ben_affleck";
        testPerson.external_ids.imdb_id = "nm0000255";
        testPerson.external_ids.facebook_id = "benaffleck";
        testPerson.external_ids.tvrage_id = 5741;

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

        testMovieCompany.name = "Paramount Pictures";
        testMovieCompany.id = 4;
        testMovieCompany.headquarters = "Burbank, California, United States";
        testMovieCompany.homepage = "http://www.paramount.com/";

        testMovie.id = 24428;
        testMovie.title = "The Avengers";
        testMovie.original_title = "The Avengers";
        testMovie.status = Status.RELEASED;
        testMovie.tagline = "Some assembly required.";
        testMovie.runtime = 143;
        testMovie.revenue = 1519557910;
        testMovie.release_date = JSON_STRING_DATE.parse("2012-04-25");
        testMovie.original_language = "en";
        testMovie.budget = 220000000;
        testMovie.imdb_id = "tt0848228";
        testMovie.overview = "When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!";
        testMovie.homepage = "http://marvel.com/avengers_movie/";
        testMovie.adult = false;

        testMovieChangesStartDate = JSON_STRING_DATE.parse("2017-3-24");
        testMovieChangesEndDate = JSON_STRING_DATE.parse("2017-4-2");

        testCollection.id = 86311;
        testCollection.name = "The Avengers Collection";
        testCollection.overview = "A superhero film series produced by Marvel Studios based on the Marvel Comics superhero team of the same name, and part of the Marvel Cinematic Universe (MCU).  The series features an ensemble cast from the Marvel Cinematic Universe series films, as they join forces for the peacekeeping organization S.H.I.E.L.D. led by Nick Fury.";

        testTvShow.name = "The Simpsons";
        testTvShow.first_air_date = JSON_STRING_DATE.parse("1989-12-17");
        testTvShow.id = 456;
        testTvShow.homepage = "http://www.thesimpsons.com/";
        testTvShow.original_language = "en";
        testTvShow.original_name = "The Simpsons";
        testTvShow.overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.";
        testTvShow.type = "Scripted";
        testTvShow.external_ids = new TvExternalIds();
        testTvShow.external_ids.tvdb_id = 71663;
        testTvShow.external_ids.freebase_mid = "/m/07c72";
        testTvShow.external_ids.freebase_id = "/en/the_simpsons";
        testTvShow.external_ids.imdb_id = "tt0096697";
        testTvShow.external_ids.tvrage_id = 6190;
        testTvShow.external_ids.id = testTvShow.id;
        testTvShowChangesStartDate = JSON_STRING_DATE.parse("2017-2-2");
        testTvShowChangesEndDate = JSON_STRING_DATE.parse("2017-2-4");

        testTvSeason._id = "5256c24719c2956ff600565b";
        testTvSeason.id = 3582;
        testTvSeason.season_number = 1;
        testTvSeason.name = "Season 1";
        testTvSeason.poster_path = "/u3U4aGwSTpvbULPHTsm7XhadynO.jpg";
        testTvSeason.overview = "The Simpsons' first season originally aired between December 17, 1989 and May 13, 1990, beginning with the Christmas special \"Simpsons Roasting on an Open Fire\". The executive producers for the first production season were Matt Groening, James L. Brooks, and Sam Simon.\n\nThe series was originally set to debut in late 1989 with the episode \"Some Enchanted Evening\", which was meant to introduce the main characters; during the first screening of the episode, the producers discovered that the animation was so appalling that 70% of the episode needed to be redone. The producers considered aborting the series if the next episode turned out as bad, but it only suffered from easily fixable problems. The producers convinced Fox to move the debut to December 17, and aired \"Simpsons Roasting on an Open Fire\" as the first episode of the series. The first season won one Emmy Award, and received four additional nominations.\n\nThe DVD boxset was released on September 25, 2001 in Region 1 and September 24, 2001 in both Region 2 and Region 4. Season one was also released for the iTunes Store on December 22, 2010, dubbed a \"digital edition.\"";
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
        testTvEpisode.external_ids = new TvExternalIds();
        testTvEpisode.external_ids.imdb_id = "tt0348034";
        testTvEpisode.external_ids.freebase_mid = "/m/0129zr";
        testTvEpisode.external_ids.freebase_id = "/en/simpsons_roasting_on_an_open_fire";
        testTvEpisode.external_ids.tvdb_id = 55452;
        testTvEpisode.external_ids.tvrage_id = 206468;
        testTvEpisodeChangesStartDate = JSON_STRING_DATE.parse("2017-2-2");
        testTvEpisodeChangesEndDate = JSON_STRING_DATE.parse("2017-2-4");

        testCredit.credit_type = CreditType.CAST;
        testCredit.department = "Actors";
        testCredit.job = "Actor";
        testCredit.media_type = MediaType.TV;
        testCredit.id = "5256bdc319c2956ff6001554";
        testCredit.media = new CreditMedia();
        testCredit.media.id = 456;
        testCredit.media.name = "The Simpsons";
        testCredit.media.original_name = "The Simpsons";
        testCredit.media.character = "Bart Simpson / Nelson Muntz / Ralph Wiggum / Todd Flanders / Kearney / Various (voice)";

        testReview.id = "581bbdbbc3a36805c60001f1";
        testReview.author = "iheardthatmoviewas";
        testReview.content = "With each new Marvel film one could expect the studio to push the limits of what a superhero film should consist of. Films such as Captain America: Winter Solider, Guardians of the Galaxy and Ant-Man all consisted of elements that we have never seen within a superhero film before. Now, Marvel Studios attempts to push the envelop once more by leaving the world of high tech armors and super soldiers and entering the mystical world of magic. Leading this charge is Benedict Cumberbatch's Doctor Stephen Strange and hopefully he could answer why there is this strange mystical feeling that we have seen this all before.\r\n\r\n\r\n\r\n> From Marvel comes “Doctor Strange,” the story of world-famous neurosurgeon Dr. Stephen Strange whose life changes forever after a horrific car accident robs him of the use of his hands. When traditional medicine fails him, he is forced to look for healing, and hope, in an unlikely place—a mysterious enclave known as Kamar-Taj. He quickly learns that this is not just a center for healing but also the front line of a battle against unseen dark forces bent on destroying our reality. Before long Strange—armed with newly acquired magical powers—is forced to choose whether to return to his life of fortune and status or leave it all behind to defend the world as the most powerful sorcerer in existence.\r\n\r\nI am going to start off the review by discussing the only strong positive thing that came out of Doctor Strange and its by far the visuals. Beautiful vibrant colors pop at you throughout the film, the costume design is beyond extravagant and the framing is spot on. Doctor Strange and his fellow Sorcerers Supremes powers aren't fully on display until they enter the Mirror Dimension and my god, Tony Stark and Bruce Banner would hate this place. As they are men of science, they would hate that all rules of physics are thrown out the window as our Sorcerers Supremes are capable of breaking buildings apart and reforming to however they please. It is hard to not reference Inception but no one here is dreaming, these visuals are being created by the hand of mystical men and they use it to their power.\r\n\r\nIn all honestly, pushing the envelope when it comes to visuals was not a difficult task at all. Heck, if they didn't I would have been extremely disappointed as it was an obvious opportunity to take advantage of since we are dealing with magic. I am still extremely disappointed regardless as director Scott Derrickson completely missed another obvious opportunity to push the envelope this time in regards of character development.\r\n\r\nDoctor Stephen Strange is very much like a Tony Stark and other superheroes we have seen in films before as he is arrogant, egoistical and full of it. After Strange's accident in which he suffered extreme nerve damage to both hands, he dishes out every penny to his name to repair his hands in hopes he could continue his career as neurosurgeon. Every attempt fails and Strange becomes desperate enough to head to Nepal and finds himself under the guidance of Tilda Swinton's The Ancient One. Learning a whole new concept in the mystic arts forces Strange to reshape his way of thinking but Scott Derrickson does not let this have Strange reshape who he is as a person as well.\r\n\r\nDoctor Strange should have found himself being more like a Steve Rogers towards the end of the film as learning the mystic arts and his accident alone should have broken his ego. But instead everything still comes easy to him as he masters magic with ease with very little tension. He has a couple hiccups in the beginning but once The Ancient One puts him under her wing it is smooth sailing and Strange is stealing texts, wielding powerful weapons and defeating Supremes with decades of training with ease.\r\n\r\nYou cannot say that Strange was destined for the mystic arts and that is why everything came so easy for him. Strange is only learning the mystic arts due to circumstance. If he doesn't get into an accident, which happened to him due to being careless, he doesn't find himself in Nepal. Harry Potter was destined for magic, not Strange. Strange fails to grow as a character because he continues to be proven right and no consequences seem to happen due to his actions.\r\n\r\nIt is hard to get into the character development of our supporting characters Benedict Wong's Wong and Chiwetel Ejiofor's Mordo as I would utterly spoil the film for you. All I would say though is that they were both mishandled and deserved better developments. It is a shame too because Wong and Ejiofor are superb actors but wasting talent is something Marvel Studios is used to.\r\n\r\nWhen will Marvel Studios get a villain right?  With Mads Mikkelsen playing our lead villain, Kaecilius, I thought we might finally get a cinematic Marvel villain that is on par to Killgrave and Kingpin over at Marvel's television department. Ultimately, Kaecilius and Doctor Strange end up fighting due to the most silliest cliche: being at the wrong place at the wrong time. The two individuals meet at the New York Sanctum Sanctorum in which Strange accidentally gets blown in to. Kaecilius is there to kill the protector and Sanctum Sanctorum itself and has no knowledge of who Strange is. In fact, he honestly believes Strange's name is in fact \"Mister Doctor.\" Still learning his craft, Strange is capable of putting up a better fight than the protector who should be a matter. I mean, he is a protector after all. There are no stakes for Strange, strange.\r\n\r\nVisually appealing could only get you so far as Doctor Strange fails to focus on the development of their characters that they made so appealing to the eye. Doctor Strange will become a huge asset to the Marvel Cinematic Universe and lets hope we see the rest of Strange's development when he casts his next spell. There is so much untapped potential in Strange's character, we just haven't seen the best of him yet.";
        testReview.iso_639_1 = "en";
        testReview.media_id = 284052;
        testReview.media_title = "Doctor Strange";
        testReview.media_type = "Movie";
        testReview.url = "https://www.themoviedb.org/review/581bbdbbc3a36805c60001f1";

        testKeyword.id = 1721;
        testKeyword.name = "fight";

        testListMovies.add(testMovie.id);
        testListMovies.add(550);
        testListMovies.add(99861);
        testListMovies.add(293660);

    }

}
