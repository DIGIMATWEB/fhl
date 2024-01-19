package com.fhl.sistemadedistribucionfh.mainContainerV2.utilV2;

import android.content.Context;
import android.content.SharedPreferences;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.responseMenuItemsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface mainServiceV2 {
    @GET(RetrofitEndPoints.MENUSFH + "{param1}/" + "{param2}")
    Call<responseMenuItemsV2> getMenusV2(@Path("param1") int param1, @Path("param2") int param2, @Header("Authorization") String authorizationHeader);
}
