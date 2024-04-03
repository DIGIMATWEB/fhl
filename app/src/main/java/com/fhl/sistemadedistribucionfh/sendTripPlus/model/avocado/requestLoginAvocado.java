package com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado;

import com.google.gson.annotations.SerializedName;

public class requestLoginAvocado {
    @SerializedName("password")
    private String password;
    @SerializedName("user")
    private String user;


    public requestLoginAvocado(String password, String user) {
        super();
        this.password = password;
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
