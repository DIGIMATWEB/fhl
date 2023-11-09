package com.fhl.sistemadedistribucionfh.login.presenter;

import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;

public interface loginpresenter {
    void requestLogin(String user,String password);
    void succesLogin();
    void FailureLogin(String message);

    void saveToken(String token);

    void requestProfileValues(String token);

    void saveUserValues(profileResponse body);
}
