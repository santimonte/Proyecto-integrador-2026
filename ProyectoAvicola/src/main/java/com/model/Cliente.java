package com.model;

public class Cliente {
    private int idCliente;
    private int idUsuario;
    private String telefono;

    public Cliente() {}

    public Cliente(int idCliente, int idUsuario, String telefono) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.telefono = telefono;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
