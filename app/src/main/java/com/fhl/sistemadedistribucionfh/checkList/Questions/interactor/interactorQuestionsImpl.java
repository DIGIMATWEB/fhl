package com.fhl.sistemadedistribucionfh.checkList.Questions.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHVehicles;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.dataQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.responseQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.util.utilQuestionary;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseSendChecklist;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorQuestionsImpl  implements  interactorQuestions {
    private Context context;
    private presenterQuestions presenter;
    private utilQuestionary service;
    private Retrofit retrofitClient;

    public interactorQuestionsImpl(presenterQuestions presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientFHVehicles.getRetrofitInstance();
        service = retrofitClient.create(utilQuestionary.class);
    }

    @Override
    public void getQeustions(Integer positionRespuestas,Integer checklistId) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String vehicle =preferences.getString(GeneralConstants.VEHICLEID,null);
        if (token != null) {
            requestQuestions(token, positionRespuestas,vehicle,checklistId);
        }
    }

    private void requestQuestions(String token, Integer positionRespuestas, String vehicle, Integer checklistId) {
        Call<responseQuestions> call = service.getChecklist(token, Integer.valueOf(vehicle), checklistId);
        call.enqueue(new Callback<responseQuestions>() {
            @Override
            public void onResponse(Call<responseQuestions> call, Response<responseQuestions> response) {
                validateResponseQuestions(response, context, positionRespuestas);
            }

            @Override
            public void onFailure(Call<responseQuestions> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseQuestions(Response<responseQuestions> response, Context context, Integer positionRespuestas) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getQuestions(response, context, positionRespuestas);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getQuestions(Response<responseQuestions> response, Context context, Integer positionRespuesta) {
        responseQuestions resp = response.body();
        if (resp != null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if (resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {
                dataQuestions mdata = resp.getData();
                if (mdata != null) {
                    List<VehiculoVsCheck> data = mdata.getVehiculoVsChecklist();
                    if (data != null) {
                        //TODO: Revisar el resultado aqui
                        List<Pregunta> questionary = data.get(0).getChecklist().getPreguntas();
                        //List<Pregunta> questionary=data.get(positionRespuesta).getChecklist().getPreguntas();
                        if (questionary != null) {
                            presenter.setQuestions(questionary);
                            presenter.setData(data);
                        } else {
                            Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendDataChecklist(Integer vehiculoChkId, Integer despachoId, String fechaAplicado, String jsonRespuestas, String usuario, Integer vehiculoId, Integer checklistId) {
        presenter.showpDialog();
        // Token
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String user = preferences.getString(GeneralConstants.OPERADOR_NAME,null);

        // RequestBody
        RequestBody VehiculoChkId = null;
        RequestBody DespachoId = null;
        RequestBody FechaAplicado = null;
        RequestBody JsonRespuestas = null;
        RequestBody Usuario = null;
        RequestBody VehiculoId = null;
        RequestBody ChecklistId = null;

        // RequestBody con variables
        VehiculoChkId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(vehiculoChkId));
        DespachoId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(despachoId));
        FechaAplicado = RequestBody.create(MediaType.parse("text/plain"), fechaAplicado);
        JsonRespuestas = RequestBody.create(MediaType.parse("text/plain"), jsonRespuestas);
        Usuario = RequestBody.create(MediaType.parse("text/plain"), user);
        VehiculoId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(vehiculoId));
        ChecklistId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(checklistId));

        // Enviamos los datos
        Call<responseSendChecklist> call= service.setChecklistByVehiculo(token,VehiculoChkId,DespachoId,FechaAplicado,JsonRespuestas,Usuario,VehiculoId,ChecklistId);
       // Log.e("SendCheck: ", token + VehiculoChkId + DespachoId + FechaAplicado + JsonRespuestas + Usuario + VehiculoId + ChecklistId);//TODO no puedes logear correctamente un requestbody no usar asi
        Log.e("SendCheck: ",  "vehicleCheckId: "+vehiculoChkId+" Despacho: " + despachoId+" fecha: "+ fechaAplicado+" JSON: " + jsonRespuestas + " vehicleId: " + vehiculoId+" checkListId: " + checklistId);
        call.enqueue(new Callback<responseSendChecklist>() {
            @Override
            public void onResponse(Call<responseSendChecklist> call, Response<responseSendChecklist> response) {
                validateResponseChecklist(response, context);
            }

            @Override
            public void onFailure(Call<responseSendChecklist> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseChecklist(Response<responseSendChecklist> response, Context context) {
        responseSendChecklist responseSendChecklist = response.body();

        if(responseSendChecklist!=null){
            Integer responseCode = responseSendChecklist.getStatus();
            String function = responseSendChecklist.getFunction();

            if(responseCode == GeneralConstants.RESPONSE_CODE_OK_PEP) {
                String message = responseSendChecklist.getMessage();
                Log.e("SendCheck","respuesta: "+message);
                if(message.isEmpty()) {
                    // Cuando viene vacio el mensaje
                } else {
                    // Cuando no viene vacio el mensaje
                    //Toast.makeText(context, "Tod0 bien.", Toast.LENGTH_SHORT).show();
                    presenter.gotoChecklistAgain();
                    presenter.hidepDialog();
                }
            } else {
                // Cuando no es ok con el codigo
                Toast.makeText(context, "Ocurrió un error al enviar el checklist, favor de intentarlo nuevamente.", Toast.LENGTH_SHORT).show();
                presenter.closeChecklistError();
                presenter.hidepDialog();
            }
        } else {
            // Cuando es nula la data
            Toast.makeText(context, "Ocurrió un error al enviar el checklist, favor de intentarlo nuevamente.", Toast.LENGTH_SHORT).show();
            presenter.closeChecklistError();
            presenter.hidepDialog();
        }
    }
}
