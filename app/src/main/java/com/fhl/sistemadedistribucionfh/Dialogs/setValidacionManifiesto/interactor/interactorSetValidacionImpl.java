package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.responseVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.util.serviceHabilities;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.Validadorset;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetDatosValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetDatosValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util.serviceSetValidacionManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.google.gson.Gson;

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
    private Retrofit retrofitClient;
    private Retrofit retrofit;
    private serviceHabilities service2;

    public interactorSetValidacionImpl(presenterSetValidacion presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceSetValidacionManifest.class);

        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service2= retrofit.create(serviceHabilities.class);
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
               presenter.setresponseValidacionMenifest("401");

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
                    presenter.setresponseValidacionMenifest("401");
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

                        presenter.setresponseValidacionMenifest("105");

                    } else {
                        presenter.setresponseValidacionMenifest("107");
                    }
                } else {

                    presenter.setresponseValidacionMenifest("401");
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

}
