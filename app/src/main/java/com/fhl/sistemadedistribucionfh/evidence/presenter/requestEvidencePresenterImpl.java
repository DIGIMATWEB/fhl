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
    public void sendEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, String stars) {
        if(view!=null){
            interactor.requestEvidence(secuenceRequest,signatureBase64,inputTextSignature,currusel,ffiles,stars);
        }
    }

    @Override
    public void nextRequest() {
        if(view!=null){
            view.setMessage();
        }
    }
}
