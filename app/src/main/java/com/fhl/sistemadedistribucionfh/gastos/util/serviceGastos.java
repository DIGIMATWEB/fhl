package com.fhl.sistemadedistribucionfh.gastos.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.gastos.model.requestGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.responseGastos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceGastos {
    @POST(RetrofitEndPoints.GASTOS)
    Call<responseGastos> getGastos(@Body requestGastos request);
}
