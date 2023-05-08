package com.fhl.sistemadedistribucionfh.nmanifest.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifest.model.requestManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.model.responseManifest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface manifestUtil {
    @POST(RetrofitEndPoints.MANIFEST)
    Call<responseManifest> getManifest(@Body requestManifest request);
}
