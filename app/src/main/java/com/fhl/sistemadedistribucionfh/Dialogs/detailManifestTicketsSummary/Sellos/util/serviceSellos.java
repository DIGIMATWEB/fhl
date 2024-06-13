package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.util;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.requestSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.responseSetSello;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceSellos {

     @POST(RetrofitEndPoints.SET_SELLO)
     Call<responseSetSello> setTSellos(@Header("Authorization")String token,
                                       @Body requestSello folio);

}
