package com.model;

import java.time.LocalDateTime;

public class Faena {
    private int idFaena;
    private int idLote;
    private LocalDateTime fechaFaena;
    private int cantidadAvesFaenadas;
    private double pesoTotalObtenido;

    public Faena() {}

    public Faena(int idFaena, int idLote, LocalDateTime fechaFaena,
                 int cantidadAvesFaenadas, double pesoTotalObtenido) {
        this.idFaena = idFaena;
        this.idLote = idLote;
        this.fechaFaena = fechaFaena;
        this.cantidadAvesFaenadas = cantidadAvesFaenadas;
        this.pesoTotalObtenido = pesoTotalObtenido;
    }

    public int getIdFaena() { return idFaena; }
    public void setIdFaena(int idFaena) { this.idFaena = idFaena; }

    public int getIdLote() { return idLote; }
    public void setIdLote(int idLote) { this.idLote = idLote; }

    public LocalDateTime getFechaFaena() { return fechaFaena; }
    public void setFechaFaena(LocalDateTime fechaFaena) { this.fechaFaena = fechaFaena; }

    public int getCantidadAvesFaenadas() { return cantidadAvesFaenadas; }
    public void setCantidadAvesFaenadas(int cantidadAvesFaenadas) { this.cantidadAvesFaenadas = cantidadAvesFaenadas; }

    public double getPesoTotalObtenido() { return pesoTotalObtenido; }
    public void setPesoTotalObtenido(double pesoTotalObtenido) { this.pesoTotalObtenido = pesoTotalObtenido; }
}
