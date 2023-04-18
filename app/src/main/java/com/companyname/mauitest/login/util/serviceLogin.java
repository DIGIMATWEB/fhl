package com.companyname.mauitest.login.util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.login.model.requestLogin;
import com.companyname.mauitest.login.model.responseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceLogin {
    @POST(RetrofitEndPoints.LOGIN)
    Call<responseLogin> getLogin(@Body requestLogin request);
}
