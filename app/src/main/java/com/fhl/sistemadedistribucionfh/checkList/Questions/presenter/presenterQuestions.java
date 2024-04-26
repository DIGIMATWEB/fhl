package com.fhl.sistemadedistribucionfh.checkList.Questions.presenter;

import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public interface presenterQuestions {
    void requestQuestions(Integer position);
    void setQuestions( List<Pregunta> mdata);
    void setData (List<VehiculoVsCheck> data);
    void sendDataChecklist(Integer vehiculoChkId, Integer despachoId, String fechaAplicado, String jsonRespuestas, String usuario, Integer vehiculoId, Integer checklistId);
    void showpDialog();
    void hidepDialog();
    void closeChecklistError();
    void gotoChecklistAgain();
}
