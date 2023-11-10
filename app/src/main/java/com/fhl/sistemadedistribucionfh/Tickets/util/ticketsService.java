package com.fhl.sistemadedistribucionfh.Tickets.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.Tickets.model.ResoponseTicketsDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ticketsService {
    @GET(RetrofitEndPoints.DETALLE_TICKETS)
    Call<ResoponseTicketsDetail> getTicketsByManifiesto(
            @Header("Authorization") String authorization,
            @Query("folioDespacho") String manifiestoId,
            @Query("folioTicket") String folioTicket

    );
}
