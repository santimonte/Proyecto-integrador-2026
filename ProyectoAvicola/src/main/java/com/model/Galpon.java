package com.model;

public class Galpon {
    private int idGalpon;
    private String nombreGalpon;
    private String capacidadMaxima;

    public Galpon() {}

    public Galpon(int idGalpon, String nombreGalpon, String capacidadMaxima) {
        this.idGalpon = idGalpon;
        this.nombreGalpon = nombreGalpon;
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getIdGalpon() { return idGalpon; }
    public void setIdGalpon(int idGalpon) { this.idGalpon = idGalpon; }

    public String getNombreGalpon() { return nombreGalpon; }
    public void setNombreGalpon(String nombreGalpon) { this.nombreGalpon = nombreGalpon; }

    public String getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(String capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
}
