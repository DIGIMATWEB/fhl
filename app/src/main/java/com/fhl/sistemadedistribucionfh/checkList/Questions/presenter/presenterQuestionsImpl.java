package com.fhl.sistemadedistribucionfh.checkList.Questions.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.checkList.Questions.interactor.interactorQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionsView;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public class presenterQuestionsImpl  implements  presenterQuestions{
    private questionsView view;
    private Context context;
    private interactorQuestionsImpl interactor;
    public presenterQuestionsImpl(questionsView view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorQuestionsImpl(this,context);

    }
    @Override
    public void requestQuestions(Integer position, Integer checklistId) {
        if(view!=null) {
            interactor.getQeustions(position,checklistId);
        }
    }

    @Override
    public void setQuestions( List<Pregunta>  mdata) {
        if(view!=null) {
            view.setQuestiomns(mdata);
        }
    }

    @Override
    public void setData(List<VehiculoVsCheck> data) {
        if(view!=null) {
            view.setData(data);
        }
    }

    @Override
    public void showpDialog() {
        if(view!=null) {
            view.showDialog();
        }
    }

    @Override
    public void hidepDialog() {
        if(view!=null) {
            view.hideDialog();
        }
    }

    @Override
    public void closeChecklistError() {
        if(view!=null){
            view.closeCheckListError2();
        }
    }

    @Override
    public void gotoChecklistAgain() {
        if(view!=null) {
            view.successetCehcklist();
        }
    }

    @Override
    public void sendDataChecklist(Integer vehiculoChkId, Integer despachoId, String fechaAplicado, String jsonRespuestas, String usuario, Integer vehiculoId, Integer checklistId){
        if(view!=null) {
            interactor.sendDataChecklist(vehiculoChkId, despachoId, fechaAplicado,jsonRespuestas, usuario, vehiculoId, checklistId);
        }
    }
}
