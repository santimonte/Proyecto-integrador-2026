package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeguridadUtilTest {

    @Test
    void generaHashSha256Esperado() {
        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
                SeguridadUtil.hashearPassword("123456"));
    }

    @Test
    void mismoTextoGeneraElMismoHash() {
        assertEquals(SeguridadUtil.hashearPassword("clave123"),
                SeguridadUtil.hashearPassword("clave123"));
    }

    @Test
    void contrasenasDistintasGeneranHashesDistintos() {
        assertNotEquals(SeguridadUtil.hashearPassword("clave123"),
                SeguridadUtil.hashearPassword("clave456"));
    }

    @Test
    void hashTiene64CaracteresHexadecimalesYNoDevuelveTextoPlano() {
        String hash = SeguridadUtil.hashearPassword("miContrasena");
        assertEquals(64, hash.length());
        assertTrue(hash.matches("[0-9a-f]{64}"));
        assertNotEquals("miContrasena", hash);
    }
}
