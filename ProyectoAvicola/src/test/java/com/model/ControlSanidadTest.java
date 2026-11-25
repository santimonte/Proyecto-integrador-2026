package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class ControlSanidadTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        ControlSanidad objeto = new ControlSanidad(7, 7, 7, FECHA, 7, "valorPrueba");
        assertEquals(7, objeto.getIdControl());
        assertEquals(7, objeto.getIdLote());
        assertEquals(7, objeto.getIdUsuarioVeterinario());
        assertEquals(FECHA, objeto.getFechaControl());
        assertEquals(7, objeto.getCantidadBajas());
        assertEquals("valorPrueba", objeto.getObservacionesVeterinarias());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        ControlSanidad objeto = new ControlSanidad();
        objeto.setIdControl(7);
        assertEquals(7, objeto.getIdControl());
        objeto.setIdLote(7);
        assertEquals(7, objeto.getIdLote());
        objeto.setIdUsuarioVeterinario(7);
        assertEquals(7, objeto.getIdUsuarioVeterinario());
        objeto.setFechaControl(FECHA);
        assertEquals(FECHA, objeto.getFechaControl());
        objeto.setCantidadBajas(7);
        assertEquals(7, objeto.getCantidadBajas());
        objeto.setObservacionesVeterinarias("valorPrueba");
        assertEquals("valorPrueba", objeto.getObservacionesVeterinarias());
    }
}
