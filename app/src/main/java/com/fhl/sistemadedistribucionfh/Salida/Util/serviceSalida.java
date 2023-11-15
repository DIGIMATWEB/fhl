package com.fhl.sistemadedistribucionfh.Salida.Util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.requestSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.responseSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceSalida {

//     @POST(RetrofitEndPoints.SALIDA)
//     Call<responseSalida> getSalida(@Body requestSalida request);
     @GET(RetrofitEndPoints.SALIDA_V2)
     Call<ResponseSalida> getSalidaV2(@Header("Authorization") String authorizationHeader,
                                      @Query("folioDespacho") String operadorId
     );

}
