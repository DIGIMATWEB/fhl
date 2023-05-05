package com.companyname.mauitest.checkList.util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.checkList.model.requestChecklist;
import com.companyname.mauitest.checkList.model.responseChecklist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceChecklist {
     @POST(RetrofitEndPoints.CHECKLIST)
     Call<responseChecklist> getChecklist(@Body requestChecklist request);
}
