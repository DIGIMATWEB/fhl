package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.responseVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.util.serviceHabilities;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.Validadorset;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetDatosValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util.serviceSetValidacionManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientAvocado;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.dataAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.requestLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado.responseLoginAvocado;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.SendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.request.Trip;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.Data;
import com.fhl.sistemadedistribucionfh.sendTripPlus.model.response.ResponseSendTripPlus;
import com.fhl.sistemadedistribucionfh.sendTripPlus.util.serviceSendtripPlus;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorSetValidacionImpl implements interactorSetValidacion{
    private presenterSetValidacion presenter;
    private Context context;
    private serviceSetValidacionManifest service;
    private Retrofit retrofitClient,retrofitClientV2;
    private Retrofit retrofit;
    private serviceHabilities service2;
    private serviceSendtripPlus service3;
    private Integer iteration;

    public interactorSetValidacionImpl(presenterSetValidacion presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceSetValidacionManifest.class);

        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service2= retrofit.create(serviceHabilities.class);

        retrofitClientV2= RetrifitClientAvocado.getRetrofitInstance();
        service3 = retrofitClientV2.create(serviceSendtripPlus.class);
    }

    @Override
    public void setValidacionMenifest(String manifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestValidacionManifest(manifest,token);
        }
    }



    private void requestValidacionManifest(String manifest, String token) {
       Validadorset newset=new Validadorset("","Correcto");
       requestSetValidacion request = new requestSetValidacion(manifest,newset);
       Call<responseSetValidacion> call= service.getValidacion(token,request);
       call.enqueue(new Callback<responseSetValidacion>() {
           @Override
           public void onResponse(Call<responseSetValidacion> call, Response<responseSetValidacion> response) {
               validateSetValidacionManifest(response, context);
           }

           @Override
           public void onFailure(Call<responseSetValidacion> call, Throwable t) {
               presenter.setresponseValidacionMenifest("401",t.getMessage().toString());

           }
       });

    }

    private void validateSetValidacionManifest(Response<responseSetValidacion> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidacion(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    // presenter.returnTologin();
                    presenter.setresponseValidacionMenifest("401", response.message());
                }
            }
        }
    }

    private void setValidacion(Response<responseSetValidacion> response, Context context) {
         responseSetValidacion resp = response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                        presenter.setresponseValidacionMenifest("105", resp.getMessage());

                    } else {
                        presenter.setresponseValidacionMenifest("107", resp.getMessage());
                    }
                } else {

                    presenter.setresponseValidacionMenifest("401", response.message().toString());
                }
    }
    @Override
    public void getDrirver() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String operador= preferences.getString(GeneralConstants.OPERADOR_ID,null);
        Log.e("habilidades","operador "+operador);
        if(token!=null){
            goGetDriver(token,operador);
        }
    }

    private void goGetDriver(String token, String operador) {
        Call<responseDriver> call= service2.getDriverInfo(token,Integer.valueOf(operador));
        call.enqueue(new Callback<responseDriver>() {
            @Override
            public void onResponse(Call<responseDriver> call, Response<responseDriver> response) {
                validategoGetDriver(response, context);
            }

            @Override
            public void onFailure(Call<responseDriver> call, Throwable t) {
                Log.e("habilidades",""+t.getMessage());
            }
        });
    }

    private void validategoGetDriver(Response<responseDriver> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidaciongoGetDriver(response, context);
            } else {
                Log.e("habilidades",""+response.code());
            }
        }
    }

    private void setValidaciongoGetDriver(Response<responseDriver> response, Context context) {
        responseDriver resp= response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {
                presenter.setDriverHailities(resp.getData().getHabilidades());
            } else {
               Log.e("habilidades",""+message);
            }
        } else {
            Log.e("habilidades","respuesta nula");
        }
    }

    @Override
    public void getVehicle(Integer claveVehicleID) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String vehiculo= preferences.getString(GeneralConstants.VEHICLEID,null);
        Log.e("habilidades","vehiculo "+claveVehicleID);
        if(token!=null){
            goGetVehicle(token,claveVehicleID);
        }
    }

    private void goGetVehicle(String token, Integer vehiculo) {
        Call<responseVehicle> call = service2.getVehicleInfor(token, vehiculo);
        call.enqueue(new Callback<responseVehicle>() {
            @Override
            public void onResponse(Call<responseVehicle> call, Response<responseVehicle> response) {
                validategoGetVehicle(response, context);
            }

            @Override
            public void onFailure(Call<responseVehicle> call, Throwable t) {
                Log.e("habilidades",""+t.getMessage());
            }
        });
    }

    private void validategoGetVehicle(Response<responseVehicle> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidaciongoGetVehicle(response, context);
            } else {
                Log.e("habilidades",""+response.code());
            }
        }
    }

    private void setValidaciongoGetVehicle(Response<responseVehicle> response, Context context) {
        responseVehicle resp= response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {
                        presenter.setDriverVehicle(resp.getData().getHabilidadVehiculos());
                    } else {
                       Log.e("habilidades",""+message);
                    }
                } else {
                    Log.e("habilidades","respuesta nula");
                }
    }
    @Override
    public void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        //String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);

        if(token!=null){
            requestDatosValidador(token, manifest, vehicleVin, rfcUser, jsonHabDriver, jsonHabVehicles, user);
        }
    }


    private void requestDatosValidador(String token, String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user) {
        //Validadorset newset=new Validadorset("","Correcto");
        // RequestBody
        RequestBody FolioDespacho = null;
        RequestBody VehicleVIM = null;
        RequestBody RFC = null;
        RequestBody HabilidadesOperador = null;
        RequestBody HabilidadesVehiculo = null;
        RequestBody User = null;

        // RequestBody con variables
        FolioDespacho = RequestBody.create(MediaType.parse("text/plain"), manifest);
        VehicleVIM = RequestBody.create(MediaType.parse("text/plain"), vehicleVin);
        RFC = RequestBody.create(MediaType.parse("text/plain"), rfcUser);
        HabilidadesOperador = RequestBody.create(MediaType.parse("text/plain"), jsonHabDriver);
        HabilidadesVehiculo = RequestBody.create(MediaType.parse("text/plain"), jsonHabVehicles);
        User = RequestBody.create(MediaType.parse("text/plain"), user);

        //requestSetDatosValidador request = new requestSetDatosValidador(manifest,vehicleVin, rfcUser, jsonHabDriver, jsonHabVehicles, user);
        Call<responseSetDatosValidador> call= service.setDatosValidador(token,FolioDespacho, VehicleVIM, RFC, HabilidadesOperador, HabilidadesVehiculo, User);
        call.enqueue(new Callback<responseSetDatosValidador>() {
            @Override
            public void onResponse(Call<responseSetDatosValidador> call, Response<responseSetDatosValidador> response) {
                Log.e("SendTicket: ", "Response: " + response);
                validateResponseValidador(response, context);
            }

            @Override
            public void onFailure(Call<responseSetDatosValidador> call, Throwable t) {
                //presenter.setresponseValidacionMenifest("401");
                Log.e("SendTicket: ", "Response: " + t.getMessage() + t.getLocalizedMessage());
            }
        });
    }

    private void validateResponseValidador(Response<responseSetDatosValidador> response, Context context) {
        responseSetDatosValidador responseSetDatosValidador = response.body();

        if(responseSetDatosValidador!=null) {
            Integer responseCode = responseSetDatosValidador.getStatus();

            if(responseCode == GeneralConstants.RESPONSE_CODE_OK_FH) {
                String message = responseSetDatosValidador.getMessage();

                Log.e("SendTicket: ", "Response: " + message);
            } else {
                Log.e("SendTicket: ", "Response: " + response.message());
            }
        } else {
            Log.e("SendTicket: ", "Response: ");
        }
    }
    @Override
    public void requestTicketsByManifest(String manifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            Call<TicketsDetailSentriplus> call= service.getTicket(token,manifest,null);
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
    }


    private void validateDetailTicket(Response<TicketsDetailSentriplus> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseSendtripTicket(response,context);
        } else {
            Log.e("responseSendtripTicket","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    private void responseSendtripTicket(Response<TicketsDetailSentriplus> response, Context context) {//todo metodo para obetener el detalle de ticket
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
                    Gson gson=new Gson();
                    String json= gson.toJson(data);
                    Log.e("empaque","json: "+json);
                    //Toast.makeText(context, "Folio sendTripDetail: "+data.get(0).getFolioTicket(), Toast.LENGTH_SHORT).show();
                    Log.e("responseSendtripTicket","Folio sendTrip: "+data.get(0).getFolioTicket());
                    presenter.setDetailTickets(data);

                }
            }else{

                Log.e("responseSendtripTicket","no  105");
            }
        }else{
            Log.e("responseSendtripTicket","resp null");
        }
    }
    @Override
    public void tokenAvocado() {
        requestLoginAvocado request= new requestLoginAvocado("fhl_api_sendtrip_sgd","0LAsCUmZ");//"efren","efrenw");
        Call<responseLoginAvocado> call= service3.loginAvocado(request);
        call.enqueue(new Callback<responseLoginAvocado>() {
            @Override
            public void onResponse(Call<responseLoginAvocado> call, Response<responseLoginAvocado> response) {
                validateOriginresponse(response,context);

            }

            @Override
            public void onFailure(Call<responseLoginAvocado> call, Throwable t) {
                Toast.makeText(context, "token request Fail"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void validateOriginresponse(Response<responseLoginAvocado> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseOirgin(response,context);
        } else {
            // Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
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


                }
            }else{
            }
        }else{
        }
    }
    @Override
    public void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow,Integer iteration) {
        this.iteration=iteration;
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token_Avocado = preferences.getString(GeneralConstants.TOKEN_AVOCADO, null);
        String operadorId = preferences.getString(GeneralConstants.OPERADOR_NAME, null);
        if(token_Avocado!=null){

            requestSendtriplus(token_Avocado,
                    dataTicketSendtrip.get(iteration),
                    operadorId,
                    currentManifest,
                    sentripPlusFlow);
            Log.e("sendtripplus","requestSendtriplus");
        }
    }

    private void requestSendtriplus(String token_Avocado, dataTicketsDetailsendtrip data, String operadorId, String currentManifest, String sentripPlusFlow) {
        Trip mtrip= new Trip("",0,"","",""
                ,"","",0,0,
                new ArrayList<>(),"","","","","","",
                0.0,0.0,"",
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
        mtrip.setRecipientCompanyname(data.getCliente().getRazonSocial()+
                "-"+data.getCliente().getAxaptaId()+
                "-"+data.getSendtripPlus().getDestinatario().getCompania());//mtrip.setRecipientCompanyname("NEWGEODESTINO");//todo revisar
        mtrip.setRecipientPostalcode(String.valueOf(data.getSendtripPlus().getDestinatario().getCodigoPostal()));//mtrip.setRecipientPostalcode("00000");//todo validar
        mtrip.setShipperCompanyname(data.getSendtripPlus().getRemitente().getCompania());//mtrip.setShipperCompanyname("NEWGEOORIGEN");//todo revisar alan
        mtrip.setShipperPostalcode(String.valueOf(data.getSendtripPlus().getRemitente().getCodigoPostal()));//mtrip.setShipperPostalcode("00000");
        mtrip.setVehicleName(data.getVehiculo().getEconomico());//mtrip.setVehicleName("NLA-003YF2");//todo economico
        mtrip.setVehiclePlate(data.getVehiculo().getPlaca());//mtrip.setVehiclePlate("NLA-003YF2");//todo placa
        //region TODO nuevos campos si sentriplusCheckTickets no tiene la geocerca  ESTO DEE SER EN LA RECOLECCION Y EN LA SALIDA NUNCA EN LA ENTREGA
        mtrip.setRecipientCity(data.getSendtripPlus().getDestinatario().getEstado());
        mtrip.setRecipientCountry(data.getSendtripPlus().getDestinatario().getPais());
        if(data.getSendtripPlus().getDestinatario().getCoordenadas()!=null){
            if(!data.getSendtripPlus().getDestinatario().getCoordenadas().isEmpty()) {
                double latitude = 0.0;
                double longitude = 0.0;
                String[] parts = data.getSendtripPlus().getDestinatario().getCoordenadas().split(",");
                if (parts.length == 2 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                    latitude = Double.parseDouble(parts[0].trim());
                    longitude = Double.parseDouble(parts[1].trim());
                }
                // Trim whitespace and convert to double

                mtrip.setRecipientLatitude(latitude);
                mtrip.setRecipientLongitude(longitude);
            }else{
                mtrip.setRecipientLatitude(0.0);
                mtrip.setRecipientLongitude(0.0);
            }
        }
        mtrip.setRecipientPostalcode(String.valueOf(data.getSendtripPlus().getDestinatario().getCodigoPostal()));
        mtrip.setRecipientStreet(data.getSendtripPlus().getDestinatario().getCalle()+" "+data.getSendtripPlus().getDestinatario().getColonia()+" "+ data.getSendtripPlus().getDestinatario().getLocalidad()+" "+data.getSendtripPlus().getDestinatario().getMunicipio());//pendiente ver si agregar
        mtrip.setShipperPostalcode(String.valueOf(data.getSendtripPlus().getRemitente().getCodigoPostal()));
        mtrip.setShipperStreet(data.getSendtripPlus().getRemitente().getCalle()+" "+data.getSendtripPlus().getRemitente().getColonia()+" "+ data.getSendtripPlus().getRemitente().getLocalidad()+" "+data.getSendtripPlus().getRemitente().getMunicipio());
        //endregion
        SendTripPlus request= new SendTripPlus(token_Avocado,mtrip);
        Call<ResponseSendTripPlus> call= service3.setSendtriplus(request);
        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        Log.e("sendtripplus","sendtriplus: "+ jsonString);
        //presenter.showDialog();
        call.enqueue(new Callback<ResponseSendTripPlus>() {
            @Override
            public void onResponse(Call<ResponseSendTripPlus> call, Response<ResponseSendTripPlus> response) {
                validateSendtriplus(response,context);

            }

            @Override
            public void onFailure(Call<ResponseSendTripPlus> call, Throwable t) {
                Log.e("sendtripplus","onFailure");
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.gomanifest(iteration);
            }
        });
    }

    private void validateSendtriplus(Response<ResponseSendTripPlus> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseSendtrip(response,context);
        } else {
            Log.e("sendtripplus","RetrofitValidations fail");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            presenter.gomanifest(iteration);
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
                    //   Toast.makeText(context, "Folio sendTrip: "+data.getOrderFolio(), Toast.LENGTH_SHORT).show();
                    Log.e("sendtripplus","Folio sendTrip: "+data.getOrderFolio());
                    presenter.gomanifest(iteration);
                }else{
                    presenter.gomanifest(iteration);
                }
            }else{
                //presenter.nextRequest();
                Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                Log.e("sendtripplus","no  105");
                presenter.gomanifest(iteration);
            }
        }else{
            presenter.gomanifest(iteration);
            Log.e("sendtripplus","resp null");
        }
    }
}
