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
    public void sendEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket) {
        if(view!=null){
            interactor.requestEvidence(secuenceRequest,signatureBase64,inputTextSignature,currusel,ffiles,flujoId,folioTicket);
        }
    }
    @Override
    public void sendRate(Integer stars, String folioTicket) {
        if(view!=null){
            interactor.sendRate(Integer.valueOf(stars),folioTicket);
        }
    }
    @Override
    public void sendSentriplus() {
        if(view!=null){
            interactor.sendSentriplus();
        }
    }

    @Override
    public void tokenAvocado() {
        if(view!=null){
            interactor.tokenAvocado();
        }
    }

    @Override
    public void validateSendtrip() {
        if(view!=null){
            view.validateSendtrip();
        }
    }

    @Override
    public void nextRequest() {
        if(view!=null){
            view.setMessage();
        }
    }
    @Override
    public void showDialog() {
        if(view!=null){
            view.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null){

                view.hideDialog();

        }
    }



}
