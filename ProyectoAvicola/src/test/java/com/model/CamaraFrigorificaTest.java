package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CamaraFrigorificaTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        CamaraFrigorifica objeto = new CamaraFrigorifica(7, 7, "valorPrueba");
        assertEquals(7, objeto.getIdCamara());
        assertEquals(7, objeto.getCapacidad());
        assertEquals("valorPrueba", objeto.getEstadoCadenaFrio());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        CamaraFrigorifica objeto = new CamaraFrigorifica();
        objeto.setIdCamara(7);
        assertEquals(7, objeto.getIdCamara());
        objeto.setCapacidad(7);
        assertEquals(7, objeto.getCapacidad());
        objeto.setEstadoCadenaFrio("valorPrueba");
        assertEquals("valorPrueba", objeto.getEstadoCadenaFrio());
    }
}
