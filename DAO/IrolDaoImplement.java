package com.DAO;

import com.model.Rol;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IrolDaoImplement implements IrolDao {

    @Override
    public List<Rol> listAll() {
        List<Rol> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "roles");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Rol r = new Rol();
                    r.setIdRol(rs.getInt("id_rol"));
                    r.setNombreRol(rs.getString("nombre_rol"));
                    lista.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Rol rol) {
        // Esta cadena contiene exactamente 2 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "roles");         // 1º '?'
            cs.setString(2, rol.getNombreRol()); // 2º '?' (Antes tenías 39)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Rol rol) {
        // Esta cadena contiene exactamente 3 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "roles");          // 1º '?'
            cs.setInt(2, rol.getIdRol());      // 2º '?'
            cs.setString(3, rol.getNombreRol()); // 3º '?' (Antes tenías 39)
            
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
            
            cs.setString(1, "roles");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rol findById(int id) {
        Rol r = null;
        // CORREGIDO: Mapeado al nuevo estándar optimizado de la SP unificada Buscar_por_id(?, ?)
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "roles");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    r = new Rol();
                    r.setIdRol(rs.getInt("id_rol"));
                    r.setNombreRol(rs.getString("nombre_rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
