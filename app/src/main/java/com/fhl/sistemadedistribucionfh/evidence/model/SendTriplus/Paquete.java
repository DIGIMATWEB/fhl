
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Paquete implements Serializable {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Items")
    @Expose
    private List<Item> items;
    private Boolean flag=false;
    public Paquete(String nombre, List<Item> items,Boolean flag) {
        super();
        this.nombre = nombre;
        this.items = items;
        this.flag=flag;
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

    public Boolean getFlag() {
        if(flag!=null) {
            return flag;
        }else{
            flag=false;
            return flag;
        }
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
