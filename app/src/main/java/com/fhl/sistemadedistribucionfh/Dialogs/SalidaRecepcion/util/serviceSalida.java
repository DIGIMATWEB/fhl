package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface serviceSalida {
    // @POST(RetrofitEndPointsV2.GET_CHECKLIST)
    //    Call<checkListResponse> getSCheckLits(@Body checklistRequest request);
    @GET(RetrofitEndPoints.MANIFEST_PEP)
    Call<responseManifestSalidaV2> getManifestV2(
            @Header("Authorization") String authorizationHeader,
            @Query("operadorId") String operadorId,
            @Query("folioDespacho") String manifest
    );
}
