package com.DAO;

import com.model.Sensor;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IsensorDaoImplement implements IsensorDao {

    @Override
    public List<Sensor> listAll() {
        List<Sensor> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Sensor s = new Sensor();
                    s.setIdSensor(rs.getInt("id_sensor"));
                    s.setTipoSensor(rs.getString("tipo_sensor"));
                    s.setUbicacion(rs.getString("ubicacion"));
                    lista.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Sensor sensor) {
        // Ponemos los '?' en las posiciones exactas 1, 39 y 40 correspondientes a la SP original de tu .sql
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores"); // 1º '?' -> p_tabla_destino
            cs.setString(2, sensor.getTipoSensor()); // 2º '?' -> p_tipo_sensor
            cs.setString(3, sensor.getUbicacion());  // 3º '?' -> p_ubicacion
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Sensor sensor) {
        // En actualizar de tu .sql las variables están en: p_tabla_destino (1), p_id (2), p_tipo_sensor (39) y p_ubicacion (40)
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores");            // 1º '?'
            cs.setInt(2, sensor.getIdSensor());     // 2º '?'
            cs.setString(3, sensor.getTipoSensor()); // 3º '?'
            cs.setString(4, sensor.getUbicacion());  // 4º '?'
            
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
            
            cs.setString(1, "sensores");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Sensor findById(int id) {
        Sensor s = null;
        // CORREGIDO: Mapeado al nuevo estándar de tu SP optimizada de dos parámetros Buscar_por_id(?, ?)
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    s = new Sensor();
                    s.setIdSensor(rs.getInt("id_sensor"));
                    s.setTipoSensor(rs.getString("tipo_sensor"));
                    s.setUbicacion(rs.getString("ubicacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}

