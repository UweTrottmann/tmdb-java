**Pull requests (e.g. support for more API endpoints, bugfixes) are welcome!**

tmdb-java
============

A Java wrapper around the [TMDb v3 API][1] using [retrofit 2][2].

Usage
-----
<a href="https://search.maven.org/#search%7Cga%7C1%7Ctmdb-java"><img src="https://img.shields.io/maven-central/v/com.uwetrottmann.tmdb2/tmdb-java.svg?style=flat-square"></a>

Add the following dependency to your Gradle project:

```groovy
compile 'com.uwetrottmann.tmdb2:tmdb-java:1.0.0'
```

or your Maven project:

```xml
<dependency>
    <groupId>com.uwetrottmann.tmdb2</groupId>
    <artifactId>tmdb-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

Example
-------

```java
// Create an instance of the service you wish to use
// you can keep this around
Tmdb tmdb = new Tmdb("yourapikey");
MovieService movieService = tmdb.movieService();
//
// Call any of the available endpoints
Call<Movie> call = movieService.summary(550);
Movie movie = call.execute().body();
Call<Trailers> callTrailers = movieService.trailers(550);
Trailers trailers = callTrailers.execute().body();
```

See test cases in `src/test/` for more examples and the [retrofit website][2] for configuration options.

License
-------

Created by [Uwe Trottmann](http://uwetrottmann.com/contact).
Except where noted otherwise, released into the [public domain](UNLICENSE).
Do not just copy, make it better.


 [1]: http://docs.themoviedb.apiary.io/
 [2]: http://square.github.io/retrofit/