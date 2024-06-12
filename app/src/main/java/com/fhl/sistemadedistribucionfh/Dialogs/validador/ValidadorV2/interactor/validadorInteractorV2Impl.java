package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.responseValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter.presenterValidadorDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.util.validadorService;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TicketsDetailSentriplus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class validadorInteractorV2Impl implements validadorInteractorV2{
    private presenterValidadorDetail presenter;
    private Context context;
    private validadorService service;
    private Retrofit retrofitClient;
    public validadorInteractorV2Impl(presenterValidadorDetail presenter, Context context) {
        this.presenter= presenter;
        this.context= context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(validadorService.class);
    }

    @Override
    public void requestManifestDetail(int idEmpleado, String currentManifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);
        Log.e("Validador"," M: "+currentManifest +"   "+idEmpleado);
       Call<responseValidadorV2> call = service.getManifestV2Detail(token,  idEmpleado,currentManifest);
       call.enqueue(new Callback<responseValidadorV2>() {
           @Override
           public void onResponse(Call<responseValidadorV2> call, Response<responseValidadorV2> response) {
               validateResponse(response, context);
           }

           @Override
           public void onFailure(Call<responseValidadorV2> call, Throwable t) {
               Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
             //  presenter.hideProgress();
           }
       });
    }

    @Override
    public void getTicketByManifest(String currentManifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);

        //requestTicketsManifestV2 request = new requestTicketsManifestV2(ticket);
        Call<responseTicketsManifestV2> call = service.getTicket(token,currentManifest ,null);
        Gson gson =new Gson();
        String json = gson.toJson(call.request());
        Log.e("getticketsdetail",""+json);
        //presenter.showProgress();
        call.enqueue(new Callback<responseTicketsManifestV2>() {
            @Override
            public void onResponse(Call<responseTicketsManifestV2> call, Response<responseTicketsManifestV2> response) {
                validateResponset(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketsManifestV2> call, Throwable t) {
                Toast.makeText(context, "bad request"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("getticketsdetail","onFailure "+t.getMessage());
                Log.e("getticketsdetail","onFailure "+t.getCause());
                Log.e("getticketsdetail","onFailure "+t.toString());
                //presenter.hideProgress();
                //presenter.setDatahardcode();
            }
        });
    }

    private void validateResponset(Response<responseTicketsManifestV2> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getTickets(response, context);
            } else {
                Toast.makeText(context, "fail respose" + response.message(), Toast.LENGTH_SHORT).show();
                Log.e("getticketsdetail","validateResponset "+response.message());
               // presenter.hideProgress();
            }
        }
    }

    private void getTickets(Response<responseTicketsManifestV2> response, Context context) {
        responseTicketsManifestV2 resp = response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_FH){
                List<dataTicketsManifestV2> data = resp.getData();
                Gson gson=new Gson();
                String json=gson.toJson(data);
                Log.e("manifestDetail","json tickets "+json);
                if(data!=null){
                    presenter.setDetailTickets(data);
                }else{
                    Log.e("manifestDetail","sin tickets asignados");
                   // presenter.setTickets(data);
                    // Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
               // presenter.hideProgress();
            }else{
                Log.e("manifestDetail","response not ok");//Toast.makeText(context, "response not ok" + response.message(), Toast.LENGTH_SHORT).show();
               // presenter.hideProgress();
            }

        } else{
            Log.e("manifestDetail","response null");//Toast.makeText(context, "response null" + response.message(), Toast.LENGTH_SHORT).show();
          //  presenter.hideProgress();
        }
    }

    private void validateResponse(Response<responseValidadorV2> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getManifestDetail(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                  //  presenter.hideProgress();
                }
            }
        }
    }

    private void getManifestDetail(Response<responseValidadorV2> response, Context context) {
        responseValidadorV2 resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                List<dataValidadorV2> data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    if(data.isEmpty()) {
                        Toast.makeText(context, "Sin informacion", Toast.LENGTH_SHORT).show();
                        presenter.error();
                    }else {
                        presenter.setManifestVehicleandDriver(data);
                    }
                   //presenter.hideProgress();
                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
                    //presenter.hideProgress();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
             //   presenter.hideProgress();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
           // presenter.hideProgress();
        }
    }
}
