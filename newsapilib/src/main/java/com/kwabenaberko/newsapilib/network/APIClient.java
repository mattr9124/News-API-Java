package com.kwabenaberko.newsapilib.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

public class APIClient {
    private static Retrofit mRetrofit = null;

    private static Retrofit getRetrofit(){
        if(mRetrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request request = chain.request().newBuilder()
                                .header("User-Agent", "Mozilla/5.0")
                                .build();
                        return chain.proceed(request);
                    })
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

    public static APIService getAPIService(){
        return getRetrofit().create(APIService.class);
    }

}

