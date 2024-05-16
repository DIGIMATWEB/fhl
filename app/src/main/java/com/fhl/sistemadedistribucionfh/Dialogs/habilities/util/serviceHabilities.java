package com.fhl.sistemadedistribucionfh.Dialogs.habilities.util;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriverHabilities;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.responseVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.responseVehicleHabilities;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface serviceHabilities {
    @GET(RetrofitEndPoints.HABILITIES_VEHICLELIST)//lista de informacion del vehiculo
    Call<responseVehicle> getVehicleInfor(@Header("Authorization") String authorizationHeader,
                                          @Path("id") Integer vehiculoId );
    @GET(RetrofitEndPoints.HABILITIES_VEHICLE)//se obtienen los check a partir de  los
    Call<responseVehicleHabilities> getVehicleCheck(@Header("Authorization") String authorizationHeader,
                                                    @Path("id") Integer vehiculoId );

    @GET(RetrofitEndPoints.HABILITIES_DRIVERLIST)
    Call<responseDriver> getDriverInfo(@Header("Authorization") String authorizationHeader,
                                       @Path("id") Integer vehiculoId);

    @GET(RetrofitEndPoints.HABILITIES_DRIVER)
    Call<responseDriverHabilities> getDriverCheck(@Header("Authorization") String authorizationHeader,
                                                  @Path("id") Integer vehiculoId);



}
