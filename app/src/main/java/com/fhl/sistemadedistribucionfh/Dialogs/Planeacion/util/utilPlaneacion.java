package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.util;

import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.model.responseGetPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.responseReasons;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface utilPlaneacion {
    @GET(RetrofitEndPoints.GET_PLANEACION)
    Call<responseGetPlaneacion> getTicketData(@Header("Authorization")String token,
                                           @Query("FolioTicket") String folioTicket);

    @POST(RetrofitEndPoints.SET_PLANEACION)
    Call<responseGetPlaneacion> setTicketStatus(@Header("Authorization")String token,
                                                @Query("FolioTicket")  String folio,
                                                @Query("TipoSolicitudId") Integer i);
}
