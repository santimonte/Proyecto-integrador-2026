package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetalleVentaTest {
    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        DetalleVenta objeto = new DetalleVenta(7, 7, 7, 7, 12.5);
        assertEquals(7, objeto.getIdDetalleVenta());
        assertEquals(7, objeto.getIdVenta());
        assertEquals(7, objeto.getIdProducto());
        assertEquals(7, objeto.getCantidad());
        assertEquals(12.5, objeto.getPrecioUnitario(), 0.0001);
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        DetalleVenta objeto = new DetalleVenta();
        objeto.setIdDetalleVenta(7);
        assertEquals(7, objeto.getIdDetalleVenta());
        objeto.setIdVenta(7);
        assertEquals(7, objeto.getIdVenta());
        objeto.setIdProducto(7);
        assertEquals(7, objeto.getIdProducto());
        objeto.setCantidad(7);
        assertEquals(7, objeto.getCantidad());
        objeto.setPrecioUnitario(12.5);
        assertEquals(12.5, objeto.getPrecioUnitario(), 0.0001);
    }
}
