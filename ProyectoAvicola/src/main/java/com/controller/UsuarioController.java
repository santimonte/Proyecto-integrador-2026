package com.controller;


import com.DAO.*;
import com.factory.DaoFactory;
import com.model.Usuario;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

	private final IusuarioDao usuarioDao = com.factory.DaoFactory.getUsuarioDao();

    private final Gson gson = new Gson();

    // Método auxiliar para configurar los encabezados CORS
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    // --- MANEJO DE PREFLIGHT REQUEST (OBLIGATORIO PARA CORS) ---
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

 // --- READ (Soporta listar todos, buscar por ID o verificar sesión) ---
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String accion = request.getParameter("accion");
            String idParam = request.getParameter("id");

            // 1. Si la acción es verificar sesión
            if ("sesion".equals(accion)) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("usuarioLogueado") != null) {
                    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(this.gson.toJson(usuarioLogueado)); // Devuelve UN ÚNICO objeto JSON del usuario
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.print("{\"error\": \"No hay sesión activa\"}");
                }
            } 
            // 2. Si se pasa un ID específico
            else if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Usuario usuario = usuarioDao.findById(id);

                if (usuario != null) {
                    out.print(this.gson.toJson(usuario));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\": \"Usuario no encontrado\"}");
                }
            } 
            // 3. Listar todos los usuarios
            else {
                List<Usuario> listaUsuarios = usuarioDao.listAll();
                out.print(this.gson.toJson(listaUsuarios));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

 // --- CREATE / LOGIN (Maneja registro y login según la acción) ---
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            com.google.gson.JsonObject jsonObj = gson.fromJson(reader, com.google.gson.JsonObject.class);

            if (jsonObj != null && jsonObj.has("accion") && "login".equals(jsonObj.get("accion").getAsString())) {
                String email = jsonObj.get("email").getAsString();
                String contrasena = jsonObj.get("contrasena").getAsString();

                Usuario usuarioValido = usuarioDao.validarLogin(email, contrasena);

                if (usuarioValido != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogueado", usuarioValido);

                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("{\"exito\": true, \"mensaje\": \"Login exitoso\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.print("{\"exito\": false, \"error\": \"Email o contraseña incorrectos\"}");
                }
            } else {
                Usuario nuevoUsuario = gson.fromJson(jsonObj, Usuario.class);
                boolean creado = usuarioDao.add(nuevoUsuario);

                if (creado) {
                    response.setStatus(HttpServletResponse.SC_CREATED); 
                    out.print("{\"exito\": true, \"mensaje\": \"Usuario creado con éxito\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"exito\": false, \"error\": \"No se pudo crear el usuario\"}");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"exito\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    // --- UPDATE (Actualizar usuario existente) ---
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            Usuario usuarioActualizar = this.gson.fromJson(reader, Usuario.class);

            boolean actualizado = usuarioDao.update(usuarioActualizar);

            if (actualizado) {
                out.print("{\"mensaje\": \"Usuario actualizado con éxito\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"No se pudo actualizar el usuario\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    // --- DELETE (Eliminar usuario pasando el ?id=X) ---
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                boolean eliminado = usuarioDao.delete(id);

                if (eliminado) {
                    out.print("{\"mensaje\": \"Usuario eliminado con éxito\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\": \"No se pudo eliminar el usuario\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"Falta el parámetro 'id' en la URL\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
