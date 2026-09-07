package com.model;

public class Usuario {
    private int idUsuario;
    private String email;
    private String contrasena;
    private int idRol;

    public Usuario() {}

    public Usuario(int idUsuario, String email, String contrasena, int idRol) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
}
