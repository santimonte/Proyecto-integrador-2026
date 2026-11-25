package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class ProductoStockTest {
    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 11, 16, 10, 30);

    @Test
    void constructorCompletoAsignaTodosLosAtributos() {
        ProductoStock objeto = new ProductoStock(7, 7, "valorPrueba", 7, FECHA);
        assertEquals(7, objeto.getIdProducto());
        assertEquals(7, objeto.getIdCamara());
        assertEquals("valorPrueba", objeto.getNombreProducto());
        assertEquals(7, objeto.getStockDisponible());
        assertEquals(FECHA, objeto.getFechaEnvasado());
    }

    @Test
    void constructorVacioYSettersPermitenAsignarAtributos() {
        ProductoStock objeto = new ProductoStock();
        objeto.setIdProducto(7);
        assertEquals(7, objeto.getIdProducto());
        objeto.setIdCamara(7);
        assertEquals(7, objeto.getIdCamara());
        objeto.setNombreProducto("valorPrueba");
        assertEquals("valorPrueba", objeto.getNombreProducto());
        objeto.setStockDisponible(7);
        assertEquals(7, objeto.getStockDisponible());
        objeto.setFechaEnvasado(FECHA);
        assertEquals(FECHA, objeto.getFechaEnvasado());
    }
}
