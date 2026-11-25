package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GalponTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Galpon objeto = new Galpon(7, "valorPrueba", "valorPrueba");
        assertEquals(7, objeto.getIdGalpon());
        assertEquals("valorPrueba", objeto.getNombreGalpon());
        assertEquals("valorPrueba", objeto.getCapacidadMaxima());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Galpon objeto = new Galpon();
        objeto.setIdGalpon(7);
        assertEquals(7, objeto.getIdGalpon());
        objeto.setNombreGalpon("valorPrueba");
        assertEquals("valorPrueba", objeto.getNombreGalpon());
        objeto.setCapacidadMaxima("valorPrueba");
        assertEquals("valorPrueba", objeto.getCapacidadMaxima());
    }
}
