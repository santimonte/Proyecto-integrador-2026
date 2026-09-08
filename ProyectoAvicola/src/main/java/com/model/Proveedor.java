package com.model;

public class Proveedor {
    private int idProveedor;
    private String telefono;

    public Proveedor() {}

    public Proveedor(int idProveedor, String telefono) {
        this.idProveedor = idProveedor;
        this.telefono = telefono;
    }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
