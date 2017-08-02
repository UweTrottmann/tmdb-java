Change Log
==========

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
