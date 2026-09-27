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
        // En insertar: p_tipo_sensor = 40, p_ubicacion_sensor = 41
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores");
            cs.setString(40, sensor.getTipoSensor());
            cs.setString(41, sensor.getUbicacion());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Sensor sensor) {
        // En actualizar: p_id = 2, p_tipo_sensor = 40, p_ubicacion_sensor = 41
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "sensores");
            cs.setInt(2, sensor.getIdSensor());
            cs.setString(40, sensor.getTipoSensor());
            cs.setString(41, sensor.getUbicacion());
            
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
        // En Buscar_por_id: p_id_sensor es el decimotercer parámetro (posición 13)
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(13, id);
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
