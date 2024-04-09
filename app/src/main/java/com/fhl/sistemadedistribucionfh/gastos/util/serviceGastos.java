package com.fhl.sistemadedistribucionfh.gastos.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.ResponseGastosv2;
import com.fhl.sistemadedistribucionfh.gastos.model.requestGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.responseGastos;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceGastos {
//    @POST(RetrofitEndPoints.GASTOS)
//    Call<responseGastos> getGastos(@Body requestGastos request);
@GET(RetrofitEndPoints.MANIFEST_PEP)
Call<ResponseGastosv2> getgastos(
        @Header("Authorization") String authorizationHeader,
        @Query("operadorId") String operadorId,
        @Query("gastosOperativos") Boolean gastosOperativos
);
}
