package com.shanan.bplaces.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shanan.bplaces.rest.ApiConstants.API_BASE_URL;

/**
 * Created by Shanan on 23/09/2017.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static ApiEndPointInterface mApiEndPointInterface;

    public static <S> S createService(Class<S> serviceClass) {

        httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static ApiEndPointInterface getApiEndPointInterface() {
        if (mApiEndPointInterface == null) {
            mApiEndPointInterface = ServiceGenerator.createService(ApiEndPointInterface.class);
        }
        return mApiEndPointInterface;
    }
}
