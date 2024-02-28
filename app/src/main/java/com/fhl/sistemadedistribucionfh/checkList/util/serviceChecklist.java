package com.fhl.sistemadedistribucionfh.checkList.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.requestChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseChecklistV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceChecklist {
     @POST(RetrofitEndPoints.CHECKLISTV2)
     Call<responseChecklistV2> getChecklist(@Header("Authorization") String authorizationHeader,
                                            @Query("operadorId") Integer operadorId);
}
