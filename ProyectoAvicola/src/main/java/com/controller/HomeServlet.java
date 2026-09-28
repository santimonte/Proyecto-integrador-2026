/*//conexion servidor

package com.controller;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        
        resp.getWriter().println("¡Hola mundo desde HomeServlet!");
        resp.getWriter().println("El servidor Tomcat está respondiendo correctamente.");
    }
}

*/

//conexion base de datos
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
	    // Definimos el tipo de respuesta como texto plano
	    resp.setContentType("text/plain;charset=UTF-8");
	    
	    try {
	        Connection conn = com.conexion.Conexion.getConexion();
	        if (conn != null) {
	            resp.getWriter().println("¡Conexión con la DB exitosa!");
	            conn.close(); // Siempre es bueno cerrar la conexión
	        } else {
	            resp.getWriter().println("No se pudo conectar con la DB (Objeto Connection es nulo).");
	        }
	    } catch (Exception e) {
	        // Esto imprimirá el error real en tu navegador web
	        resp.getWriter().println("Fallo total de conexión. Error detallado:");
	        e.printStackTrace(resp.getWriter());
	    }
	}
}
