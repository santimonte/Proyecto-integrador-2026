package com.model;

public class Sensor {
    private int idSensor;
    private String tipoSensor;
    private String ubicacion;

    public Sensor() {}

    public Sensor(int idSensor, String tipoSensor, String ubicacion) {
        this.idSensor = idSensor;
        this.tipoSensor = tipoSensor;
        this.ubicacion = ubicacion;
    }

    public int getIdSensor() { return idSensor; }
    public void setIdSensor(int idSensor) { this.idSensor = idSensor; }

    public String getTipoSensor() { return tipoSensor; }
    public void setTipoSensor(String tipoSensor) { this.tipoSensor = tipoSensor; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
