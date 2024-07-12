package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.CurrentData;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.cadenaJSON;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.dataResponseSetSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.requestSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model.responseSetSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.util.serviceSellos;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.util.serviceSellosManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class sellosInteractorImpl implements sellosInteractor{
    private presenterSello presenter;
    private Context context;
    private Retrofit retrofit ,retrofit2;
    private serviceSellos service;
    private serviceSellosManifest service2;
    public sellosInteractorImpl(presenterSello presenter,Context context){
        this.presenter=presenter;
        this.context=context;

        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service= retrofit.create(serviceSellos.class);

        retrofit2= RetrofitClientFHManifest.getRetrofitInstance();
        service2= retrofit2.create(serviceSellosManifest.class);

    }
    //region get sellos
    @Override
    public void reqSellos(String folioDespachoId) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null) {
            requestDataSalida(token,folioDespachoId);
        }
    }
    private void requestDataSalida(String token,String code) {
        Call<ResponseSellos> call=service2.getSalidaV2(token,code);
        Log.e("QR","code qr "+code);
        presenter.showDialog();
        call.enqueue(new Callback<ResponseSellos>() {
            @Override
            public void onResponse(Call<ResponseSellos> call, Response<ResponseSellos> response) {
                validateResponseSellos(response,context);
            }

            @Override
            public void onFailure(Call<ResponseSellos> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateResponseSellos(Response<ResponseSellos> response, Context context) {
        if (response != null) {
            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDataQRv2(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataQRv2(Response<ResponseSellos> response, Context context) {
        ResponseSellos resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {


                if(resp.getData()!=null) {
                    presenter.setSellos(resp.getData().getSellos());
                    presenter.hideDialog();
                } else {
                    Toast.makeText(context, "Sin sellos asignados", Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    //endregion
    @Override
    public void requestManifestdetail(String currentManifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        Log.e("dataSellos","user: "+user);
        Log.e("dataSellos","manifetRequested "+currentManifest);
        Call<responseManifestSalidaV2> call = service2.getManifestV2(token,user,currentManifest);

        call.enqueue(new Callback<responseManifestSalidaV2>() {
            @Override
            public void onResponse(Call<responseManifestSalidaV2> call, Response<responseManifestSalidaV2> response) {
                validateResponse(response, context);
            }

            @Override
            public void onFailure(Call<responseManifestSalidaV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void validateResponse(Response<responseManifestSalidaV2> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getManifest(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    // presenter.returnTologin();

                }
            }
        }
    }

    private void getManifest(Response<responseManifestSalidaV2> response, Context context) {
        responseManifestSalidaV2 resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                List<responseManifestSalidaV2data> data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("dataSellos",""+json);
                if(data!=null) {
                    if(data.isEmpty()) {
                        Toast.makeText(context, "Sin informacion", Toast.LENGTH_SHORT).show();
                    }else {
                        presenter.saveManifestId(data.get(0).getId());
                    }

                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void setSellos(Integer manifestId, List<Sello> sellos) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Gson gson= new Gson();
        String json = gson.toJson(sellos);
        Log.e("dataSellos","idM: "+manifestId);
        Log.e("dataSellos","sellos: "+json);
        List<Sello> fsellos=new ArrayList<>();
        fsellos.clear();
        for(Sello seio:sellos){
            seio.setDespachoId(manifestId);
            fsellos.add(seio);
        }
        Gson gson2= new Gson();
        String json2 = gson2.toJson(fsellos);
        Log.e("dataSellos","fsellos: "+json2);
        List<cadenaJSON> cJson=new ArrayList<>();
        cJson.clear();

        for(Sello s:fsellos){
            cJson.add(new cadenaJSON(0,new CurrentData(s.getNumeroSello(),s.getDespachoId(),null,null,"[{\"trail_system_user\":\"FHapp\",\"trail_workstation\":\"FH\",\"trail_notes\":\"Alta de registro\",\"trail_timemark\":\"2024-06-11T12:00:12.7083963-06:00\"}]")));
        }
        Gson gson3= new Gson();
        String json3 = gson3.toJson(cJson);
        Log.e("dataSellos","cJson: "+json3);
        requestSello request= new requestSello(json3);
        Call<responseSetSello> call= service.setTSellos(token,request);
        call.enqueue(new Callback<responseSetSello>() {
            @Override
            public void onResponse(Call<responseSetSello> call, Response<responseSetSello> response) {
                validateSetSello(response);
            }

            @Override
            public void onFailure(Call<responseSetSello> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.getMessageSello();
            }
        });

    }

    private void validateSetSello(Response<responseSetSello> response) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setSellosf(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    presenter.getMessageSello();

                }
            }
        }
    }

    private void setSellosf(Response<responseSetSello> response, Context context) {
        responseSetSello resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                dataResponseSetSello data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("dataSellos","setSellosf "+json);
                if(data!=null) {
                       // Toast.makeText(context, ""+data.getEsNuevo(), Toast.LENGTH_SHORT).show();
                        presenter.getMessageSello();
                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
                    presenter.getMessageSello();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                presenter.getMessageSello();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            presenter.getMessageSello();
        }
    }


}
