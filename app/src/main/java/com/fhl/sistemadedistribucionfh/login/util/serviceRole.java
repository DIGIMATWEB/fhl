package com.fhl.sistemadedistribucionfh.login.util;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriver;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.login.model.roles.responseUserRole;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface serviceRole {
    @GET(RetrofitEndPoints.HABILITIES_DRIVERLIST)
    Call<responseUserRole> getRole(@Header("Authorization") String authorizationHeader,
                                         @Path("id") Integer OperadorID);
}
