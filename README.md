**[Pull requests](CONTRIBUTING.md) (e.g. support for more API endpoints, bug fixes) are welcome!**

# tmdb-java

A Java wrapper around the [TMDb v3 API](https://developers.themoviedb.org/3) using [retrofit 2](https://square.github.io/retrofit/).

## Usage
<a href="https://search.maven.org/search?q=tmdb-java">Available on Maven Central</a>

Add the following dependency to your Gradle project:

```groovy
implementation("com.uwetrottmann.tmdb2:tmdb-java:2.8.1")
```

or your Maven project:

```xml
<dependency>
    <groupId>com.uwetrottmann.tmdb2</groupId>
    <artifactId>tmdb-java</artifactId>
    <version>2.8.1</version>
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

## Proguard / R8
It is likely not every method in this library is used, so it is probably useful to strip unused ones with Proguard.
Apply the [Proguard rules for retrofit](https://square.github.io/retrofit/#download).

The specific rules for this library are [already bundled](src/main/resources/META-INF/proguard/tmdb-java.pro) into the 
release which can be interpreted by R8 automatically, ProGuard users must manually add the rules.

## License

This work by [Uwe Trottmann](https://uwetrottmann.com) is licensed under the [Apache License 2.0](LICENSE.txt).

[Contributors](https://github.com/UweTrottmann/tmdb-java/graphs/contributors) and changes are tracked by Git.

Do not just copy, make it better.
