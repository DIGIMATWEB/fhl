package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.interactor.dialogReasonsInteractor;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.interactor.dialogReasonsInteractorImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasonsView;

import java.util.List;

public class dialogReasonsPresenterImpl implements dialogReasonsPresenter{
    private Context context;
    private dialogReasonsView view;
    private dialogReasonsInteractor interactor;
    public dialogReasonsPresenterImpl(dialogReasons view, Context context) {
        this.view=view;
        this.context=context;
        this.interactor=new dialogReasonsInteractorImpl(this,context);
    }

    @Override
    public void requestMReasons() {
        if(view!=null){
            interactor.requestMReasons();
        }
    }

    @Override
    public void setReasons(List<dataReasons> data) {
        if(view!=null){
            view.setReasons(data);
        }
    }
}
