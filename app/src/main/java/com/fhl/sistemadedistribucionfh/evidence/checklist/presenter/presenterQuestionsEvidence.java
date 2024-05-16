package com.fhl.sistemadedistribucionfh.evidence.checklist.presenter;

import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public interface presenterQuestionsEvidence {
    void sendDataChecklist(String jsonRespuestas, String usuario, String folioTicket, Integer vehiculoId, Integer checklistId);
    void showpDialog();
    void hidepDialog();
    void closeChecklistError();
    void gotoChecklistAgain(Boolean status);
}
