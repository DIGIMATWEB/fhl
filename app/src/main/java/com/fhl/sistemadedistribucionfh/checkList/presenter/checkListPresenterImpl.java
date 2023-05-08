package com.fhl.sistemadedistribucionfh.checkList.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.checkList.interactor.checklistInteractor;
import com.fhl.sistemadedistribucionfh.checkList.interactor.checklistInteractorImpl;
import com.fhl.sistemadedistribucionfh.checkList.model.dataChecklist;
import com.fhl.sistemadedistribucionfh.checkList.view.checklistView;

import java.util.List;

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
    public void setChecklist(List<dataChecklist> data) {
        if(view!=null){
            view.setCheckList(data);
        }
    }
}
