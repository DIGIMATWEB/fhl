package com.fhl.sistemadedistribucionfh.login.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.login.model.modelLogin.requestLogin;
import com.fhl.sistemadedistribucionfh.login.model.modelLogin.responseLogin;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface serviceLogin {
    @POST(RetrofitEndPoints.LOGIN)
    Call<responseLogin> getLogin(@Body requestLogin request);
    @GET(RetrofitEndPoints.PROFILE)
    Call<profileResponse> getData(@Header("Authorization") String authToken);
}
