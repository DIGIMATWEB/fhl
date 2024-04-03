package com.fhl.sistemadedistribucionfh.sendTripPlus.util;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.requestLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.responseLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.SendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.ResponseSendTripPlus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceSendtripPlus {
    @POST(RetrofitEndPoints.SENDTRIPPLUS)
    Call<ResponseSendTripPlus> setSendtriplus(@Body SendTripPlus request );
    @POST(RetrofitEndPoints.LOGINAVOCADO)
    Call<responseLoginAvocado> loginAvocado(@Body requestLoginAvocado requestOrigin);
}
