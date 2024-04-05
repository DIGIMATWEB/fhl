package com.fhl.sistemadedistribucionfh.evidence.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientAvocado;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.dataApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.model.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.requestRate;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.model.responseRate;
import com.fhl.sistemadedistribucionfh.evidence.util.serviceEvidence;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.dataAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.requestLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.responseLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.SendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.Trip;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.Data;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.ResponseSendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.util.serviceSendtripPlus;
import com.google.gson.Gson;

import java.io.File;
import java.time.LocalTime;
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
    private serviceSendtripPlus service2;
    private Retrofit retrofitClient,retrofitClientV2;
    private String ftoken;
    private Integer flujo;
    private String ticket;
    public sendEvidenceInteractorImpl(requestEvidencePresenter presenter,Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceEvidence.class);

        retrofitClientV2= RetrifitClientAvocado.getRetrofitInstance();
        service2 = retrofitClientV2.create(serviceSendtripPlus.class);

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
                presenter.showDialog();
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
                                    presenter.hideDialog();
                                }
                                presenter.nextRequest();
                            }
                        }else {
                            Log.e("sendEvidence", "data is empty o null 1");
                            presenter.hideDialog();
                        }

                    }else{
                        Log.e("sendEvidence", "apiresponse null 1");
                        presenter.hideDialog();
                    }
                } else {
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String jsonString = gson.toJson(call.request().body());
                Log.e("sendEvidence",""+t.getMessage()+"request  "+jsonString);
               // presenter.nextRequest();
                presenter.hideDialog();
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
                                    presenter.hideDialog();
                                }
                                presenter.nextRequest();
                            }
                        }else {
                            Log.e("sendEvidence", "data is empty o null");
                            presenter.hideDialog();
                        }

                    }else{
                        Log.e("sendEvidence", "apiresponse null");
                        presenter.hideDialog();
                    }
                } else {
                    Log.e("sendEvidence", "File upload failed "+response.body());
                    Toast.makeText(context, "File upload failed", Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("sendEvidence",""+t.getMessage());
                presenter.hideDialog();
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
        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        Log.e("sendRate","request  "+jsonString+" token "+ftoken);
        call.enqueue(new Callback<responseRate>() {
            @Override
            public void onResponse(Call<responseRate> call, Response<responseRate> response) {

              if(response.body().getStatus()==200){
               //  Toast.makeText(context, ""+response.body().getData().getEncuestaOperadorPickup(), Toast.LENGTH_SHORT).show();
                //  Log.e("ratingStars",""+response.body().getData().getEncuestaOperadorPickup());
                  presenter.nextRequest();

              }else{
                 // Toast.makeText(context,""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                  Log.e("ratingStars",""+response.body().getMessage());

                  presenter.hideDialog();
              }
            }

            @Override
            public void onFailure(Call<responseRate> call, Throwable t) {
               Toast.makeText(context, "Rate failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ratingStars","1"+t.getMessage());
                //presenter.hideDialog();
                presenter.nextRequest();
            }
        });
    }
    @Override
    public void tokenAvocado() {
        requestLoginAvocado request= new requestLoginAvocado("efren","efrenw");
        Call<responseLoginAvocado> call= service2.loginAvocado(request);
        call.enqueue(new Callback<responseLoginAvocado>() {
            @Override
            public void onResponse(Call<responseLoginAvocado> call, Response<responseLoginAvocado> response) {
                validateOriginresponse(response,context);
              
            }

            @Override
            public void onFailure(Call<responseLoginAvocado> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

   

    private void validateOriginresponse(Response<responseLoginAvocado> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseOirgin(response,context);
        } else {
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    private void responseOirgin(Response<responseLoginAvocado> response, Context context) {
        responseLoginAvocado responseOrigin=response.body();
        if(responseOrigin!=null)
        {
            int responseCode=responseOrigin.getResponseCode();
            String message=responseOrigin.getMessage();
            dataAvocado data= responseOrigin.getData();
            if(responseCode==100)
            {

                if(data!=null) {
                    SharedPreferences preferencias = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString(GeneralConstants.TOKEN_AVOCADO, String.valueOf( data.getToken()));
                   // editor.putString(GeneralConstants.CVE_EMPLOYE,String.valueOf(data.getCve_employee()));
                    editor.commit();
                    Log.e("token"," token avocado: "+data.getToken());
                    presenter.validateSendtrip();

                }
            }else{
            }
        }else{
        }
    }

    @Override
    public void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token_Avocado = preferences.getString(GeneralConstants.TOKEN_AVOCADO, null);
        String operadorId = preferences.getString(GeneralConstants.OPERADOR_NAME, null);
        if(token_Avocado!=null){
            requestSendtriplus(token_Avocado,dataTicketSendtrip.get(0),operadorId,currentManifest,sentripPlusFlow);
            Log.e("sendtripplus","requestSendtriplus");
        }
    }

    private void requestSendtriplus(String token_Avocado, dataTicketsDetailsendtrip data, String operadorId, String currentManifest, String sentripPlusFlow) {
        Trip mtrip= new Trip("",0,"","",""
                ,"","",0,0,
                new ArrayList<>(),"","","","","","",
                0,0,"",
                "","","","",sentripPlusFlow,"","","",
                "","","","","","","");
        mtrip.setComments(currentManifest);
        mtrip.setOrderFolio(Integer.valueOf(data.getFolioTicket()));//mtrip.setOrderFolio(0);
        mtrip.setOrderDriver(operadorId);//mtrip.setOrderDriver("WALMART");
        mtrip.setOrderTimestampD(data.getSendtripPlus().getFechaPromesaEntrega());//mtrip.setOrderTimestampD("2024-04-02T17:41:44.152Z");
        mtrip.setOrderTimestampIntervalE(data.getSendtripPlus().getFechaVentanaFin());//mtrip.setOrderTimestampIntervalE("2024-04-02T17:41:44.152Z");
        mtrip.setOrderTimestampIntervalS(data.getSendtripPlus().getFechaVentanaInicio());//mtrip.setOrderTimestampIntervalS("2024-04-02T17:41:44.152Z");
        mtrip.setOrderTimestampO(data.getSendtripPlus().getFechaPromesaCarga());//mtrip.setOrderTimestampO("2024-04-02T17:41:44.152Z");
        int roundedMinutes=0;
        if(data.getSendtripPlus().getTiempoCarga()!=null) {
            LocalTime time = LocalTime.parse(data.getSendtripPlus().getTiempoCarga());
            long totalMinutes = time.getHour() * 60 + time.getMinute() + time.getSecond() / 60;
            roundedMinutes= (int) Math.round(totalMinutes);
        }else{

        }
        mtrip.setOrderUploadingTime(roundedMinutes);//mtrip.setOrderUploadingTime(0);//todo revisar este campo
        mtrip.setPackageCounts(data.getSendtripPlus().getCantidadPaquetes());//mtrip.setPackageCounts(0);
        mtrip.setRecipientCompanyname(data.getSendtripPlus().getDestinatario().getCompania());//mtrip.setRecipientCompanyname("NEWGEODESTINO");//todo revisar
        mtrip.setRecipientPostalcode(String.valueOf(data.getSendtripPlus().getDestinatario().getCodigoPostal()));//mtrip.setRecipientPostalcode("00000");//todo validar
        mtrip.setShipperCompanyname(data.getSendtripPlus().getRemitente().getCompania());//mtrip.setShipperCompanyname("NEWGEOORIGEN");//todo revisar alan
        mtrip.setShipperPostalcode(String.valueOf(data.getSendtripPlus().getRemitente().getCodigoPostal()));//mtrip.setShipperPostalcode("00000");
        mtrip.setVehicleName(data.getVehiculo().getEconomico());//mtrip.setVehicleName("NLA-003YF2");//todo economico
        mtrip.setVehiclePlate(data.getVehiculo().getPlaca());//mtrip.setVehiclePlate("NLA-003YF2");//todo placa
        SendTripPlus request= new SendTripPlus(token_Avocado,mtrip);
        Call<ResponseSendTripPlus> call= service2.setSendtriplus(request);
        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        Log.e("sendtripplus","sendtriplus: "+ jsonString);
        call.enqueue(new Callback<ResponseSendTripPlus>() {
            @Override
            public void onResponse(Call<ResponseSendTripPlus> call, Response<ResponseSendTripPlus> response) {
                validateSendtriplus(response,context);

            }

            @Override
            public void onFailure(Call<ResponseSendTripPlus> call, Throwable t) {
                Log.e("sendtripplus","onFailure");
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.nextRequest();
            }
        });
    }

    private void validateSendtriplus(Response<ResponseSendTripPlus> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseSendtrip(response,context);
        } else {
            Log.e("sendtripplus","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            presenter.nextRequest();
        }
    }

    private void responseSendtrip(Response<ResponseSendTripPlus> response, Context context) {
        ResponseSendTripPlus resp=response.body();
        if(resp!=null)
        {
            int responseCode=resp.getResponseCode();
            String message=resp.getMessage();
            Data data= resp.getData();
            if(responseCode==105)
            {
                Log.e("sendtripplus","105");
                if(data!=null) {
                 Toast.makeText(context, "Folio sendTrip: "+data.getOrderFolio(), Toast.LENGTH_SHORT).show();
                    Log.e("sendtripplus","Folio sendTrip: "+data.getOrderFolio());
                  presenter.nextRequest();
                }
            }else{
                presenter.nextRequest();
                Log.e("sendtripplus","no  105");
            }
        }else{
            presenter.nextRequest();
            Log.e("sendtripplus","resp null");
        }
    }


    @Override
    public void requestDetailTicketsSendtriplus(boolean isArray, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            if(!isArray) {//todo no es uno solo es de la iteracion que le corresponde
                requestDetailforSendtriplusTicket(token, ticket, currentManifest);
            }else{//todo si es uno solo es el ticket que le corresponde
                requestDetailforSendtriplusTicket(token, folioTicket, currentManifest);
            }
        }
    }

    private void requestDetailforSendtriplusTicket(String token, String iterateidTickets, String currentManifest) {
        Call<TicketsDetailSentriplus> call= service.getTicket(token,currentManifest,iterateidTickets);
        call.enqueue(new Callback<TicketsDetailSentriplus>() {
            @Override
            public void onResponse(Call<TicketsDetailSentriplus> call, Response<TicketsDetailSentriplus> response) {
                validateDetailTicket(response,context);
            }
            @Override
            public void onFailure(Call<TicketsDetailSentriplus> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateDetailTicket(Response<TicketsDetailSentriplus> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseSendtripTicket(response,context);
        } else {
            Log.e("responseSendtripTicket","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    private void responseSendtripTicket(Response<TicketsDetailSentriplus> response, Context context) {
        TicketsDetailSentriplus resp=response.body();
        if(resp!=null)
        {
            int responseCode=resp.getStatus();
            String message=resp.getMessage();
            List<dataTicketsDetailsendtrip> data= resp.getData();
            if(responseCode==200)
            {
                Log.e("responseSendtripTicket","105");
                if(data!=null) {
                    Toast.makeText(context, "Folio sendTripDetail: "+data.get(0).getFolioTicket(), Toast.LENGTH_SHORT).show();
                    Log.e("responseSendtripTicket","Folio sendTrip: "+data.get(0).getFolioTicket());
                    presenter.setDetailTicketsentriplus(data);

                }
            }else{

                Log.e("responseSendtripTicket","no  105");
            }
        }else{
            Log.e("responseSendtripTicket","resp null");
        }
    }
}
