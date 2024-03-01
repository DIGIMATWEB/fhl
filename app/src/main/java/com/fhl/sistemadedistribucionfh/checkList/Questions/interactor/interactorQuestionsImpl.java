package com.fhl.sistemadedistribucionfh.checkList.Questions.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep2;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.dataQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.responseQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.util.utilQuestionary;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Checklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorQuestionsImpl  implements  interactorQuestions{
    private Context context;
    private presenterQuestions presenter;
    private utilQuestionary service;
    private Retrofit retrofitClient;
    public interactorQuestionsImpl(presenterQuestions presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientPep2.getRetrofitInstance();
        service=retrofitClient.create(utilQuestionary.class);
    }

    @Override
    public void getQeustions() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestQuestions(token);
        }
    }

    private void requestQuestions(String token) {
       Call<responseQuestions> call= service.getChecklist(token,6,2);
       call.enqueue(new Callback<responseQuestions>() {
           @Override
           public void onResponse(Call<responseQuestions> call, Response<responseQuestions> response) {
               validateResponseQuestions(response,context);
           }

           @Override
           public void onFailure(Call<responseQuestions> call, Throwable t) {
               Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void validateResponseQuestions(Response<responseQuestions> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getQuestions(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getQuestions(Response<responseQuestions> response, Context context) {
         responseQuestions resp=response.body();
                             if(resp!=null){
                                    String message = resp.getMessage();
                                    int responseCode = resp.getStatus();
                                   if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_PEP){
                                      dataQuestions mdata=resp.getData();
                                        if(mdata!=null){
                                            List<VehiculoVsCheck> data=   mdata.getVehiculoVsChecklist();
                                            if(data!=null) {
                                                List<Pregunta> questionary=data.get(0).getChecklist().getPreguntas();
                                                if(questionary!=null){
                                                presenter.setQuestions(questionary);
                                                }else{
                                                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                                       }
                                    }else{
                                        Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                                   }

                            } else{
                                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                                  }
    }
}
