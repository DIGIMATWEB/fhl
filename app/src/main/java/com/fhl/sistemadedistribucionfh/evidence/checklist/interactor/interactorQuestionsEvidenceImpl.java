package com.fhl.sistemadedistribucionfh.evidence.checklist.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHVehicles;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.util.utilQuestionary;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseSendChecklist;
import com.fhl.sistemadedistribucionfh.evidence.checklist.presenter.presenterQuestionsEvidence;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorQuestionsEvidenceImpl implements interactorQuestionsEvidence {
    private Context context;
    private presenterQuestionsEvidence presenter;
    private utilQuestionary service;
    private Retrofit retrofitClient;

    public interactorQuestionsEvidenceImpl(presenterQuestionsEvidence presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(utilQuestionary.class);
    }

    @Override
    public void sendDataChecklist(String jsonRespuestas, String usuario, String folioTicket, Integer vehiculoId, Integer checklistId) {
        presenter.showpDialog();
        // Token
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);

        // RequestBody
        RequestBody JsonRespuestas = null;
        RequestBody Usuario = null;
        RequestBody FolioTicket = null;
        RequestBody VehiculoId = null;
        RequestBody ChecklistId = null;

        // RequestBody con variables
        JsonRespuestas = RequestBody.create(MediaType.parse("text/plain"), jsonRespuestas);
        Usuario = RequestBody.create(MediaType.parse("text/plain"), usuario);
        FolioTicket = RequestBody.create(MediaType.parse("text/plain"), folioTicket);
        VehiculoId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(vehiculoId));
        ChecklistId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(checklistId));

        // Enviamos los datos
        Call<responseSendChecklist> call= service.setChecklistByTicket(token,JsonRespuestas,Usuario,FolioTicket, VehiculoId,ChecklistId);
        // Log.e("SendCheck: ", token + VehiculoChkId + DespachoId + FechaAplicado + JsonRespuestas + Usuario + VehiculoId + ChecklistId);//TODO no puedes logear correctamente un requestbody no usar asi
        Log.e("SendCheck: ",  " JSON: " + jsonRespuestas + " User" + usuario + " folioTicket" + folioTicket + " vehicleId: " + vehiculoId+" checkListId: " + checklistId);
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

            if(responseCode == GeneralConstants.RESPONSE_CODE_OK_FH) {
                String message = responseSendChecklist.getMessage();
                Log.e("SendCheck","respuesta: "+message);
                if(message.isEmpty()) {
                    // Cuando viene vacio el mensaje
                } else {
                    // Cuando no viene vacio el mensaje
                    //Toast.makeText(context, "Tod0 bien.", Toast.LENGTH_SHORT).show();
                    // Mandamos el Boolean
                    Boolean status = true;
                    presenter.gotoChecklistAgain(status);
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
