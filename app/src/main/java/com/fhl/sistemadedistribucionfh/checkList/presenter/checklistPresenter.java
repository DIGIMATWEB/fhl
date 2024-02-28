package com.fhl.sistemadedistribucionfh.checkList.presenter;

import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;

import java.util.List;

public interface checklistPresenter {
    void getCheckList();

    void setChecklist(List<VehiculoVsCheck> data);
}
