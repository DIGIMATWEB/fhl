package com.fhl.sistemadedistribucionfh.Cancelar.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Cancelar.model.dataTicketNoEntregado;
import com.fhl.sistemadedistribucionfh.Cancelar.model.responseTicketNoEntregado;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Cancelar.presenter.cancelPresenterImpl;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.dataApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.dataStatusManifestTicket;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.responseStatusManifestOrTicket;
import com.fhl.sistemadedistribucionfh.evidence.util.serviceEvidence;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class cancelInteractorImpl implements cancelInteractor{
    private cancelPresenterImpl presenter;
    private Context context;
    private serviceEvidence service;
    private Retrofit retrofitClient;
    public cancelInteractorImpl(cancelPresenterImpl presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceEvidence.class);
    }

    @Override
    public void sendEvidences(List<String> directories, String folioTicket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null) {
            uploadFiles(directories, 2, "test", token,folioTicket);
        }
    }

    @Override
    public void changemStatusManifestTicket(String currentManifest, String folioTicket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        RequestBody currentManifestR = null;
        RequestBody statusDespachoR = null;
        RequestBody changeStatusTicketR = RequestBody.create(MediaType.parse("text/plain"), folioTicket);
        RequestBody statusTicketR = RequestBody.create(MediaType.parse("text/plain"),String.valueOf( 5));

        Call<responseStatusManifestOrTicket> call= service.setEstatusByManifiestoOrTicket(token,currentManifestR,statusDespachoR,changeStatusTicketR,statusTicketR);
        Log.e("changeStatus",currentManifestR+" "+statusDespachoR+" "+changeStatusTicketR+"  "+statusTicketR);

        call.enqueue(new Callback<responseStatusManifestOrTicket>() {
            @Override
            public void onResponse(Call<responseStatusManifestOrTicket> call, Response<responseStatusManifestOrTicket> response) {
                validateChangeStatus(response,context);
            }

            @Override
            public void onFailure(Call<responseStatusManifestOrTicket> call, Throwable t) {
                Log.e("changeStatus","changemStatusManifestTicket changeStatus "+t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.okChangeStatus();
            }
        });
    }


    private void validateChangeStatus(Response<responseStatusManifestOrTicket> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseStatuscheck(response,context);
        } else {
            Log.e("changeStatus","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            presenter.okChangeStatus();
        }
    }
    private void responseStatuscheck(Response<responseStatusManifestOrTicket> response, Context context) {
        responseStatusManifestOrTicket resp=response.body();
        if(resp!=null)
        {
            Log.e("changeStatus", "response not null");
            int responseCode=resp.getStatus();
            String message=resp.getMessage();
            dataStatusManifestTicket data= resp.getData();
            if(resp.getTotalRows()< 0) {
                Log.e("changeStatus", "105");
                if (data != null) {
                    //Toast.makeText(context, "Folio sendTrip: " + , Toast.LENGTH_SHORT).show();
                    Log.e("changeStatus", "Folio status: " + resp.getMessage());
                    presenter.okChangeStatus();

                } else {
                    Log.e("changeStatus", "Folio status: " + data.getEstatusDespacho().getFolioDespacho());
                    presenter.okChangeStatus();
                    Log.e("changeStatus", "no  105");
                }
            }else{
                presenter.okChangeStatus();
            }
        }else{
            presenter.okChangeStatus();
            Log.e("changeStatus","resp null");
        }

    }

    private void uploadFiles(List<String> filePaths, Integer type, String Text, String token, String folioTicket) {

        List<MultipartBody.Part> filesParts = new ArrayList<>();
        for (String filePath : filePaths) {
            File file = new File(filePath); // Trim each file path to remove leading/trailing whitespace
            if (file.exists()) { // Check if the file exists before proceeding
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);
                filesParts.add(filePart);
            } else {
                Log.e("sendEvidence", "File does not exist: " + filePath);
            }
        }

        // Other parameters remain the same
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), folioTicket);
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(type));
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), Text);
        // Authorization header remains the same
        String authorization = token;//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNzA4NTAxMjY3fQ.hOqMzu1zK115a1Z739waaju9e3Co4dubb3bpYneUuAg";

        // Call the uploadFiles method in Retrofit service
        Call<ApiResponse> call = service.uploadFiles(authorization, folioObjeto, tipoEvidencia, filesParts, usuario,3);

        Gson gson = new Gson();

        // Convert the string to JSON
        String jsonString = gson.toJson(call.request().body().toString());
        Log.e("sendEvidence",""+jsonString);
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
                        }
                        presenter.okSendEvidence();

                    }
                    presenter.hideDialog();
                } else {
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("sendEvidence","onFailure "+t.getMessage());
               // Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.okSendEvidence();
                presenter.hideDialog();
            }
        });
    }
    @Override
    public void setTicketNoEntregado(String currentManifest, String folioTicket, Integer idReason, String name) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        RequestBody currenmanifest = RequestBody.create(MediaType.parse("text/plain"), currentManifest);
        RequestBody folioTicketr = RequestBody.create(MediaType.parse("text/plain"), folioTicket);
        RequestBody idReasonr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idReason));
        RequestBody namer = RequestBody.create(MediaType.parse("text/plain"), name);

        Call<responseTicketNoEntregado> call=service.setTicketNoEntregado(token,currenmanifest,folioTicketr,idReasonr,namer);
        call.enqueue(new Callback<responseTicketNoEntregado>() {
            @Override
            public void onResponse(Call<responseTicketNoEntregado> call, Response<responseTicketNoEntregado> response) {
                validateticketStatus(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketNoEntregado> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.nextRequest();
            }
        });
    }

    private void validateticketStatus(Response<responseTicketNoEntregado> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseNoEntregadocheck(response,context);
        } else {
            Log.e("changeStatus","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            presenter.nextRequest();
        }
    }

    private void responseNoEntregadocheck(Response<responseTicketNoEntregado> response, Context context) {
         responseTicketNoEntregado resp=response.body();
                if(resp!=null)
                {
                    Log.e("changeStatus", "response not null");
                    int responseCode=resp.getStatus();
                    String message=resp.getMessage();
                    dataTicketNoEntregado data= resp.getData();
                    if(message.equals("Ejecución completada correctamente")) {
                        Log.e("changeStatus", "105");
                        if (data != null) {
                            //Toast.makeText(context, "Folio sendTrip: " + , Toast.LENGTH_SHORT).show();
                            Log.e("changeStatus", "Folio status: " + resp.getMessage());
                            presenter.nextRequest();

                        } else {

                            presenter.nextRequest();
                            Log.e("changeStatus", "no  105");
                        }
                    }else{
                        presenter.nextRequest();
                    }
                }else{
                    presenter.nextRequest();
                    Log.e("changeStatus","resp null");
                }
    }

}
