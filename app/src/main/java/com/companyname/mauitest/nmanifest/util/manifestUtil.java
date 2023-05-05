package com.companyname.mauitest.nmanifest.util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.nmanifest.model.requestManifest;
import com.companyname.mauitest.nmanifest.model.responseManifest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface manifestUtil {
    @POST(RetrofitEndPoints.MANIFEST)
    Call<responseManifest> getManifest(@Body requestManifest request);
}
