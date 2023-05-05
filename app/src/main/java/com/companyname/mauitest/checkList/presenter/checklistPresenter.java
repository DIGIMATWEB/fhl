package com.companyname.mauitest.checkList.presenter;

import com.companyname.mauitest.checkList.model.dataChecklist;

import java.util.List;

public interface checklistPresenter {
    void getCheckList();

    void setChecklist(List<dataChecklist> data);
}
