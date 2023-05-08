package com.fhl.sistemadedistribucionfh.nmanifestDetail.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.requestTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.responseTicketsManifest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceTicketsManifest {
     @POST(RetrofitEndPoints.TICKETS)
     Call<responseTicketsManifest> getTickets(@Body requestTicketsManifest request);
}
