package com.companyname.mauitest.login.presenter;

public interface loginpresenter {
    void requestLogin(String user,String password);
    void succesLogin();
    void FailureLogin(String message);

    void saveToken(String token);
}
