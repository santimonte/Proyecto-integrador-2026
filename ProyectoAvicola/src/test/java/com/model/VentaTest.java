package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class VentaTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        Venta objeto = new Venta(7, 7, FECHA, 12.5);
        assertEquals(7, objeto.getIdVenta());
        assertEquals(7, objeto.getIdCliente());
        assertEquals(FECHA, objeto.getFechaVenta());
        assertEquals(12.5, objeto.getTotalFacturado(), 0.0001);
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        Venta objeto = new Venta();
        objeto.setIdVenta(7);
        assertEquals(7, objeto.getIdVenta());
        objeto.setIdCliente(7);
        assertEquals(7, objeto.getIdCliente());
        objeto.setFechaVenta(FECHA);
        assertEquals(FECHA, objeto.getFechaVenta());
        objeto.setTotalFacturado(12.5);
        assertEquals(12.5, objeto.getTotalFacturado(), 0.0001);
    }
}
