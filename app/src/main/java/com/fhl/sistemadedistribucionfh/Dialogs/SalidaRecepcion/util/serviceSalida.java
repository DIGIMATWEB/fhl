package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.responseCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;

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
    @GET(RetrofitEndPoints.CORTINA)
    Call<responseCortina> getcortina(
            @Header("Authorization") String authorizationHeader,
            @Query("folioDespacho") String folioDespacho,
            @Query("codigoAnden") String codigoAnden
    );

    @GET(RetrofitEndPoints.TICKETS_PEP)
    Call<responseTicketsManifestV2> getTicketsV2(
            @Header("Authorization") String authorizationHeader,
             @Query("folioDespacho") String folioDespacho
    );
    @GET(RetrofitEndPoints.SALIDA_V2)
    Call<ResponseSellos> getSalidaV2(@Header("Authorization") String authorizationHeader,
                                     @Query("folioDespacho") String operadorId
    );
}
