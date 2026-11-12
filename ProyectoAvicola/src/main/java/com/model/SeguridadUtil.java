package com.model;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class SeguridadUtil {

    // Convierte cualquier contraseña en un hash SHA-256 seguro
    public static String hashearPassword(String passwordPlana) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(passwordPlana.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}