package com.DAO;

import com.model.ControlSanidad;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IcontrolSanidadDaoImplement implements IcontrolSanidadDao {

    @Override
    public List<ControlSanidad> listAll() {
        List<ControlSanidad> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ControlSanidad c = new ControlSanidad();
                    c.setIdControl(rs.getInt("id_control"));
                    c.setIdLote(rs.getInt("id_lote"));
                    c.setIdUsuarioVeterinario(rs.getInt("id_usuario_veterinario"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_control");
                    if (ts != null) {
                        c.setFechaControl(ts.toLocalDateTime());
                    }
                    
                    c.setCantidadBajas(rs.getInt("cantidad_bajas"));
                    c.setObservacionesVeterinarias(rs.getString("observaciones_veterinarias"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(ControlSanidad controlSanidad) {
        // Esta cadena contiene exactamente 6 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");             // 1º '?'
            cs.setInt(2, controlSanidad.getIdLote());          // 2º '?' (Antes tenías 7)
            cs.setInt(3, controlSanidad.getIdUsuarioVeterinario()); // 3º '?' (Antes tenías 8)
            
            if (controlSanidad.getFechaControl() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(controlSanidad.getFechaControl())); // 4º '?' (Antes tenías 9)
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(5, controlSanidad.getCantidadBajas()); // 5º '?' (Antes tenías 10)
            cs.setString(6, controlSanidad.getObservacionesVeterinarias()); // 6º '?' (Antes tenías 11)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ControlSanidad controlSanidad) {
        // Esta cadena contiene exactamente 7 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");             // 1º '?'
            cs.setInt(2, controlSanidad.getIdControl());       // 2º '?'
            cs.setInt(3, controlSanidad.getIdLote());          // 3º '?' (Antes tenías 7)
            cs.setInt(4, controlSanidad.getIdUsuarioVeterinario()); // 4º '?' (Antes tenías 8)
            
            if (controlSanidad.getFechaControl() != null) {
                cs.setTimestamp(5, Timestamp.valueOf(controlSanidad.getFechaControl())); // 5º '?' (Antes tenías 9)
            } else {
                cs.setNull(5, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(6, controlSanidad.getCantidadBajas()); // 6º '?' (Antes tenías 10)
            cs.setString(7, controlSanidad.getObservacionesVeterinarias()); // 7º '?' (Antes tenías 11)
            
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
            
            cs.setString(1, "controles_sanidad");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ControlSanidad findById(int id) {
        ControlSanidad c = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    c = new ControlSanidad();
                    c.setIdControl(rs.getInt("id_control"));
                    c.setIdLote(rs.getInt("id_lote"));
                    c.setIdUsuarioVeterinario(rs.getInt("id_usuario_veterinario"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_control");
                    if (ts != null) {
                        c.setFechaControl(ts.toLocalDateTime());
                    }
                    
                    c.setCantidadBajas(rs.getInt("cantidad_bajas"));
                    c.setObservacionesVeterinarias(rs.getString("observaciones_veterinarias"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


}
