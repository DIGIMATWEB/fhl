package com.fhl.sistemadedistribucionfh.Salida.Util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.Salida.Model.requestSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.responseSalida;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceSalida {

     @POST(RetrofitEndPoints.SALIDA)
     Call<responseSalida> getSalida(@Body requestSalida request);
}
