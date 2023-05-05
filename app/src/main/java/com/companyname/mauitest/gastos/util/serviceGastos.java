package com.companyname.mauitest.gastos.util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.checkList.model.requestChecklist;
import com.companyname.mauitest.gastos.model.requestGastos;
import com.companyname.mauitest.gastos.model.responseGastos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceGastos {
    @POST(RetrofitEndPoints.GASTOS)
    Call<responseGastos> getGastos(@Body requestGastos request);
}
