package com.fhl.sistemadedistribucionfh.login.view;

public interface loginview {
    void succesLogin();
    void failLogin(String message);

    void saveToken(String token);
}
