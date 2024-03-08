package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface serviceSetValidacionManifest {
    @POST(RetrofitEndPoints.SETVALIDACION)
    Call<responseSetValidacion> getValidacion(@Header("Authorization") String authorizationHeader, @Body requestSetValidacion request );
}
