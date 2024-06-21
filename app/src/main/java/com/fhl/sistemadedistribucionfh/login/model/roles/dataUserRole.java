package com.fhl.sistemadedistribucionfh.login.model.roles;

import com.google.gson.annotations.SerializedName;

public class dataUserRole {

    @SerializedName("nombre")
    
    private String nombre;
    @SerializedName("rfc")
    
    private String rfc;
    @SerializedName("identificacion")
    
    private String identificacion;
    @SerializedName("tipoPerfilesId")
    
    private Integer tipoPerfilesId;
    @SerializedName("centroDistribucionesId")
    
    private Integer centroDistribucionesId;
    @SerializedName("nss")
    
    private Object nss;
    @SerializedName("correoElectronico")
    
    private String correoElectronico;
    @SerializedName("telefono")
    
    private Object telefono;
    @SerializedName("imei")
    
    private String imei;
    @SerializedName("estado")
    
    private Boolean estado;
    @SerializedName("comentarios")
    
    private String comentarios;
    @SerializedName("ultimoAcceso")
    
    private String ultimoAcceso;
    @SerializedName("usuario")
    
    private String usuario;
    @SerializedName("tipoPerfiles")
    
    private TipoPerfiles tipoPerfiles;
    @SerializedName("id")
    
    private Integer id;
    @SerializedName("eliminado")
    
    private Boolean eliminado;
    @SerializedName("esNuevo")
    
    private Boolean esNuevo;


    public dataUserRole(String nombre, String rfc, String identificacion, Integer tipoPerfilesId, Integer centroDistribucionesId, Object nss, String correoElectronico, Object telefono, String imei, Boolean estado, String comentarios, String ultimoAcceso, String usuario, TipoPerfiles tipoPerfiles, Integer id, Boolean eliminado, Boolean esNuevo) {
        super();
        this.nombre = nombre;
        this.rfc = rfc;
        this.identificacion = identificacion;
        this.tipoPerfilesId = tipoPerfilesId;
        this.centroDistribucionesId = centroDistribucionesId;
        this.nss = nss;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.imei = imei;
        this.estado = estado;
        this.comentarios = comentarios;
        this.ultimoAcceso = ultimoAcceso;
        this.usuario = usuario;
        this.tipoPerfiles = tipoPerfiles;
        this.id = id;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getTipoPerfilesId() {
        return tipoPerfilesId;
    }

    public void setTipoPerfilesId(Integer tipoPerfilesId) {
        this.tipoPerfilesId = tipoPerfilesId;
    }

    public Integer getCentroDistribucionesId() {
        return centroDistribucionesId;
    }

    public void setCentroDistribucionesId(Integer centroDistribucionesId) {
        this.centroDistribucionesId = centroDistribucionesId;
    }

    public Object getNss() {
        return nss;
    }

    public void setNss(Object nss) {
        this.nss = nss;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(String ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public TipoPerfiles getTipoPerfiles() {
        return tipoPerfiles;
    }

    public void setTipoPerfiles(TipoPerfiles tipoPerfiles) {
        this.tipoPerfiles = tipoPerfiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Boolean getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        this.esNuevo = esNuevo;
    }
}
