package com.fhl.sistemadedistribucionfh.login.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.login.interactor.loginInteractor;
import com.fhl.sistemadedistribucionfh.login.interactor.loginInteractorImplementation;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.login.view.loginview;

public class loginpresenterImplementation  implements loginpresenter{
    private Context context;
    private loginview view;
    private loginInteractor interactor;

    public loginpresenterImplementation(loginview view,Context context){
        this.view=view;
        this.context=context;
        this.interactor=new loginInteractorImplementation(this,context);
    }


    @Override
    public void requestLogin(String user, String password, Boolean checkBoxState) {
        if(view!=null){
          interactor.myrequestLogin(user,password, checkBoxState);
        }
    }
    @Override
    public void requestProfileValues(String token) {
        if(view!=null){
            interactor.requestProfileValues(token);
        }
    }

    @Override
    public void saveUserValues(profileResponse body) {
        if(view!=null){
            view.saveUserValues(body);
        }
    }

    @Override
    public void continueWithoutSave(Boolean checkBoxState) {
        if(view!=null){
            view.continueWithoutSave(checkBoxState);
        }
    }

    @Override
    public void succesLogin() {
        if(view!=null){
           view.succesLogin();
        }
    }

    @Override
    public void FailureLogin(String message) {
        if(view!=null){
          view.failLogin(message);
        }
    }

    @Override
    public void saveToken(String token) {
        if(view!=null){
            view.saveToken(token);
        }
    }


}
