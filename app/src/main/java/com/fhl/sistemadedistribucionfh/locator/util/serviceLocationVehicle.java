package com.fhl.sistemadedistribucionfh.locator.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.locator.model.requestVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.model.responseVehicleLocation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface serviceLocationVehicle {

     @POST(RetrofitEndPoints.LOCATION)
     Call<responseVehicleLocation> getVehicles(@Header("Authorization") String authorizationHeader, @Body requestVehicleLocation request);
    //     );
}
