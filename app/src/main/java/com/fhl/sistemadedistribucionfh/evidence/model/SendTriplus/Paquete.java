
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Paquete {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Items")
    @Expose
    private List<Item> items;
    public Paquete(String nombre, List<Item> items) {
        super();
        this.nombre = nombre;
        this.items = items;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
