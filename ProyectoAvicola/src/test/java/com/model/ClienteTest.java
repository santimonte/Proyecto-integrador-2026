package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Cliente objeto = new Cliente(7, 7, "valorPrueba");
        assertEquals(7, objeto.getIdCliente());
        assertEquals(7, objeto.getIdUsuario());
        assertEquals("valorPrueba", objeto.getTelefono());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Cliente objeto = new Cliente();
        objeto.setIdCliente(7);
        assertEquals(7, objeto.getIdCliente());
        objeto.setIdUsuario(7);
        assertEquals(7, objeto.getIdUsuario());
        objeto.setTelefono("valorPrueba");
        assertEquals("valorPrueba", objeto.getTelefono());
    }
}
