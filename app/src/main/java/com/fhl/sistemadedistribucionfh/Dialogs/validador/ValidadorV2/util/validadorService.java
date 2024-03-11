package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.util;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.responseValidadorV2;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface validadorService {
    @GET(RetrofitEndPoints.MANIFEST_PEP)
    Call<responseValidadorV2> getManifestV2Detail(
            @Header("Authorization") String authorizationHeader,
            @Query("operadorId") Integer operadorId,
            @Query("folioDespacho") String folioDespacho
    );
}
