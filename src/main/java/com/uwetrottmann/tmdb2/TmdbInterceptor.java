package com.uwetrottmann.tmdb2;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * {@link Interceptor} to add the API key query parameter. As it modifies the URL ensure this is added as regular
 * interceptor, otherwise caching will be broken.
 */
public class TmdbInterceptor implements Interceptor {

    private Tmdb tmdb;

    public TmdbInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return handleIntercept(chain, tmdb.apiKey());
    }

    /**
     * If the host matches {@link Tmdb#API_HOST} adds a query parameter with the API key.
     */
    public static Response handleIntercept(Chain chain, String apiKey) throws IOException {
        Request request = chain.request();
        if (!Tmdb.API_HOST.equals(request.url().host())) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request);
        }

        // add (or replace) the API key query parameter
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, apiKey);

        Request.Builder builder = request.newBuilder();
        builder.url(urlBuilder.build());

        return chain.proceed(builder.build());
    }

}
