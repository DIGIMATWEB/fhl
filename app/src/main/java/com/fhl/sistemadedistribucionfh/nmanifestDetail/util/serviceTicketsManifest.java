package com.fhl.sistemadedistribucionfh.nmanifestDetail.util;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.requestTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceTicketsManifest {
     /*@POST(RetrofitEndPoints.TICKETS)
     Call<responseTicketsManifestV2> getTickets(@Body requestTicketsManifestV2 request);*/
     @GET(RetrofitEndPoints.TICKETS_PEP)
     Call<responseTicketsManifestV2> getTicketsV2(
             /*@Header("Authorization") String authorizationHeader,
             @Query("folioDespacho") String folioDespacho*/
             @Query("folioDespacho") String folioDespacho,
             @Query("gastosOperativos") Boolean gastosoperativos,
             @Header("Authorization") String authorizationHeader
     );

    @GET(RetrofitEndPoints.SALIDA_V2)
    Call<ResponseSellos> getSalidaV2(@Header("Authorization") String authorizationHeader,
                                     @Query("folioDespacho") String operadorId
    );
}
