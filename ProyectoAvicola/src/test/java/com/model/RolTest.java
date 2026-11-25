package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RolTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Rol objeto = new Rol(7, "valorPrueba");
        assertEquals(7, objeto.getIdRol());
        assertEquals("valorPrueba", objeto.getNombreRol());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Rol objeto = new Rol();
        objeto.setIdRol(7);
        assertEquals(7, objeto.getIdRol());
        objeto.setNombreRol("valorPrueba");
        assertEquals("valorPrueba", objeto.getNombreRol());
    }
}
