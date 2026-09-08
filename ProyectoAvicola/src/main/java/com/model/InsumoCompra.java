package com.model;

import java.time.LocalDateTime;

public class InsumoCompra {
    private int idCompra;
    private int idProveedor;
    private String descripcionInsumo;
    private int cantidad;
    private LocalDateTime fechaCompra;

    public InsumoCompra() {}

    public InsumoCompra(int idCompra, int idProveedor, String descripcionInsumo,
                        int cantidad, LocalDateTime fechaCompra) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.descripcionInsumo = descripcionInsumo;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getDescripcionInsumo() { return descripcionInsumo; }
    public void setDescripcionInsumo(String descripcionInsumo) { this.descripcionInsumo = descripcionInsumo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
}
