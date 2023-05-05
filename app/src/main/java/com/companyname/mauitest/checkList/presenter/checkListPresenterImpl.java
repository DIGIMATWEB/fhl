package com.companyname.mauitest.checkList.presenter;

import android.content.Context;

import com.companyname.mauitest.checkList.interactor.checklistInteractor;
import com.companyname.mauitest.checkList.interactor.checklistInteractorImpl;
import com.companyname.mauitest.checkList.model.dataChecklist;
import com.companyname.mauitest.checkList.view.checkList;
import com.companyname.mauitest.checkList.view.checklistView;

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
