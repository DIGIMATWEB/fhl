package com.fhl.sistemadedistribucionfh.login.presenter;

import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;

public interface loginpresenter {
    void requestLogin(String user,String password, Boolean checkBoxState);
    void succesLogin();
    void FailureLogin(String message);

    void saveToken(String token);

    void requestProfileValues(String token);

    void saveUserValues(profileResponse body);
    void continueWithoutSave(Boolean checkBoxState);

    void requestProfileRole(String token, String idEmpleadoString);

    void setRole();

    void hideDialog();

    void showDialog();
}
