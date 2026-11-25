package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Sensor objeto = new Sensor(7, "valorPrueba", "valorPrueba");
        assertEquals(7, objeto.getIdSensor());
        assertEquals("valorPrueba", objeto.getTipoSensor());
        assertEquals("valorPrueba", objeto.getUbicacion());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Sensor objeto = new Sensor();
        objeto.setIdSensor(7);
        assertEquals(7, objeto.getIdSensor());
        objeto.setTipoSensor("valorPrueba");
        assertEquals("valorPrueba", objeto.getTipoSensor());
        objeto.setUbicacion("valorPrueba");
        assertEquals("valorPrueba", objeto.getUbicacion());
    }
}
