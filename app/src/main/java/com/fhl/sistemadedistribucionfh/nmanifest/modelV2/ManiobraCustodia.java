package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManiobraCustodia {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("Tipo")
    @Expose
    private Integer tipo;
    @SerializedName("TicketId")
    @Expose
    private Integer ticketId;
    @SerializedName("Comentarios")
    @Expose
    private String comentarios;
    @SerializedName("Ticket")
    @Expose
    private String ticket;
    @SerializedName("AuxiliaresSeleccionados")
    @Expose
    private Object AuxiliaresSeleccionados;
    @SerializedName("EditDetalle")
    @Expose
    private Boolean editDetalle;
    @SerializedName("CantidadError")
    @Expose
    private Boolean cantidadError;
    @SerializedName("CantidadErrorTexto")
    @Expose
    private String cantidadErrorTexto;
    @SerializedName("TipoError")
    @Expose
    private Boolean tipoError;
    @SerializedName("TipoErrorTexto")
    @Expose
    private String tipoErrorTexto;
    @SerializedName("TicketError")
    @Expose
    private Boolean TicketError;
    @SerializedName("TicketErrorTexto")
    @Expose
    private String TicketErrorTexto;

    public ManiobraCustodia(Integer id, Integer cantidad, Integer tipo, Integer ticketId, String comentarios, String ticket, Object auxiliaresSeleccionados, Boolean editDetalle, Boolean cantidadError, String cantidadErrorTexto, Boolean tipoError, String tipoErrorTexto, Boolean ticketError, String ticketErrorTexto) {
        super();
        this.id = id;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.ticketId = ticketId;
        this.comentarios = comentarios;
        this.ticket = ticket;
        AuxiliaresSeleccionados = auxiliaresSeleccionados;
        this.editDetalle = editDetalle;
        this.cantidadError = cantidadError;
        this.cantidadErrorTexto = cantidadErrorTexto;
        this.tipoError = tipoError;
        this.tipoErrorTexto = tipoErrorTexto;
        TicketError = ticketError;
        TicketErrorTexto = ticketErrorTexto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Object getAuxiliaresSeleccionados() {
        return AuxiliaresSeleccionados;
    }

    public void setAuxiliaresSeleccionados(Object auxiliaresSeleccionados) {
        AuxiliaresSeleccionados = auxiliaresSeleccionados;
    }

    public Boolean getEditDetalle() {
        return editDetalle;
    }

    public void setEditDetalle(Boolean editDetalle) {
        this.editDetalle = editDetalle;
    }

    public Boolean getCantidadError() {
        return cantidadError;
    }

    public void setCantidadError(Boolean cantidadError) {
        this.cantidadError = cantidadError;
    }

    public String getCantidadErrorTexto() {
        return cantidadErrorTexto;
    }

    public void setCantidadErrorTexto(String cantidadErrorTexto) {
        this.cantidadErrorTexto = cantidadErrorTexto;
    }

    public Boolean getTipoError() {
        return tipoError;
    }

    public void setTipoError(Boolean tipoError) {
        this.tipoError = tipoError;
    }

    public String getTipoErrorTexto() {
        return tipoErrorTexto;
    }

    public void setTipoErrorTexto(String tipoErrorTexto) {
        this.tipoErrorTexto = tipoErrorTexto;
    }

    public Boolean getTicketError() {
        return TicketError;
    }

    public void setTicketError(Boolean ticketError) {
        TicketError = ticketError;
    }

    public String getTicketErrorTexto() {
        return TicketErrorTexto;
    }

    public void setTicketErrorTexto(String ticketErrorTexto) {
        TicketErrorTexto = ticketErrorTexto;
    }
}
