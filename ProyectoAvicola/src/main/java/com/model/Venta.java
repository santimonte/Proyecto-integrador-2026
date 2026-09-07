package com.model;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private int idCliente;
    private LocalDateTime fechaVenta;
    private double totalFacturado;

    public Venta() {}

    public Venta(int idVenta, int idCliente, LocalDateTime fechaVenta, double totalFacturado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.totalFacturado = totalFacturado;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public double getTotalFacturado() { return totalFacturado; }
    public void setTotalFacturado(double totalFacturado) { this.totalFacturado = totalFacturado; }
}
