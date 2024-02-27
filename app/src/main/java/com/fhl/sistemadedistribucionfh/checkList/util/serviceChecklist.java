package com.fhl.sistemadedistribucionfh.checkList.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.requestChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.responseChecklist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceChecklist {
     @POST(RetrofitEndPoints.CHECKLIST)
     Call<responseChecklist> getChecklist(@Body requestChecklist request);
}
