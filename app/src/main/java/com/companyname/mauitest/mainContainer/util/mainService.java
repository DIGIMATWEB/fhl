package com.companyname.mauitest.mainContainer.util;

import com.companyname.mauitest.Retrofit.RetrofitEndPoints;
import com.companyname.mauitest.login.model.requestLogin;
import com.companyname.mauitest.login.model.responseLogin;
import com.companyname.mauitest.mainContainer.model.requestMenuItems;
import com.companyname.mauitest.mainContainer.model.responseMenuItems;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface mainService {
    @POST(RetrofitEndPoints.MENUS)
    Call<responseMenuItems> getMenus(@Body requestMenuItems request);
}
