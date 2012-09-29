tmdb-java
============

A Java wrapper around the [TMDb v3 API][1], based upon [JakeWharton/trakt-java/][2].

Remote services are grouped into local service objects which can be centrally
managed by a `ServiceManager` instance. The manager will act as a factory for
all of the services and will automatically initialize them with your API key.

Each service contains methods which correspond to a remote method. Each of
these methods instantiates a class that will allow for you to build the
parameters using the Java builder pattern.

Required remote method parameters will be arguments to the service method and
all of the methods in the returned builder are optional.

When fully assembled, you can trigger the remote execution by calling the
`fire()` method. This will return a native object which represents the result
of the execution. All returned objects are immutable and should be handled
as such.

Based upon
============

The overall package and class layout as well as most of the code
originates from [JakeWharton/trakt-java/][2].

Using with Android
============

Because some device manufactures shipped a non-hidden version of GSON you *HAVE* to replace the included GSON jar (see /libs) with a version with the package name customized, to e.g. myjson. For details see this [GSON help page][3].

License
=======

    Copyright 2012 Uwe Trottmann

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
 [2]: https://github.com/JakeWharton/trakt-java/
 [3]: https://sites.google.com/site/gson/gson-on-android
