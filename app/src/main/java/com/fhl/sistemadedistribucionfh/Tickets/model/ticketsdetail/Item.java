package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("Llave")
    public Integer llave;

    @SerializedName("Valor")
    public String valor;
}
