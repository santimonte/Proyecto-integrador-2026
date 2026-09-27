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
            
            cs.setString(1, "mediciones_sensores");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    MedicionSensor m = new MedicionSensor();
                    m.setIdMedicion(rs.getInt("id_medicion"));
                    m.setIdSensor(rs.getInt("id_sensor"));
                    
                    // Corregido con tus setters reales
                    m.setValorRegistrado(rs.getDouble("valor_lectura"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_lectura");
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
        // p_id_sensor_medicion = 30, p_id_lote_medicion = 31 (le mandamos NULL ya que no está en tu modelo), p_valor_lectura = 32, p_fecha_lectura = 33
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "mediciones_sensores");
            cs.setInt(30, medicionSensor.getIdSensor());
            
            // Corregido con tus getters reales
            cs.setDouble(32, medicionSensor.getValorRegistrado());
            
            if (medicionSensor.getFechaMedicion() != null) {
                cs.setTimestamp(33, Timestamp.valueOf(medicionSensor.getFechaMedicion()));
            } else {
                cs.setNull(33, java.sql.Types.TIMESTAMP);
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
        // p_id = 2, p_id_sensor_medicion = 30, p_id_lote_medicion = 31 (NULL), p_valor_lectura = 32, p_fecha_lectura = 33
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "mediciones_sensores");
            cs.setInt(2, medicionSensor.getIdMedicion());
            cs.setInt(30, medicionSensor.getIdSensor());
            
            // Corregido con tus getters reales
            cs.setDouble(32, medicionSensor.getValorRegistrado());
            
            if (medicionSensor.getFechaMedicion() != null) {
                cs.setTimestamp(33, Timestamp.valueOf(medicionSensor.getFechaMedicion()));
            } else {
                cs.setNull(33, java.sql.Types.TIMESTAMP);
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
            
            cs.setString(1, "mediciones_sensores");
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
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(9, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    m = new MedicionSensor();
                    m.setIdMedicion(rs.getInt("id_medicion"));
                    m.setIdSensor(rs.getInt("id_sensor"));
                    
                    // Corregido con tus setters reales
                    m.setValorRegistrado(rs.getDouble("valor_lectura"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_lectura");
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

