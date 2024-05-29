package com.fhl.sistemadedistribucionfh.nmanifest.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface manifestUtil {
//    @POST(RetrofitEndPoints.MANIFEST)
//    Call<responseManifest> getManifest(@Body requestManifest request);

    //@POST(RetrofitEndPoints.MANIFEST_PEP)
    //Call<responseManifestV2> getManifestV2(@Header("Authorization") String authToken, @Body requestManifestV2 request);

    @GET(RetrofitEndPoints.MANIFEST_PEP)
    Call<responseManifestV2> getManifestV2(
            @Header("Authorization") String authorizationHeader,
            @Query("operadorId") String operadorId
    );
    @GET(RetrofitEndPoints.TICKETS_PEP)
    Call<responseTicketsManifestV2> getTicketsV2(
             /*@Header("Authorization") String authorizationHeader,
             @Query("folioDespacho") String folioDespacho*/
            @Query("folioDespacho") String folioDespacho,
            @Query("gastosOperativos") Boolean gastosoperativos,
            @Header("Authorization") String authorizationHeader
    );
}
