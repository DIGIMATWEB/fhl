package com.fhl.sistemadedistribucionfh.nmanifest.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifest.model.requestManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.model.responseManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface manifestUtil {
    @POST(RetrofitEndPoints.MANIFEST)
    Call<responseManifest> getManifest(@Body requestManifest request);

    //@POST(RetrofitEndPoints.MANIFEST_PEP)
    //Call<responseManifestV2> getManifestV2(@Header("Authorization") String authToken, @Body requestManifestV2 request);

    @GET(RetrofitEndPoints.MANIFEST_PEP)
    Call<responseManifestV2> getManifestV2(
            @Header("Authorization") String authorizationHeader,
            @Query("operadorId") String operadorId
    );
}
