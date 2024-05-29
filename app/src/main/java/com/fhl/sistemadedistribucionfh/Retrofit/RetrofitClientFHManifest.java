package com.fhl.sistemadedistribucionfh.Retrofit;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientFHManifest {
    private static Retrofit retrofit;
    //BASE_URL al Servidor Test
    private static final String BASE_URL = RetrofitEndPoints.URL_PRODMANFEST; //
    private static OkHttpClient okHttpClient; //
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(2000, TimeUnit.SECONDS)
                    .connectTimeout(2000, TimeUnit.SECONDS)
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
