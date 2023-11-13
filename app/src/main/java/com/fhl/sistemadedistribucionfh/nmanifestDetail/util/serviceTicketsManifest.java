package com.fhl.sistemadedistribucionfh.nmanifestDetail.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.requestTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceTicketsManifest {
     @POST(RetrofitEndPoints.TICKETS)
     Call<responseTicketsManifestV2> getTickets(@Body requestTicketsManifestV2 request);
}
