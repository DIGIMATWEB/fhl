package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetDatosValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetDatosValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseSendChecklist;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface serviceSetValidacionManifest {
    @POST(RetrofitEndPoints.SETVALIDACION)
    Call<responseSetValidacion> getValidacion(@Header("Authorization") String authorizationHeader, @Body requestSetValidacion request );

    @Multipart
    @POST(RetrofitEndPoints.SET_DATOS_VALIDADOR)
    Call<responseSetDatosValidador> setDatosValidador(
            @Header("Authorization") String authorization,
            @Part("folioDespacho") RequestBody folioDespacho,
            @Part("vehicleVIM") RequestBody vehicleVIM,
            @Part("rfc") RequestBody rfc,
            @Part("habilidadesOperador") RequestBody habilidadesOperador,
            @Part("habilidadesVehiculo") RequestBody habilidadesVehiculo,
            @Part("user") RequestBody user
    );
}
