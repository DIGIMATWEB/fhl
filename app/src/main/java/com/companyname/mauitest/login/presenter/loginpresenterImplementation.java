package com.companyname.mauitest.login.presenter;

import android.content.Context;

import com.companyname.mauitest.login.interactor.loginInteractor;
import com.companyname.mauitest.login.interactor.loginInteractorImplementation;
import com.companyname.mauitest.login.view.loginview;

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
    public void requestLogin(String user, String password) {
        if(view!=null){
          interactor.myrequestLogin(user,password);
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
