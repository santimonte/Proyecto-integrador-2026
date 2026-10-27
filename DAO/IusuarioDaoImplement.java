package com.DAO;

import com.model.Usuario;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IusuarioDaoImplement implements IusuarioDao {

    @Override
    public List<Usuario> listAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "usuarios");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setEmail(rs.getString("email"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setIdRol(rs.getInt("id_rol"));
                    lista.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Usuario usuario) {
        // Consulta SQL directa a la tabla usuarios
        String sql = "INSERT INTO usuarios (id_rol, email, contrasena) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Si el objeto usuario no trae un idRol asignado, usamos el rol 1 (Cliente)
            int idRol = (usuario.getIdRol() > 0) ? usuario.getIdRol() : 1;

            ps.setInt(1, idRol); // Envía 1 a la columna id_rol
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());

            ps.executeUpdate(); // Ejecutamos la inserción
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        // CORREGIDO: Tiene exactamente los 46 casilleros de tu base de datos.
        // ? (1) | ? (2) | 38 NULLs | ? (3) | ? (4) | ? (5) | 3 NULLs finales.
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            // Los índices numéricos representan ESTRICTAMENTE el orden de los signos "?" de izquierda a derecha
            cs.setString(1, "usuarios");              // 1er "?" -> p_tabla_destino
            cs.setInt(2, usuario.getIdUsuario());     // 2do "?" -> p_id
            cs.setString(3, usuario.getEmail());       // 3er "?" -> p_email
            cs.setString(4, usuario.getContrasena());  // 4to "?" -> p_contrasena
            cs.setInt(5, usuario.getIdRol());         // 5to "?" -> p_id_rol
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("=== ERROR REAL EN UPDATE ===");
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        String sql = "{CALL eliminar(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "usuarios");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario findById(int id) {
        Usuario u = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "usuarios");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setEmail(rs.getString("email"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setIdRol(rs.getInt("id_rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    
    @Override
    public Usuario validarLogin(String email, String contrasena) {
        String sql = "{CALL validar_login(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, email);
            cs.setString(2, contrasena);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setEmail(rs.getString("email"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setIdRol(rs.getInt("id_rol"));
                    return u;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al validar el login", e);
        }
        
        return null; // Retorna null si las credenciales no coinciden
    }

      
}


