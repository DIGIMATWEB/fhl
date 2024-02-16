package com.fhl.sistemadedistribucionfh.evidence.util;

import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
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
}
