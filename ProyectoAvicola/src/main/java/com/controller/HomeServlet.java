package com.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/testdb")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    resp.setContentType("text/plain;charset=UTF-8");
	    
	    try {
	        Connection conn = com.conexion.Conexion.getInstancia().getConexion();
	        if (conn != null) {
	            resp.getWriter().println("¡Conexión con la DB exitosa!");
	            conn.close(); 
	        } else {
	            resp.getWriter().println("No se pudo conectar con la DB (Objeto Connection es nulo).");
	        }
	    } catch (Exception e) {
	        resp.getWriter().println("Fallo total de conexión. Error detallado:");
	        e.printStackTrace(resp.getWriter());
	    }
	}
}
