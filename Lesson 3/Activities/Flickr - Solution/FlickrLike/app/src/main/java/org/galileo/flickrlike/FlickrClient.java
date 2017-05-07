package org.galileo.flickrlike;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class FlickrClient {

    private static final String FLICKR_API_BASE_URL = "https://api.flickr.com";
    private static final String API_KEY = "YOUR_API_KEY_GOES_HERE";
    private Retrofit retrofit;

    FlickrClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder urlBuilder = request.url().newBuilder();
                urlBuilder.addQueryParameter("api_key", API_KEY);
                urlBuilder.addQueryParameter("format", "json");
                urlBuilder.addQueryParameter("nojsoncallback", "1");

                request = request.newBuilder().url(urlBuilder.build()).build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
            .baseUrl(FLICKR_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build();
    }

    FlickrService getFlickrService() {
        return retrofit.create(FlickrService.class);
    }
}
