package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseHabilidades {
    @SerializedName("HabilidadesVehiculos")
    public List<Item> habilidadesVehiculos;

    @SerializedName("DocumentosVencimientosVehiculos")
    public List<Item> documentosVencimientosVehiculos;

    @SerializedName("HabilidadesOperadores")
    public List<Item> habilidadesOperadores;

    @SerializedName("DocumentosOperadoresVencimientos")
    public List<Item> documentosOperadoresVencimientos;

    @SerializedName("HabilidadesAuxiliares")
    public List<Item> habilidadesAuxiliares;

    @SerializedName("DocumentosAuxiliaresVencimientos")
    public List<Item> documentosAuxiliaresVencimientos;

    @SerializedName("EvidenciasCargas")
    public List<Item> evidenciasCargas;

    @SerializedName("EvidenciasLlegadas")
    public List<Item> evidenciasLlegadas;

    @SerializedName("ChecklistRequeridos")
    public List<Object> checklistRequeridos;
}
