package com.fhl.sistemadedistribucionfh.login.interactor;

public interface loginInteractor {
    void myrequestLogin(String user,String password, Boolean checkBoxState);
    void requestProfileValues(String token);

    void requestProfileRole(String token, String idEmpleadoString);
}
