package com.controller;

import com.DAO.IusuarioDaoImplement;
import com.model.Usuario;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

    private final IusuarioDaoImplement usuarioDao = new IusuarioDaoImplement();
    private final Gson gson = new Gson();

    // --- READ (Soporta listar todos o buscar por ID si se pasa ?id=X) ---
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                // Si viene un ID en la URL, usamos findById
                int id = Integer.parseInt(idParam);
                Usuario usuario = usuarioDao.findById(id);

                if (usuario != null) {
                    out.print(this.gson.toJson(usuario));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\": \"Usuario no encontrado\"}");
                }
            } else {
                // Si no viene ID, listamos todos
                List<Usuario> listaUsuarios = usuarioDao.listAll();
                out.print(this.gson.toJson(listaUsuarios));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    // --- CREATE (Insertar nuevo usuario) ---
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Leer el cuerpo JSON enviado desde Postman
            BufferedReader reader = request.getReader();
            Usuario nuevoUsuario = this.gson.fromJson(reader, Usuario.class);

            // Llamar al método add del DAO
            boolean creado = usuarioDao.add(nuevoUsuario);

            if (creado) {
                response.setStatus(HttpServletResponse.SC_CREATED); // Código 201
                out.print("{\"mensaje\": \"Usuario creado con éxito\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"No se pudo crear el usuario\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    // --- UPDATE (Actualizar usuario existente) ---
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Leer el cuerpo JSON enviado desde Postman
            BufferedReader reader = request.getReader();
            Usuario usuarioActualizar = this.gson.fromJson(reader, Usuario.class);

            // Llamar al método update del DAO
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
