package com.fhl.sistemadedistribucionfh.login.model.modelLogin;

import com.google.gson.annotations.SerializedName;

public class requestLogin {


    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public requestLogin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
