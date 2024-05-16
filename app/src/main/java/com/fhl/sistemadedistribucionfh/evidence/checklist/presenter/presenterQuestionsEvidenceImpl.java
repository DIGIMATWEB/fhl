package com.fhl.sistemadedistribucionfh.evidence.checklist.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.checkList.Questions.interactor.interactorQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionsView;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.evidence.checklist.interactor.interactorQuestionsEvidenceImpl;
import com.fhl.sistemadedistribucionfh.evidence.checklist.view.questionsEvidenceView;

import java.util.List;

public class presenterQuestionsEvidenceImpl implements presenterQuestionsEvidence {
    private questionsEvidenceView view;
    private Context context;
    private interactorQuestionsEvidenceImpl interactor;
    public presenterQuestionsEvidenceImpl(questionsEvidenceView view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorQuestionsEvidenceImpl(this,context);

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
    public void gotoChecklistAgain(Boolean status) {
        if(view!=null) {
            view.successetCehcklist(status);
        }
    }

    @Override
    public void sendDataChecklist(String jsonRespuestas, String usuario, String folioTicket, Integer vehiculoId, Integer checklistId){
        if(view!=null) {
            interactor.sendDataChecklist(jsonRespuestas, usuario, folioTicket, vehiculoId, checklistId);
        }
    }
}
