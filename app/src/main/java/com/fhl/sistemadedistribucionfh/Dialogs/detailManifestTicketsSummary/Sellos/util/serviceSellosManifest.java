package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.util;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface serviceSellosManifest {
    @GET(RetrofitEndPoints.MANIFEST_PEP)
    Call<responseManifestSalidaV2> getManifestV2(
            @Header("Authorization") String authorizationHeader,
            @Query("operadorId") String operadorId,
            @Query("folioDespacho") String folioDespacho
    );

    @GET(RetrofitEndPoints.SALIDA_V2)
    Call<ResponseSellos> getSalidaV2(@Header("Authorization") String authorizationHeader,
                                     @Query("folioDespacho") String operadorId
    );
}
