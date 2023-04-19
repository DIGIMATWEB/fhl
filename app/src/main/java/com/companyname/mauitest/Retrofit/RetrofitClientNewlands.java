package com.companyname.mauitest.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientNewlands {
    private static Retrofit retrofit;
    //BASE_URL al Servidor Test
    private static final String BASE_URL = RetrofitEndPoints.URL_NEWLANDS; //
    private static OkHttpClient okHttpClient; //
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(300, TimeUnit.SECONDS)
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .build();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
