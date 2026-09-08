package com.model;

import java.time.LocalDateTime;

public class ProductoStock {
    private int idProducto;
    private int idCamara;
    private String nombreProducto;
    private int stockDisponible;
    private LocalDateTime fechaEnvasado;

    public ProductoStock() {}

    public ProductoStock(int idProducto, int idCamara, String nombreProducto,
                         int stockDisponible, LocalDateTime fechaEnvasado) {
        this.idProducto = idProducto;
        this.idCamara = idCamara;
        this.nombreProducto = nombreProducto;
        this.stockDisponible = stockDisponible;
        this.fechaEnvasado = fechaEnvasado;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getIdCamara() { return idCamara; }
    public void setIdCamara(int idCamara) { this.idCamara = idCamara; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int stockDisponible) { this.stockDisponible = stockDisponible; }

    public LocalDateTime getFechaEnvasado() { return fechaEnvasado; }
    public void setFechaEnvasado(LocalDateTime fechaEnvasado) { this.fechaEnvasado = fechaEnvasado; }
}
