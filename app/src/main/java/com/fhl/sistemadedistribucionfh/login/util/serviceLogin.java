package com.fhl.sistemadedistribucionfh.login.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.login.model.requestLogin;
import com.fhl.sistemadedistribucionfh.login.model.responseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceLogin {
    @POST(RetrofitEndPoints.LOGIN)
    Call<responseLogin> getLogin(@Body requestLogin request);
}
