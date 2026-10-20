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
        // En actualizar: p_id = 2, p_id_rol_usuario = 42, p_email_usuario = 43, p_contrasena_usuario = 44
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "usuarios");
            cs.setInt(2, usuario.getIdUsuario());
            cs.setInt(42, usuario.getIdRol());
            cs.setString(43, usuario.getEmail());
            cs.setString(44, usuario.getContrasena());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
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
    public Usuario findById(int id) { // Corregido: Retorna Usuario en lugar de Rol
        Usuario u = null;
        // En Buscar_por_id: p_id_usuario es el decimocuarto parámetro (posición 14)
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(14, id);
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
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasena = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setIdRol(rs.getInt("id_rol"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuario; // Si coincide, devuelve el objeto usuario; si no, devuelve null (credenciales incorrectas)
    }

    
}


