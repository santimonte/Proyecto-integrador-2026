package com.model;

import java.time.LocalDateTime;

public class LoteAves {
    private int idLote;
    private int idGalpon;
    private LocalDateTime fechaIngreso;
    private int cantidadInicial;
    private String razaGenetica;

    public LoteAves() {}

    public LoteAves(int idLote, int idGalpon, LocalDateTime fechaIngreso,
                    int cantidadInicial, String razaGenetica) {
        this.idLote = idLote;
        this.idGalpon = idGalpon;
        this.fechaIngreso = fechaIngreso;
        this.cantidadInicial = cantidadInicial;
        this.razaGenetica = razaGenetica;
    }

    public int getIdLote() { return idLote; }
    public void setIdLote(int idLote) { this.idLote = idLote; }

    public int getIdGalpon() { return idGalpon; }
    public void setIdGalpon(int idGalpon) { this.idGalpon = idGalpon; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public int getCantidadInicial() { return cantidadInicial; }
    public void setCantidadInicial(int cantidadInicial) { this.cantidadInicial = cantidadInicial; }

    public String getRazaGenetica() { return razaGenetica; }
    public void setRazaGenetica(String razaGenetica) { this.razaGenetica = razaGenetica; }
}
