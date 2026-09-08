package com.model;

import java.time.LocalDateTime;

public class ControlSanidad {
    private int idControl;
    private int idLote;
    private int idUsuarioVeterinario;
    private LocalDateTime fechaControl;
    private int cantidadBajas;
    private String observacionesVeterinarias;

    public ControlSanidad() {}

    public ControlSanidad(int idControl, int idLote, int idUsuarioVeterinario,
                          LocalDateTime fechaControl, int cantidadBajas,
                          String observacionesVeterinarias) {
        this.idControl = idControl;
        this.idLote = idLote;
        this.idUsuarioVeterinario = idUsuarioVeterinario;
        this.fechaControl = fechaControl;
        this.cantidadBajas = cantidadBajas;
        this.observacionesVeterinarias = observacionesVeterinarias;
    }

    public int getIdControl() { return idControl; }
    public void setIdControl(int idControl) { this.idControl = idControl; }

    public int getIdLote() { return idLote; }
    public void setIdLote(int idLote) { this.idLote = idLote; }

    public int getIdUsuarioVeterinario() { return idUsuarioVeterinario; }
    public void setIdUsuarioVeterinario(int idUsuarioVeterinario) { this.idUsuarioVeterinario = idUsuarioVeterinario; }

    public LocalDateTime getFechaControl() { return fechaControl; }
    public void setFechaControl(LocalDateTime fechaControl) { this.fechaControl = fechaControl; }

    public int getCantidadBajas() { return cantidadBajas; }
    public void setCantidadBajas(int cantidadBajas) { this.cantidadBajas = cantidadBajas; }

    public String getObservacionesVeterinarias() { return observacionesVeterinarias; }
    public void setObservacionesVeterinarias(String observacionesVeterinarias) { this.observacionesVeterinarias = observacionesVeterinarias; }
}
