
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacto {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("Puesto")
    @Expose
    private String puesto;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Contacto() {
    }

    /**
     * 
     * @param puesto
     * @param id
     * @param telefono
     * @param nombre
     * @param email
     */
    public Contacto(Integer id, String nombre, String email, String telefono, String puesto) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
