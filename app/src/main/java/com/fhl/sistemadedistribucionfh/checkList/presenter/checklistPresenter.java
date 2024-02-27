package com.fhl.sistemadedistribucionfh.checkList.presenter;

import com.fhl.sistemadedistribucionfh.checkList.model.v1.dataChecklist;

import java.util.List;

public interface checklistPresenter {
    void getCheckList();

    void setChecklist(List<dataChecklist> data);
}
