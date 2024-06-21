package com.fhl.sistemadedistribucionfh.login.view;

import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;

public interface loginview {
    void succesLogin();
    void failLogin(String message);

    void saveToken(String token);

    void saveUserValues(profileResponse body);
    void continueWithoutSave(Boolean checkBoxState);
    void setRole();

    void showDialog();
    void hideDialog();
}
