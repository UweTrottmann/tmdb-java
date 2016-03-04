**Pull requests (e.g. support for more API endpoints, bugfixes) are welcome!**

tmdb-java
============

A Java wrapper around the [TMDb v3 API][1] using [retrofit][2].

Usage
-----
<a href="https://search.maven.org/#search%7Cga%7C1%7Ctmdb-java"><img src="https://img.shields.io/maven-central/v/com.uwetrottmann/tmdb-java.svg?style=flat-square"></a>

Add the following dependency to your Gradle project:

```groovy
compile 'com.uwetrottmann:tmdb-java:0.9.2'
```

or your Maven project:

```xml
<dependency>
    <groupId>com.uwetrottmann</groupId>
    <artifactId>tmdb-java</artifactId>
    <version>0.9.2</version>
</dependency>
```

Dependencies
------------
If you rather use the [released jar][3], add dependencies yourself as you see fit.
You can find the required dependencies in [pom.xml][4].

Example
-------

```java
// Create an instance of the service you wish to use
// you can keep this around
Tmdb tmdb = new Tmdb();
tmdb.setApiKey("yourapikey");
MovieService movieService = tmdb.movieService();
//
// Call any of the available endpoints
Movie movie = movieService.summary(550);
Trailers trailers = movieService.trailers(550);
```

See test cases in `src/test/` for more examples.

Related projects
----------------

[tmdb-rx-java](https://github.com/migueljteixeira/tmdb-rx-java) - adds RxAndroid support

License
-------

    Copyright 2013-2016 Uwe Trottmann

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




 [1]: http://docs.themoviedb.apiary.io/
 [2]: https://github.com/square/retrofit
 [3]: https://github.com/UweTrottmann/tmdb-java/releases
 [4]: https://github.com/UweTrottmann/tmdb-java/blob/master/pom.xml
