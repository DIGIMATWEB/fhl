package com.fhl.sistemadedistribucionfh.evidence.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.dataApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.requestRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRate;
import com.fhl.sistemadedistribucionfh.evidence.util.serviceEvidence;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String ftoken;
    private Integer flujo;
    private String ticket;
    public sendEvidenceInteractorImpl(requestEvidencePresenter presenter,Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceEvidence.class);

    }
    @Override
    public void requestEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        this.ftoken=token;
        this.flujo=flujoId;
        this.ticket=folioTicket;
        if(ftoken!=null) {

            //  Toast.makeText(context, "mandarEvidencias", Toast.LENGTH_SHORT).show();
            if (secuenceRequest == 1) {
                uploadFile(signatureBase64, 1, inputTextSignature,token);
                //  Log.e("sendEvidence"," send signature : "+signatureBase64);
            } else if (secuenceRequest == 2) {
                uploadFiles(currusel, 2, "test",token);
            } else if (secuenceRequest == 3) {
                uploadFiles(ffiles, 3, "test", token);
            } else if (secuenceRequest == 4) {
                presenter.nextRequest();
            } else {

            }
        }
        //uploadFile();
    }



    private void uploadFile(String filePath, Integer type, String Text, String token) {
        // Create a file object
        File file = new File(filePath);

        // Create request body for file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part listaArchivos = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);

        // Other request parameters
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), ticket);
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(type));
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), Text);
        RequestBody flujoid= RequestBody.create(MediaType.parse("text/plain"), String.valueOf(flujo));
//        SharedPreferences preferences =context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
//        String token = preferences.getString(GeneralConstants.TOKEN, null);
        // Authorization header
        String authorization = token;//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNzA4NTAxMjY3fQ.hOqMzu1zK115a1Z739waaju9e3Co4dubb3bpYneUuAg";

        // Call the uploadFile method in Retrofit service
        Call<ApiResponse> call = service.uploadFile(authorization, folioObjeto, tipoEvidencia, listaArchivos, usuario,flujo);
        Gson gson = new Gson();
        String jsonString = gson.toJson("folio "+ticket+"   tipoEvidence "+ type+" listfiles "+ filePath+"  usuario " +Text +"  flow "+flujo);
        Log.e("sendEvidence","request  "+jsonString);
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
                                }else {
                                    Toast.makeText(context, ""+item.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("sendEvidence", "" + item.getMessage());
                                }

                            }
                        }else {
                            Log.e("sendEvidence", "data is empty o null 1");
                        }
                        presenter.nextRequest();
                    }else{
                        Log.e("sendEvidence", "apiresponse null 1");
                    }
                } else {
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String jsonString = gson.toJson(call.request().body());
                Log.e("sendEvidence",""+t.getMessage()+"request  "+jsonString);
               // presenter.nextRequest();
            }
        });
    }
    private void uploadFiles(String filePaths, Integer type, String Text, String token) {
        String[] filePathsArray = filePaths.split(",");
        ArrayList<String> filePathsList = new ArrayList<>(Arrays.asList(filePathsArray));

        List<MultipartBody.Part> filesParts = new ArrayList<>();
        for (String filePath : filePathsList) {
            File file = new File(filePath.trim()); // Trim each file path to remove leading/trailing whitespace
            if (file.exists()) { // Check if the file exists before proceeding
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);
                filesParts.add(filePart);
            } else {
                Log.e("sendEvidence", "File does not exist: " + filePath);
            }
        }

        // Other parameters remain the same
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), ticket);
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(type));
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), Text);
        RequestBody flujoid= RequestBody.create(MediaType.parse("text/plain"), String.valueOf(flujo));
        // Authorization header remains the same
        String authorization = token;//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNzA4NTAxMjY3fQ.hOqMzu1zK115a1Z739waaju9e3Co4dubb3bpYneUuAg";

        // Call the uploadFiles method in Retrofit service
        Call<ApiResponse> call = service.uploadFiles(authorization, folioObjeto, tipoEvidencia, filesParts, usuario,flujo);


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
                                }else{
                                    Log.e("sendEvidence", item.getMessage());
                                    Toast.makeText(context, ""+item.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            Log.e("sendEvidence", "data is empty o null");
                        }
                        presenter.nextRequest();
                    }else{
                        Log.e("sendEvidence", "apiresponse null");
                    }
                } else {
                    Log.e("sendEvidence", "File upload failed "+response.body());
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("sendEvidence",""+t.getMessage());
            }
        });
    }
    @Override
    public void sendRate(Integer stars, String folioTicket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        this.ftoken=token;


        requestRate request= new requestRate(folioTicket,6,stars);
        Call<responseRate> call =service.sendRate(ftoken,request);
        call.enqueue(new Callback<responseRate>() {
            @Override
            public void onResponse(Call<responseRate> call, Response<responseRate> response) {

              if(response.body().getStatus()==200){
               //  Toast.makeText(context, ""+response.body().getData().getEncuestaOperadorPickup(), Toast.LENGTH_SHORT).show();
                  Log.e("ratingStars",""+response.body().getData().getEncuestaOperadorPickup());
                  presenter.nextRequest();
              }else{
                 // Toast.makeText(context,""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                  Log.e("ratingStars",""+response.body().getMessage());
              }
            }

            @Override
            public void onFailure(Call<responseRate> call, Throwable t) {
              //  Toast.makeText(context, "Rate failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ratingStars",""+t.getMessage());
            }
        });
    }
}
