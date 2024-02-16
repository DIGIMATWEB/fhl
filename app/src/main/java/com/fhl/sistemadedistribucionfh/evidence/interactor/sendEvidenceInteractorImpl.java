package com.fhl.sistemadedistribucionfh.evidence.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util.serviceSalida;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.util.serviceEvidence;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class sendEvidenceInteractorImpl implements sendEvidenceInteractor{

    private Context context;
    private requestEvidencePresenter presenter;
    private serviceEvidence service;
    private Retrofit retrofitClient;
    public sendEvidenceInteractorImpl(requestEvidencePresenter presenter,Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientPep.getRetrofitInstance();
        service = retrofitClient.create(serviceEvidence.class);

    }
    @Override
    public void requestEvidence() {
        Toast.makeText(context, "mandarEvidencias", Toast.LENGTH_SHORT).show();
        //uploadFile();
    }
    private void uploadFile(String filePath) {
        // Create a file object
        File file = new File(filePath);

        // Create request body for file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part listaArchivos = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);

        // Other request parameters
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), "00000168");
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), "2");
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), "texto");

        SharedPreferences preferences =context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        // Authorization header
        String authorization = token;

        // Call the uploadFile method in Retrofit service
        Call<ApiResponse> call = service.uploadFile(authorization, folioObjeto, tipoEvidencia, listaArchivos, usuario);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null && !apiResponse.getData().isEmpty()) {
                        InnerData innerData = apiResponse.getData().get(0).getData();
                        // Handle the response data as needed
                        Log.e("uploader",""+innerData.getDocumentoId());
                        Toast.makeText(context, "File uploaded successfully: " + innerData.getDocumentoId(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
