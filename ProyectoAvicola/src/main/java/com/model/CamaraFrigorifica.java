package com.model;

public class CamaraFrigorifica {
    private int idCamara;
    private int capacidad;
    private String estadoCadenaFrio;

    public CamaraFrigorifica() {}

    public CamaraFrigorifica(int idCamara, int capacidad, String estadoCadenaFrio) {
        this.idCamara = idCamara;
        this.capacidad = capacidad;
        this.estadoCadenaFrio = estadoCadenaFrio;
    }

    public int getIdCamara() { return idCamara; }
    public void setIdCamara(int idCamara) { this.idCamara = idCamara; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getEstadoCadenaFrio() { return estadoCadenaFrio; }
    public void setEstadoCadenaFrio(String estadoCadenaFrio) { this.estadoCadenaFrio = estadoCadenaFrio; }
}
