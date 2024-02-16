package com.fhl.sistemadedistribucionfh.evidence.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.evidence.evidenceView;
import com.fhl.sistemadedistribucionfh.evidence.interactor.sendEvidenceInteractor;
import com.fhl.sistemadedistribucionfh.evidence.interactor.sendEvidenceInteractorImpl;

public class requestEvidencePresenterImpl  implements requestEvidencePresenter{
    private Context context;
    private evidenceView view;
    private sendEvidenceInteractor interactor;

    public requestEvidencePresenterImpl (evidenceView view,Context context){
        this.view=view;
        this.context=context;
        this.interactor=new sendEvidenceInteractorImpl(this,context);

    }
    @Override
    public void sendEvidence() {
        if(view!=null){
            interactor.requestEvidence();
        }
    }
}
