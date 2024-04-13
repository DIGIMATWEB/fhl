package com.fhl.sistemadedistribucionfh.sendTripPlus.util;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.responseStatusManifestOrTicket;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.requestLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.responseLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.SendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.ResponseSendTripPlus;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface serviceSendtripPlus {
    @POST(RetrofitEndPoints.SENDTRIPPLUS)
    Call<ResponseSendTripPlus> setSendtriplus(@Body SendTripPlus request );
    @POST(RetrofitEndPoints.LOGINAVOCADO)
    Call<responseLoginAvocado> loginAvocado(@Body requestLoginAvocado requestOrigin);
    @Multipart
    @POST(RetrofitEndPoints.MANIFIESTO_SET_ESTATUS)
    Call<responseStatusManifestOrTicket> setEstatusByManifiestoOrTicket(
            @Header("Authorization") String authorization,
            @Part("folioDespacho") RequestBody folioDespacho,
            @Part("estatusIdDespacho") RequestBody estatusIdDespacho,
            @Part("folioTicket") RequestBody folioTicket,
            @Part("estatusIdTicket") RequestBody estatusIdTicket
    );

}
