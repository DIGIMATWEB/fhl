package com.fhl.sistemadedistribucionfh.mainContainer.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.mainContainer.model.requestMenuItems;
import com.fhl.sistemadedistribucionfh.mainContainer.model.responseMenuItems;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface mainService {
    @POST(RetrofitEndPoints.MENUS)
    Call<responseMenuItems> getMenus(@Body requestMenuItems request);
}
