package com.DAO;

import com.model.Galpon;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IgalponDaoImplement implements IgalponDao {

    @Override
    public List<Galpon> listAll() {
        List<Galpon> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}"; // Tiene 1 solo '?'
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones"); // Índice 1 (Primer y único '?')
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Galpon g = new Galpon();
                    g.setIdGalpon(rs.getInt("id_galpon"));
                    g.setNombreGalpon(rs.getString("nombre_galpon"));
                    g.setCapacidadMaxima(rs.getString("capacidad_maxima"));
                    lista.add(g);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Galpon galpon) {
        // Esta cadena tiene exactamente 3 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones");         // 1º '?'
            cs.setString(2, galpon.getNombreGalpon());   // 2º '?' (Antes tenías 20)
            cs.setString(3, galpon.getCapacidadMaxima()); // 3º '?' (Antes tenías 21)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Galpon galpon) {
        // Esta cadena tiene exactamente 4 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones");         // 1º '?'
            cs.setInt(2, galpon.getIdGalpon());          // 2º '?'
            cs.setString(3, galpon.getNombreGalpon());   // 3º '?' (Antes tenías 20)
            cs.setString(4, galpon.getCapacidadMaxima()); // 4º '?' (Antes tenías 21)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "{CALL eliminar(?, ?)}"; // Tiene 2 '?'
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones"); // 1º '?'
            cs.setInt(2, id);           // 2º '?'
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Galpon findById(int id) {
        Galpon g = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, "galpones");
            cs.setInt(2, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    g = new Galpon();
                    g.setIdGalpon(rs.getInt("id_galpon"));
                    g.setNombreGalpon(rs.getString("nombre_galpon"));
                    g.setCapacidadMaxima(rs.getString("capacidad_maxima"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return g;
    }
}


