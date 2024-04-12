package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter.presenterSalida;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientAvocado;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.dataStatusManifestTicket;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.responseStatusManifestOrTicket;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorSalidaImpl implements interactorSalida{
    private presenterSalida presenter;
    private Context context;
    private serviceSendtripPlus service2;
    private serviceEvidence service;
    private Retrofit retrofitClientV2,retrofitClient;
    public  interactorSalidaImpl(presenterSalida presenter , Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceEvidence.class);
        //todo este es la instancia de la peticion para el token de avocado y el TEA
        retrofitClientV2= RetrifitClientAvocado.getRetrofitInstance();
        service2 = retrofitClientV2.create(serviceSendtripPlus.class);
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
        if(token_Avocado!=null&&operadorId!=null){
            Log.e("sendtripplus","token_Avocado "+token_Avocado);
            Log.e("sendtripplus","dataTicketSendtrip "+dataTicketSendtrip.get(0));
            Log.e("sendtripplus","operadorId "+operadorId);
            Log.e("sendtripplus","currentManifest "+currentManifest);
            Log.e("sendtripplus","sentripPlusFlow "+sentripPlusFlow);
            requestSendtriplus(token_Avocado,dataTicketSendtrip.get(0),operadorId,currentManifest,sentripPlusFlow);

        }else {
            Log.e("salidaSentrip","token "+token_Avocado+"  operadorId "+operadorId);
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
            Log.e("sendtripplus","RetrofitValidations fail sentrip");
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
    public void changeStatusManifestTicket(String currentManifest, String changeStatusTicket, String sentripPlusFlow) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Integer statusDespacho=0;
        Integer statusTicket = 0;
        RequestBody currentManifestR =null ;
        RequestBody statusDespachoR = null;
        RequestBody changeStatusTicketR = null;
        RequestBody statusTicketR = null;
        if(sentripPlusFlow.equals("Recoleccion")){
            statusDespacho=3;
            statusTicket=3;
            currentManifestR = RequestBody.create(MediaType.parse("text/plain"), currentManifest);
            statusDespachoR = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(statusDespacho));
            changeStatusTicketR = RequestBody.create(MediaType.parse("text/plain"), changeStatusTicket);
            statusTicketR = RequestBody.create(MediaType.parse("text/plain"),String.valueOf( statusTicket));
        }else if (sentripPlusFlow.equals("Entrega")){
            statusDespacho=4;
            statusTicket=4;
            currentManifestR = null;
            statusDespachoR = null;
            changeStatusTicketR = RequestBody.create(MediaType.parse("text/plain"), changeStatusTicket);
            statusTicketR = RequestBody.create(MediaType.parse("text/plain"),String.valueOf( statusTicket));
        }else{
            statusDespacho=2;
            statusTicket=2;
            currentManifestR = null;
            statusDespachoR = null;
            changeStatusTicketR = null;
            statusTicketR = null;
        }


        Call<responseStatusManifestOrTicket> call= service2.setEstatusByManifiestoOrTicket(token,currentManifestR,statusDespachoR,changeStatusTicketR,statusTicketR);
        Log.e("changeStatus",currentManifestR+" "+statusDespachoR+" "+changeStatusTicketR+"  "+statusTicketR);

        call.enqueue(new Callback<responseStatusManifestOrTicket>() {
            @Override
            public void onResponse(Call<responseStatusManifestOrTicket> call, Response<responseStatusManifestOrTicket> response) {
                validateChangeStatus(response,context);
            }

            @Override
            public void onFailure(Call<responseStatusManifestOrTicket> call, Throwable t) {
                Log.e("changeStatus",""+t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.nextRequest();
            }
        });

    }

    private void validateChangeStatus(Response<responseStatusManifestOrTicket> response, Context context) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            responseStatuscheck(response,context);
        } else {
            Log.e("changeStatus","RetrofitValidations fail change");
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            presenter.nextRequest();
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
                    Log.e("changeStatus", "Folio sendTrip: " + resp.getMessage());
                    presenter.nextRequest();

                } else {
                    Log.e("changeStatus", "Folio sendTrip: " + data.getEstatusDespacho().getFolioDespacho());
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

    @Override
    public void requestDetailTicketsSendtriplus(boolean isArray, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            if(isArray) {//todo no es uno solo es de la iteracion que le corresponde
                requestDetailforSendtriplusTicket(token, ticket, currentManifest);
            }else{//todo si es uno solo es el ticket que le corresponde
                requestDetailforSendtriplusTicket(token, folioTicket, currentManifest);
            }
        }
    }


    private void requestDetailforSendtriplusTicket(String token, String iterateidTickets, String currentManifest) {
        Log.e("salidaSentrip","token "+token);
        Log.e("salidaSentrip","iterateidTickets "+iterateidTickets);
        Log.e("salidaSentrip","currentManifest "+currentManifest);
        Call<TicketsDetailSentriplus> call= service2.getTicket(token,currentManifest,iterateidTickets);
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
            Log.e("responseSendtripTicket","RetrofitValidations fail Ticket");
            Log.e("responseSendtripTicket",""+response.message());
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
                    //Toast.makeText(context, "Folio sendTripDetail: "+data.get(0).getFolioTicket(), Toast.LENGTH_SHORT).show();
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