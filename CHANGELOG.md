Change Log
==========
Supported API calls
-------------------

 * [Configuration](http://docs.themoviedb.apiary.io/#reference/configuration)
 * [Collections](http://docs.themoviedb.apiary.io/#reference/collections)
 * [Discover](http://docs.themoviedb.apiary.io/#reference/discover)
 * [Find](http://docs.themoviedb.apiary.io/#reference/find)
 * [Movies](http://docs.themoviedb.apiary.io/#reference/movies) _incomplete_
 * [People](http://docs.themoviedb.apiary.io/#reference/people) _incomplete_
 * [Search](http://docs.themoviedb.apiary.io/#reference/search) _incomplete_
 * [TV](http://docs.themoviedb.apiary.io/#reference/tv) _incomplete_
 * [TV Seasons](http://docs.themoviedb.apiary.io/#reference/tv-seasons) _incomplete_
 * [TV Episodes](http://docs.themoviedb.apiary.io/#reference/tv-episodes) _incomplete_

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
