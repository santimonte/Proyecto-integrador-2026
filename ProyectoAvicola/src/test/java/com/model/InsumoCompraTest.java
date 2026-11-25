package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class InsumoCompraTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        InsumoCompra objeto = new InsumoCompra(7, 7, "valorPrueba", 7, FECHA);
        assertEquals(7, objeto.getIdCompra());
        assertEquals(7, objeto.getIdProveedor());
        assertEquals("valorPrueba", objeto.getDescripcionInsumo());
        assertEquals(7, objeto.getCantidad());
        assertEquals(FECHA, objeto.getFechaCompra());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        InsumoCompra objeto = new InsumoCompra();
        objeto.setIdCompra(7);
        assertEquals(7, objeto.getIdCompra());
        objeto.setIdProveedor(7);
        assertEquals(7, objeto.getIdProveedor());
        objeto.setDescripcionInsumo("valorPrueba");
        assertEquals("valorPrueba", objeto.getDescripcionInsumo());
        objeto.setCantidad(7);
        assertEquals(7, objeto.getCantidad());
        objeto.setFechaCompra(FECHA);
        assertEquals(FECHA, objeto.getFechaCompra());
    }
}
