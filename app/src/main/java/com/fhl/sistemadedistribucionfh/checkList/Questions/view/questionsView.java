package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public interface questionsView {
    void setQuestiomns(List<Pregunta> mdata);
    void changeStatusManifestTicket();
    void setData(List<VehiculoVsCheck> data);
    void showDialog();
    void hideDialog();
    void closeCheckListError2();
    void successetCehcklist();
}
