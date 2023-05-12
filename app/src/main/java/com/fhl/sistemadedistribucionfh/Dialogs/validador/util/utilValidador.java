package com.fhl.sistemadedistribucionfh.Dialogs.validador.util;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.requestValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.responseValidador;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface utilValidador {
     @POST(RetrofitEndPoints.VALIDADOR)
     Call<responseValidador> getDespachos(@Body requestValidador request);
}
