package com.fhl.sistemadedistribucionfh.checkList.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.checkList.interactor.checklistInteractor;
import com.fhl.sistemadedistribucionfh.checkList.interactor.checklistInteractorImpl;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.dataChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.view.checklistView;

public class checkListPresenterImpl implements checklistPresenter{
    private checklistView view;
    private checklistInteractor interactor;
    private Context context;
    public checkListPresenterImpl(checklistView view, Context context) {
        this.view=view;
        this.context=context;
        this.interactor=new checklistInteractorImpl(this,context);
    }

    @Override
    public void getCheckList() {
        if(view!=null){
            interactor.requestChecklist();
        }
    }

    @Override
    public void setChecklist(dataChecklistV2 data) {
        if(view!=null){
            view.setCheckList(data);
        }
    }
}
