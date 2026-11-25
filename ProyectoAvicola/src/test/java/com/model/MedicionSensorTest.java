package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class MedicionSensorTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        MedicionSensor objeto = new MedicionSensor(7, 7, 12.5, FECHA);
        assertEquals(7, objeto.getIdMedicion());
        assertEquals(7, objeto.getIdSensor());
        assertEquals(12.5, objeto.getValorRegistrado(), 0.0001);
        assertEquals(FECHA, objeto.getFechaMedicion());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        MedicionSensor objeto = new MedicionSensor();
        objeto.setIdMedicion(7);
        assertEquals(7, objeto.getIdMedicion());
        objeto.setIdSensor(7);
        assertEquals(7, objeto.getIdSensor());
        objeto.setValorRegistrado(12.5);
        assertEquals(12.5, objeto.getValorRegistrado(), 0.0001);
        objeto.setFechaMedicion(FECHA);
        assertEquals(FECHA, objeto.getFechaMedicion());
    }
}
