package com.companyname.mauitest.Salida.Util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.Salida.Model.requestSalida;
import com.companyname.mauitest.Salida.Model.responseSalida;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceSalida {

     @POST(RetrofitEndPoints.SALIDA)
     Call<responseSalida> getSalida(@Body requestSalida request);
}
