package com.fhl.sistemadedistribucionfh.evidence.checklist.view;

import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public interface questionsEvidenceView {
    void setQuestiomns(List<Pregunta> mdata);
    void changeStatusManifestTicket();
    void showDialog();
    void hideDialog();
    void closeCheckListError2();
    void successetCehcklist(Boolean status);
}
