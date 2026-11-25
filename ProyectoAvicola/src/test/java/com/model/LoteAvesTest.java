package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class LoteAvesTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        LoteAves objeto = new LoteAves(7, 7, FECHA, 7, "valorPrueba");
        assertEquals(7, objeto.getIdLote());
        assertEquals(7, objeto.getIdGalpon());
        assertEquals(FECHA, objeto.getFechaIngreso());
        assertEquals(7, objeto.getCantidadInicial());
        assertEquals("valorPrueba", objeto.getRazaGenetica());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        LoteAves objeto = new LoteAves();
        objeto.setIdLote(7);
        assertEquals(7, objeto.getIdLote());
        objeto.setIdGalpon(7);
        assertEquals(7, objeto.getIdGalpon());
        objeto.setFechaIngreso(FECHA);
        assertEquals(FECHA, objeto.getFechaIngreso());
        objeto.setCantidadInicial(7);
        assertEquals(7, objeto.getCantidadInicial());
        objeto.setRazaGenetica("valorPrueba");
        assertEquals("valorPrueba", objeto.getRazaGenetica());
    }
}
