package com.model;

import java.time.LocalDateTime;

public class MedicionSensor {
    private int idMedicion;
    private int idSensor;
    private double valorRegistrado;
    private LocalDateTime fechaMedicion;

    public MedicionSensor() {}

    public MedicionSensor(int idMedicion, int idSensor, double valorRegistrado, LocalDateTime fechaMedicion) {
        this.idMedicion = idMedicion;
        this.idSensor = idSensor;
        this.valorRegistrado = valorRegistrado;
        this.fechaMedicion = fechaMedicion;
    }

    public int getIdMedicion() { return idMedicion; }
    public void setIdMedicion(int idMedicion) { this.idMedicion = idMedicion; }

    public int getIdSensor() { return idSensor; }
    public void setIdSensor(int idSensor) { this.idSensor = idSensor; }

    public double getValorRegistrado() { return valorRegistrado; }
    public void setValorRegistrado(double valorRegistrado) { this.valorRegistrado = valorRegistrado; }

    public LocalDateTime getFechaMedicion() { return fechaMedicion; }
    public void setFechaMedicion(LocalDateTime fechaMedicion) { this.fechaMedicion = fechaMedicion; }
}
