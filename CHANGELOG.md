Change Log
==========

## 2.11.0
_2024-06-07_
                                                                                                   
* Add Episode type attribute by @ZelKami in https://github.com/UweTrottmann/tmdb-java/pull/96
* TvService: support aggregate credits by @UweTrottmann in https://github.com/UweTrottmann/tmdb-java/pull/98
* Update retrofit [2.9.0 -> 2.11.0]
* Update okhttp [4.10.0 -> 4.12.0] 
* Update gson [2.10.1 -> 2.11.0]

## 2.10.0
_2023-06-14_

* Add WikiData IDs. Thanks @Sheigutn! [#95](https://github.com/UweTrottmann/tmdb-java/pull/95)
                      
## 2.9.0
_2023-05-25_

* Add trending endpoint. Thanks @gabrielsme! [#94](https://github.com/UweTrottmann/tmdb-java/issues/94)
* Update gson [2.9.1 -> 2.10.1].

## 2.8.1
_2022-09-01_

* Add explicit Gson dependency [2.9.1]: transient dependency on 2.8.5 has a [security vulnerability](https://github.com/google/gson/pull/1991)
  and this library uses the Gson API explicitly.
* Add explicit OkHttp dependency [4.10.0] as this library uses the API directly and to receive latest bug and security fixes.

## 2.8.0
_2022-09-01_

* Configuration: add `languages` and `primary_translations`.
* Add `runtime` on the episode level.
* Bundle R8 rules into library.

## 2.7.0
_2022-03-11_

* Add `tagline` to `TvShow`. [#90](https://github.com/UweTrottmann/tmdb-java/issues/90)

## 2.6.0
_2021-11-18_

* Add more discover filters (watch provider, region and monetization type).

## 2.5.0
_2021-09-30_

* Added AppendToResponseItem.TRANSLATIONS for episodes [#87](https://github.com/UweTrottmann/tmdb-java/pull/87) Thanks to @mlaggner!

## 2.4.0
_2021-05-20_

* Enhanced the method SearchService.tv() by the parameter `includeAdult` [#84](https://github.com/UweTrottmann/tmdb-java/pull/84) Thanks to @mlaggner!
* Added an alternative DiscoverFilter constructor to allow 'OR' as an operator choice [#85](https://github.com/UweTrottmann/tmdb-java/pull/85) Thanks to @Ethan-Icet!

## 2.3.1
_2021-03-11_

* Add additional Watch Provider lists.

## 2.3.0
_2021-03-11_

* Add Watch Providers methods for movies and TV.
* Update retrofit dependency to `2.9.0`, requires Java 8 or Android 5.0. If you need to support older devices,
  you might have to exclude this transitive dependency.

## 2.2.0
_2019-11-27_

* Update retrofit dependency to `2.6.2`.
* Add homepage, headquarters, logo_path and origin_country to `Network`. Thanks @chrisbanes!
* Changed budget and revenue in `Movie` from `Integer` to `Long`. Thanks @ProIcons!
* Added origin_country to `Company`. Thanks @ProIcons!

## 2.1.1
_2019-07-25_

* ReleaseDate: make types actual non-nullable constants (to allow using `==` in Kotlin).

## 2.1.0
_2019-07-25_

* Movies: add region parameter to various lists (popular, top rated, ...). This requires code updates.
* Search: add region, remove search type, reorder params. This requires code updates.
* Kotlin: allow nullable values when using the discover builders.

## 2.0.3
_2019-04-12_

* Add `name` property to `Translation`, used for TV shows instead of `title`. Thanks @myron0815!

## 2.0.2
_2019-03-14_

* Add country code and translation data (title, overview, website) to `Translation`. Thanks @myron0815!

## 2.0.1
_2019-02-08_

* Do not use `ThreadLocal.withInitial` from Java 8, it's not available on Android.

## 2.0.0
_2019-02-07_

* Update `retrofit` dependency to `2.5.0`. The previous versions are affected by a path-traversal security vulnerability. 
  https://github.com/square/retrofit/blob/master/CHANGELOG.md
* Produce Java 8 bytecode. For Android this requires Android Gradle Plugin 3.2.x or newer.
* Fix crash due to missing `character` property for cast credits. Thanks @JeremyQuagmire!
  https://github.com/UweTrottmann/tmdb-java/pull/69
* For the `com.uwetrottmann.tmdb2` package return values and fields are now non-null unless otherwise annotated. For 
  the `com.uwetrottmann.tmdb2.entities` package return values and fields are now annotated nullable.
  https://github.com/UweTrottmann/tmdb-java/pull/70
* Simplified session handling: instead of passing an `AuthenticationType` the library will use an available account
  session, or if not available a guest session. Also `Tmdb` methods regarding sessions have changed. 
  See https://github.com/UweTrottmann/tmdb-java/pull/71 for details.

## 1.10.1
_2018-09-21_

* Do not leak the response body if TMDB asks to retry the request.
* Add `last_episode_to_air` and `next_episode_to_air` to `TvShow`. Thanks @serafo27!

## 1.10.0
_2018-09-12_

* Clean up some methods, notably those without language param.
* Add language parameter to People summary methods, thanks @JeremyQuagmire!

## 1.9.0
_2018-08-08_

* Support getting external IDs for movies. Thanks @JeremyQuagmire!
* Drop `BaseExternalIds`. Properties have moved to their respective subclasses. Added `TvEpisodeExternalIds`.
* Add new social media IDs to external IDs.
* Update `retrofit` dependency to `2.4.0`.

## 1.8.4
_2018-04-25_

* Add `BaseTvSeason.episode_count` which is included in a show summary. Thanks @stavangr!
* Allow lists to include shows: `List.items` type changed to `Media` from `BaseMovie`. Thanks @stavangr!

## 1.8.3
_2018-02-22_

* Do not crash if response is unsuccessful and error code is not handled.
* Undo throwing on unsuccessful responses with known error codes. To analyze why a response is unsuccessful 
  use `Tmdb.throwOnKnownError(response)` instead.

## 1.8.2
_2018-02-02_

* Undo rename of TvService to TvShowService.
* Removed TmdbInvalidAcceptHeaderException: managed by retrofit. Thanks @ProIcons!
* Move jobs endpoint into `ConfigurationService`, returns list of `Jobs`.
* Find results should return base media classes. #49 Thanks @ProIcons!
* Add missing fields to TaggedImage entity. #51 Thanks @ProIcons!
* Video type is now an enum (was a String). #52 Thanks @ProIcons!

## 1.8.1
_2017-08-02_
* Fix: If multiple responses containing a date are processed at the same time date formatting fails with exceptions. Thanks @chrisbanes!

## 1.8.0
_2017-07-19_
* Add missing API methods. Note that this includes breaking changes due to renamed services and entities. Full details in the [commit message](https://github.com/UweTrottmann/tmdb-java/commit/b79f63e2347ecb77750224e3df1fada378a776e7). Thanks @ProIcons!
* If API calls fail, error details are wrapped in `TmdbException` and subclasses (they are still `IOException`). Thanks @ProIcons!
* Support for account and guest sessions. Thanks @ProIcons!

## 1.7.0
_2017-05-20_

* Add `original_language` to `Movie`. Thanks @ProIcons!
* Support appending translations to movie response. Thanks @ProIcons!
* Update [retrofit to 2.3.0][3].

## 1.6.0
_2017-01-18_

* Update `discover` methods, add helper builders`tmdb.discoverMovie()` and `tmdb.discoverTv()`. 

## 1.5.0
_2016-12-19_

* Add `review/{review_id}` with new `ReviewsService`. Thanks @MostafaMatar! 

## 1.4.0
_2016-11-30_

* Add `status` to movie, add `Status` enum. Thanks @cicoub13! 

## 1.3.0
_2016-10-15_

* Add `still_sizes` to `ImagesConfiguration` (used by TV episode stills). Thanks @SimonVT! 

## 1.2.0
_2016-07-31_

* Added movie and TV genre methods. Thanks @jackwakefield!

## 1.1.0
_2016-07-14_

* Removed built-in logging support. Simply subclass `Tmdb` and add your own logger by overriding `setOkHttpClientDefaults()`.
* Support for TV content ratings. Thanks @davidsben!
* Additional `append_to_response` fields for movies and shows. Thanks @urizev!

## 1.0.0
_2016-05-26_

* Update to `retrofit2`. Read about the most [notable changes and benefits](https://github.com/square/retrofit/blob/master/CHANGELOG.md#version-200-2016-03-11).
 You will have to make changes to your app, see the [README][4] for an example of the new code flow.
* Package name changed to `com.uwetrottmann.tmdb2`. So you can keep using the old version while updating your code.

0.10.1 *(2016-03-15)*
--------------------

 * Use `ReleaseDatesResults` when using `append_to_response` to get movie information. Thanks to @urizev.

0.10.0 *(2016-03-04)*
--------------------

 * Add `/movie/{id}/release_dates` thanks to @urizev.
 * Deprecate `/movie/{id}/releases`, see [update post](https://plus.google.com/+TravisBellTMDb/posts/4uNG5uSwtfe).

0.9.2 *(2016-02-26)*
--------------------

 * Add `/person/{id}/tagged_images` thanks to @urizev.
 * Add some missing fields to `Media` thanks to @urizev.

0.9.1 *(2016-01-22)*
--------------------

 * Update `okhttp` suggested dependency to 2.7.2.
 * Fix discover methods.

0.9.0 *(2015-05-11)*
--------------------
 * Support Collections service. Thanks to @mlaggner!
 * Support `/movie/{id}/tranlations`. Thanks to @ssouris!
 * Using `Images` instead of `TvEpisodeImages`, adds `stills`. Thanks to @mlaggner!    
 * `TvService.tv()`, `TvSeasonsService.season()`, `TvEpisodesService.episode()` have a new append-to-response parameter. Thanks to @mlaggner!
 * Add origin_country to TV show search results.

0.8.0 *(2015-02-23)*
--------------------
 * Support more services (Discover, TV seasons and episodes) and methods, thanks to @migueljteixeira!
 * Drop method variants (e.g. movie summary call with just a TMDb id). Use the full parameter variant and supply `null`
   for non-required query parameters.
 * Drop any async variants for methods. They only encourage bad programming (e.g. not using proper background threads).
 * Renamed `PersonService` to `PeopleService` to be consistent with docs.
 * Switch to JUnit 4 for testing.
 * Update `retrofit` to 1.9.0, which allows to drop the `okhttp-urlconnection` dependency.
 * Update `okhttp` suggested dependency to 2.2.0.
 * Require Java 1.7.

0.7.0 *(2014-08-12)*
--------------------
 * Easier customization of `RestAdapter`: set your own HTTP client or executor by overriding `newRestAdapterBuilder()`.
 * Add `okhttp` and `okhttp-urlconnection` 2.0.0 as optional dependencies.

0.6.0 *(2014-06-30)*
--------------------
 * Support `/find/{id}`.
 * Support `/tv/{id}/credits`.
 * Add language support to Person service credit endpoints.
 * Add special API key for testing, no need to fill in your own any longer. Just run the tests.
 * Update to [retrofit][3] 1.6.0.

0.5.0 *(2014-03-17)*
--------------------
 * Support `/person/{id}`, `/person/{id}/movie_credits`, `/person/{id}/tv_credits` and `/person/{id}/combined_credits`. Thanks @chrisbanes!

0.4.1 *(2014-03-15)*
--------------------
 * First Maven Central release! See the [README][4] for details.
 * Limited fest dependency to test scope.

0.4.0 *(2014-03-09)*
--------------------
 * Use secure TMDb endpoint (https://api.themoviedb.org/3). If you use tmdb-java on Android with okhttp, you should update okhttp to at least 1.5.0 to avoid SSL context issues.

0.3.0 *(2014-02-13)*
--------------------
 * Allow `append_to_response` of supported movie endpoints to `MoviesService.summary()`. Thanks @chrisbanes!
 * Add `MoviesService.releases()`. Thanks @chrisbanes!
 * Add `secure_base_url` and `change_keys` to `Configuration`.
 * Use [retrofit][3] 1.4.1.

0.2.0 *(2013-11-17)*
--------------------
 * Port to [retrofit][1].
 * No longer packaged with dependencies, add them yourself as you see fit (see http://uwetrottmann.github.io/tmdb-java/).
 * Releases published [on GitHub][2].

0.1
---
 * Do not crash on empty (e.g. "") release dates of movies.
 * Switch to a maven powered build.
 * New `myjson` branch which uses a custom gson library to work around https://sites.google.com/site/gson/gson-on-android.


  [1]: https://github.com/square/retrofit
  [2]: https://github.com/UweTrottmann/tmdb-java/releases
  [3]: https://github.com/square/retrofit/blob/master/CHANGELOG.md
  [4]: https://github.com/UweTrottmann/tmdb-java/blob/master/README.md
