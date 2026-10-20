package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DAO.IusuarioDao;
import com.DAO.IusuarioDaoImplement;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model.Usuario;

@WebServlet("/UsuarioServlet")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IusuarioDao usuarioDao = new IusuarioDaoImplement();
    private Gson gson = new Gson();

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirigir a doPost para procesar todo unificado
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setCorsHeaders(response);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();

        try {
            BufferedReader reader = request.getReader();
            JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);

            if (jsonRequest == null || !jsonRequest.has("accion")) {
                jsonResponse.addProperty("exito", false);
                jsonResponse.addProperty("mensaje", "Parámetro 'accion' requerido en el JSON");
                out.print(gson.toJson(jsonResponse));
                return;
            }

            String accion = jsonRequest.get("accion").getAsString();

            switch (accion) {
                case "login": {
                    String email = jsonRequest.get("email").getAsString();
                    String contrasena = jsonRequest.get("contrasena").getAsString();
                    Usuario user = usuarioDao.validarLogin(email, contrasena);

                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioLogueado", user);
                        jsonResponse.addProperty("exito", true);
                        jsonResponse.addProperty("mensaje", "Login exitoso");
                        jsonResponse.add("usuario", gson.toJsonTree(user));
                    } else {
                        jsonResponse.addProperty("exito", false);
                        jsonResponse.addProperty("mensaje", "Credenciales incorrectas");
                    }
                    break;
                }

                case "register":
                case "registro":
                case "agregar": {
                    String email = jsonRequest.get("email").getAsString();
                    String contrasena = jsonRequest.get("contrasena").getAsString();
                    int idRol = jsonRequest.has("idRol") ? jsonRequest.get("idRol").getAsInt() : 1;

                    Usuario nuevo = new Usuario();
                    nuevo.setEmail(email);
                    nuevo.setContrasena(contrasena);
                    nuevo.setIdRol(idRol);

                    boolean ok = usuarioDao.add(nuevo);
                    jsonResponse.addProperty("exito", ok);
                    jsonResponse.addProperty("mensaje", ok ? "Usuario creado exitosamente" : "Error al guardar el usuario");
                    break;
                }

                case "listar": {
                    List<Usuario> lista = usuarioDao.listAll(); // Asegúrate de tener getAll() en tu DAO
                    jsonResponse.addProperty("exito", true);
                    jsonResponse.add("usuarios", gson.toJsonTree(lista));
                    break;
                }

                case "obtener": {
                    int idUsuario = jsonRequest.get("idUsuario").getAsInt();
                    Usuario u = usuarioDao.findById(idUsuario); // getById() en tu DAO
                    if (u != null) {
                        jsonResponse.addProperty("exito", true);
                        jsonResponse.add("usuario", gson.toJsonTree(u));
                    } else {
                        jsonResponse.addProperty("exito", false);
                        jsonResponse.addProperty("mensaje", "Usuario no encontrado");
                    }
                    break;
                }

                case "actualizar": {
                    int idUsuario = jsonRequest.get("idUsuario").getAsInt();
                    String email = jsonRequest.get("email").getAsString();
                    String contrasena = jsonRequest.get("contrasena").getAsString();
                    int idRol = jsonRequest.has("idRol") ? jsonRequest.get("idRol").getAsInt() : 1;

                    Usuario u = new Usuario();
                    u.setIdUsuario(idUsuario);
                    u.setEmail(email);
                    u.setContrasena(contrasena);
                    u.setIdRol(idRol);

                    boolean ok = usuarioDao.update(u); // update() en tu DAO
                    jsonResponse.addProperty("exito", ok);
                    jsonResponse.addProperty("mensaje", ok ? "Usuario actualizado correctamente" : "Error al actualizar");
                    break;
                }

                case "eliminar": {
                    int idUsuario = jsonRequest.get("idUsuario").getAsInt();
                    boolean ok = usuarioDao.delete(idUsuario); // delete() en tu DAO
                    jsonResponse.addProperty("exito", ok);
                    jsonResponse.addProperty("mensaje", ok ? "Usuario eliminado correctamente" : "Error al eliminar");
                    break;
                }

                default:
                    jsonResponse.addProperty("exito", false);
                    jsonResponse.addProperty("mensaje", "Acción no válida: " + accion);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.addProperty("exito", false);
            jsonResponse.addProperty("mensaje", "Error procesando petición: " + e.getMessage());
        }

        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
}