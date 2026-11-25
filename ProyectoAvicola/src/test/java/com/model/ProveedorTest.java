package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProveedorTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Proveedor objeto = new Proveedor(7, "valorPrueba");
        assertEquals(7, objeto.getIdProveedor());
        assertEquals("valorPrueba", objeto.getTelefono());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Proveedor objeto = new Proveedor();
        objeto.setIdProveedor(7);
        assertEquals(7, objeto.getIdProveedor());
        objeto.setTelefono("valorPrueba");
        assertEquals("valorPrueba", objeto.getTelefono());
    }
}
