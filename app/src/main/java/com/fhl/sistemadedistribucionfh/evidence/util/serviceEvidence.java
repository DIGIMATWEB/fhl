package com.fhl.sistemadedistribucionfh.evidence.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.responseStatusManifestOrTicket;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.requestRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRate;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface serviceEvidence {
    @Multipart
    @POST("SetPickupEvidencia")
    Call<ApiResponse> uploadFile(
            @Header("Authorization") String authorization,
            @Part("FolioObjeto") RequestBody folioObjeto,
            @Part("TipoEvidencia") RequestBody tipoEvidencia,
            @Part MultipartBody.Part listaArchivos,
            @Part("Usuario") RequestBody usuario,
            @Part("FlujoId") Integer FlujoId);

    @Multipart
    @POST("SetPickupEvidencia")
    Call<ApiResponse> uploadFiles(
            @Header("Authorization") String authorization,
            @Part("FolioObjeto") RequestBody folioObjeto,
            @Part("TipoEvidencia") RequestBody tipoEvidencia,
            @Part List<MultipartBody.Part> listaArchivos,
            @Part("Usuario") RequestBody usuario,
            @Part("FlujoId") Integer FlujoId);

    @POST(RetrofitEndPoints.RATE_STARS)
    Call<responseRate> sendRate(@Header("Authorization") String authorization,@Body requestRate request);
    @GET(RetrofitEndPoints.DETAIL_TICKET_SENDTRIPLUS)
    Call<TicketsDetailSentriplus> getTicket(
            @Header("Authorization") String authorizationHeader,
            @Query("folioDespacho") String folioDespacho,
            @Query("folioTicket") String folioTicket
    );


    @Multipart
    @POST(RetrofitEndPoints.MANIFIESTO_SET_ESTATUS)
    Call<responseStatusManifestOrTicket> setEstatusByManifiestoOrTicket(
            @Header("Authorization") String authorization,
            @Part("folioDespacho") String folioDespacho,
            @Part("estatusIdDespacho") Integer estatusIdDespacho,
            @Part("folioTicket") String folioTicket,
            @Part("estatusIdTicket") Integer estatusIdTicket
    );
}
