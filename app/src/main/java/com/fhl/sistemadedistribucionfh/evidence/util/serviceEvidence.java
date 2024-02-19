package com.fhl.sistemadedistribucionfh.evidence.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.requestRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRateData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface serviceEvidence {
    @Multipart
    @POST("SetPickupEvidencia")
    Call<ApiResponse> uploadFile(
            @Header("Authorization") String authorization,
            @Part("FolioObjeto") RequestBody folioObjeto,
            @Part("TipoEvidencia") RequestBody tipoEvidencia,
            @Part MultipartBody.Part listaArchivos,
            @Part("Usuario") RequestBody usuario
    );
    @POST(RetrofitEndPoints.RATE_STARS)
    Call<responseRate> sendRate(@Header("Authorization") String authorization,@Body requestRate request);
}
