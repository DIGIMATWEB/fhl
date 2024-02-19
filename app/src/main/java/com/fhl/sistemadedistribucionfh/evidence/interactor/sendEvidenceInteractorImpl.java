package com.fhl.sistemadedistribucionfh.evidence.interactor;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.dataApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.requestRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRateData;
import com.fhl.sistemadedistribucionfh.evidence.util.serviceEvidence;

import java.io.File;
import java.util.List;

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
    public void requestEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles) {
      //  Toast.makeText(context, "mandarEvidencias", Toast.LENGTH_SHORT).show();
        if(secuenceRequest==1){
            uploadFile(signatureBase64,1,inputTextSignature);
        }else if(secuenceRequest==2){
            uploadFile(currusel,2, "test");
        }else if(secuenceRequest==3){
            uploadFile(ffiles,3, "test");
        }else if(secuenceRequest==4){
            presenter.nextRequest();
        }else{

        }
        //uploadFile();
    }



    private void uploadFile(String filePath, Integer type, String Text) {
        // Create a file object
        File file = new File(filePath);

        // Create request body for file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part listaArchivos = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);

        // Other request parameters
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), "00000168");
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(type));
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), Text);

//        SharedPreferences preferences =context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
//        String token = preferences.getString(GeneralConstants.TOKEN, null);
        // Authorization header
        String authorization = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNzA4NDAxNDczfQ.H6UCwNAbAf30z7mcIZhU6vxCfOsqxcwFIpLCTXWcMw0";

        // Call the uploadFile method in Retrofit service
        Call<ApiResponse> call = service.uploadFile(authorization, folioObjeto, tipoEvidencia, listaArchivos, usuario);

//        Log.e("sendEvidence", "URL: " + call.request().url());
//        Log.e("sendEvidence", "Headers: " + call.request().headers());
//        Log.e("sendEvidence", "Headers: " + call.request().headers());

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("sendEvidence",""+response.body());
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null && !apiResponse.getData().isEmpty()) {
                        List<dataApiResponse> data = apiResponse.getData();
                        if (data != null && !data.isEmpty()) {
                            for (dataApiResponse item : data) {
                                InnerData innerData = item.getData();
                                // Handle the response data as needed
                                if (innerData != null) {
                                    Log.e("sendEvidence", "" + innerData.getDocumentoId());
                                }
                            }
                        }
                        presenter.nextRequest();
                    }
                } else {
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("sendEvidence",""+t.getMessage());
               // presenter.nextRequest();
            }
        });
    }
    @Override
    public void sendRate(Integer stars) {
        String authorization = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNzA4NDAxNDczfQ.H6UCwNAbAf30z7mcIZhU6vxCfOsqxcwFIpLCTXWcMw0";

        requestRate request= new requestRate("m120",6,stars);
        Call<responseRate> call =service.sendRate(authorization,request);
        call.enqueue(new Callback<responseRate>() {
            @Override
            public void onResponse(Call<responseRate> call, Response<responseRate> response) {
                Log.e("ratingStars",""+response.body());
              if(response.body().getStatus()==200){
                 Toast.makeText(context, ""+response.body().getData().getEncuestaOperadorPickup(), Toast.LENGTH_SHORT).show();
                  presenter.nextRequest();
              }else{
                  Toast.makeText(context,""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }
            }

            @Override
            public void onFailure(Call<responseRate> call, Throwable t) {
                Toast.makeText(context, "Rate failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
