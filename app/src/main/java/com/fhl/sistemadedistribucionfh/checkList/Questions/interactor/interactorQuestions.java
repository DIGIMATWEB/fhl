package com.fhl.sistemadedistribucionfh.checkList.Questions.interactor;

import okhttp3.RequestBody;

public interface interactorQuestions {
    void getQeustions(Integer position);
    void sendDataChecklist(Integer vehiculoChkId, Integer despachoId, String fechaAplicado, String jsonRespuestas, String usuario, Integer vehiculoId, Integer checklistId);

}
