package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importamos tu clase Usuario para poder usarla como objeto
import com.model.Usuario;

@WebServlet("/usuarios/prueba")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Configuramos las cabeceras para responder en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // 1. Instanciamos tu clase Usuario con datos reales usando su constructor
        Usuario miUsuario = new Usuario(67, "messi@gmail.com", "micho123", 1);
        
        // 2. Construimos el JSON de manera dinámica usando los GETTERS de tu objeto
        String usuarioJson = "{"
                + "\"idUsuario\": " + miUsuario.getIdUsuario() + ","
                + "\"email\": \"" + miUsuario.getEmail() + "\","
                + "\"contrasena\": \"" + miUsuario.getContrasena() + "\","
                + "\"idRol\": " + miUsuario.getIdRol()
                + "}";
                
        // 3. Enviamos la respuesta hacia Postman
        PrintWriter out = response.getWriter();
        out.print(usuarioJson);
        out.flush();
    }
}
