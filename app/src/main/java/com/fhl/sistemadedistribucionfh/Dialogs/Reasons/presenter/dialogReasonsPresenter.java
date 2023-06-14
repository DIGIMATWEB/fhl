package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;

import java.util.List;

public interface dialogReasonsPresenter {
    void requestMReasons();
    void setReasons(List<dataReasons> data);

}
