package com.DAO;

import com.model.MedicionSensor;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImedicionSensorDaoImplement implements ImedicionSensorDao {

    @Override
    public List<MedicionSensor> listAll() {
        List<MedicionSensor> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            // CORREGIDO: "medicion_sensores" en singular para que coincida exactamente con tu tabla
            cs.setString(1, "medicion_sensores"); 
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    MedicionSensor m = new MedicionSensor();
                    m.setIdMedicion(rs.getInt("id_medicion"));
                    m.setIdSensor(rs.getInt("id_sensor"));
                    m.setValorRegistrado(rs.getDouble("valor_registrado"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_medicion");
                    if (ts != null) {
                        m.setFechaMedicion(ts.toLocalDateTime());
                    }
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(MedicionSensor medicionSensor) {
        // Esta cadena contiene exactamente 4 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "medicion_sensores");              // 1º '?'
            cs.setInt(2, medicionSensor.getIdSensor());        // 2º '?'
            cs.setDouble(3, medicionSensor.getValorRegistrado()); // 3º '?'
            
            if (medicionSensor.getFechaMedicion() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(medicionSensor.getFechaMedicion())); // 4º '?'
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(MedicionSensor medicionSensor) {
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "medicion_sensores");              // 1º '?'
            cs.setInt(2, medicionSensor.getIdMedicion());      // 2º '?'
            cs.setInt(3, medicionSensor.getIdSensor());        // 3º '?'
            cs.setDouble(4, medicionSensor.getValorRegistrado()); // 4º '?'
            
            if (medicionSensor.getFechaMedicion() != null) {
                cs.setTimestamp(5, Timestamp.valueOf(medicionSensor.getFechaMedicion())); // 5º '?'
            } else {
                cs.setNull(5, java.sql.Types.TIMESTAMP);
            }
            
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
            
            cs.setString(1, "medicion_sensores");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MedicionSensor findById(int id) {
        MedicionSensor m = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "medicion_sensores");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    m = new MedicionSensor();
                    m.setIdMedicion(rs.getInt("id_medicion"));
                    m.setIdSensor(rs.getInt("id_sensor"));
                    m.setValorRegistrado(rs.getDouble("valor_registrado"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_medicion");
                    if (ts != null) {
                        m.setFechaMedicion(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
}


