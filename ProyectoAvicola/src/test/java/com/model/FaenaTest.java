package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class FaenaTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Faena objeto = new Faena(7, 7, FECHA, 7, 12.5);
        assertEquals(7, objeto.getIdFaena());
        assertEquals(7, objeto.getIdLote());
        assertEquals(FECHA, objeto.getFechaFaena());
        assertEquals(7, objeto.getCantidadAvesFaenadas());
        assertEquals(12.5, objeto.getPesoTotalObtenido(), 0.0001);
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Faena objeto = new Faena();
        objeto.setIdFaena(7);
        assertEquals(7, objeto.getIdFaena());
        objeto.setIdLote(7);
        assertEquals(7, objeto.getIdLote());
        objeto.setFechaFaena(FECHA);
        assertEquals(FECHA, objeto.getFechaFaena());
        objeto.setCantidadAvesFaenadas(7);
        assertEquals(7, objeto.getCantidadAvesFaenadas());
        objeto.setPesoTotalObtenido(12.5);
        assertEquals(12.5, objeto.getPesoTotalObtenido(), 0.0001);
    }
}
