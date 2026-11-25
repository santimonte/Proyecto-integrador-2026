package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Usuario objeto = new Usuario(7, "valorPrueba", "valorPrueba", 7);
        assertEquals(7, objeto.getIdUsuario());
        assertEquals("valorPrueba", objeto.getEmail());
        assertEquals("valorPrueba", objeto.getContrasena());
        assertEquals(7, objeto.getIdRol());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Usuario objeto = new Usuario();
        objeto.setIdUsuario(7);
        assertEquals(7, objeto.getIdUsuario());
        objeto.setEmail("valorPrueba");
        assertEquals("valorPrueba", objeto.getEmail());
        objeto.setContrasena("valorPrueba");
        assertEquals("valorPrueba", objeto.getContrasena());
        objeto.setIdRol(7);
        assertEquals(7, objeto.getIdRol());
    }
}
