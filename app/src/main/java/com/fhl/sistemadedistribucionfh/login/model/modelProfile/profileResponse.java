package com.fhl.sistemadedistribucionfh.login.model.modelProfile;

import com.google.gson.annotations.SerializedName;

public class profileResponse {

    @SerializedName("usuarioId")
    private Integer usuarioId;
    @SerializedName("empleadoId")
    private Integer empleadoId;
    @SerializedName("fotoPerfilId")
    private Integer fotoPerfilId;
    @SerializedName("nombreUsuario")
    private String nombreUsuario;
    @SerializedName("numeroEmpleado")
    private String numeroEmpleado;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("correoElectronico")
    private String correoElectronico;
    @SerializedName("telefonoMovil")
    private String telefonoMovil;
    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;
//    @SerializedName("usuarioClientes")
//    @Expose
//    private List<Object> usuarioClientes;

    public profileResponse(Integer usuarioId, Integer empleadoId, Integer fotoPerfilId, String nombreUsuario, String numeroEmpleado, String nombre, String correoElectronico, String telefonoMovil, String fechaNacimiento) {//, List<Object> usuarioClientes) {
        super();
        this.usuarioId = usuarioId;
        this.empleadoId = empleadoId;
        this.fotoPerfilId = fotoPerfilId;
        this.nombreUsuario = nombreUsuario;
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.telefonoMovil = telefonoMovil;
        this.fechaNacimiento = fechaNacimiento;
       // this.usuarioClientes = usuarioClientes;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Integer getFotoPerfilId() {
        return fotoPerfilId;
    }

    public void setFotoPerfilId(Integer fotoPerfilId) {
        this.fotoPerfilId = fotoPerfilId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
   }
//
//    public List<Object> getUsuarioClientes() {
//        return usuarioClientes;
//    }
//
//    public void setUsuarioClientes(List<Object> usuarioClientes) {
//        this.usuarioClientes = usuarioClientes;
//    }

}