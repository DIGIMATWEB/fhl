package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.util;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.requestReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.responseReasons;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface serviceDialogReasons {
      @GET(RetrofitEndPoints.REASONS_SGD)
      Call<responseReasons> getReasons(@Header("Authorization")String token);
}
