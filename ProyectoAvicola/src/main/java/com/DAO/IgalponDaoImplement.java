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
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones");
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
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones");
            cs.setString(20, galpon.getNombreGalpon());
            cs.setString(21, galpon.getCapacidadMaxima());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Galpon galpon) {
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "galpones");
            cs.setInt(2, galpon.getIdGalpon());
            cs.setString(20, galpon.getNombreGalpon());
            cs.setString(21, galpon.getCapacidadMaxima());
            
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
            
            cs.setString(1, "galpones");
            cs.setInt(2, id);
            
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
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(6, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    g = new Galpon(); // Corregido el typo acá
                    g.setIdGalpon(rs.getInt("id_galpon"));
                    g.setNombreGalpon(rs.getString("nombre_galpon"));
                    g.setCapacidadMaxima(rs.getString("capacidad_maxima"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}


