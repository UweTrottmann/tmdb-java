**Pull requests (e.g. support for more API endpoints, bug fixes) are welcome!**

# tmdb-java

A Java wrapper around the [TMDb v3 API](https://developers.themoviedb.org/3) using [retrofit 2](https://square.github.io/retrofit/).

## Usage
<a href="https://search.maven.org/search?q=tmdb-java">Available on Maven Central</a>

Add the following dependency to your Gradle project:

```groovy
implementation 'com.uwetrottmann.tmdb2:tmdb-java:2.2.0'
```

or your Maven project:

```xml
<dependency>
    <groupId>com.uwetrottmann.tmdb2</groupId>
    <artifactId>tmdb-java</artifactId>
    <version>2.2.0</version>
</dependency>
```

Use like any other retrofit2 based service. For example:

```java
// Create an instance of the service you wish to use
// you should re-use these
Tmdb tmdb = new Tmdb(API_KEY);
MoviesService moviesService = tmdb.moviesService();
// Call any of the available endpoints
try {
    Response<Movie> response = moviesService
        .summary(550)
        .execute();
    if (response.isSuccessful()) {
        Movie movie = response.body();
        System.out.println(movie.title + " is awesome!");
    }
} catch (Exception e) {
    // see execute() javadoc 
}
```

See test cases in `src/test/` for more examples and the [retrofit website](https://square.github.io/retrofit/) for configuration options.

### Android
This library ships Java 8 bytecode. This requires Android Gradle Plugin 3.2.x or newer.

## Use Proguard, R8!
It is likely not every method in this library is used, so it is probably useful to strip unused ones with Proguard.
Apply the [Proguard rules for retrofit](https://square.github.io/retrofit/#download).

Due to R8 being very eager in stripping unused fields even if they are set by a constructor, 
prevent entities from getting optimized. Obviously they also should not be obfuscated.
```proguard
-keep class com.uwetrottmann.tmdb2.entities.** { *; }
-keep class com.uwetrottmann.tmdb2.enumerations.** { *; }
```

## License

Created by [Uwe Trottmann](https://uwetrottmann.com).

See full [list of contributors](https://github.com/UweTrottmann/tmdb-java/graphs/contributors).

Except where noted otherwise, released into the [public domain](UNLICENSE).
Do not just copy, make it better.
