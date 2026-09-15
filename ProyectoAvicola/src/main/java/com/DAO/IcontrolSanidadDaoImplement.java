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
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");
            cs.setInt(7, controlSanidad.getIdLote());
            cs.setInt(8, controlSanidad.getIdUsuarioVeterinario());
            
            if (controlSanidad.getFechaControl() != null) {
                cs.setTimestamp(9, Timestamp.valueOf(controlSanidad.getFechaControl()));
            } else {
                cs.setNull(9, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(10, controlSanidad.getCantidadBajas());
            cs.setString(11, controlSanidad.getObservacionesVeterinarias());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ControlSanidad controlSanidad) {
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "controles_sanidad");
            cs.setInt(2, controlSanidad.getIdControl());
            cs.setInt(7, controlSanidad.getIdLote());
            cs.setInt(8, controlSanidad.getIdUsuarioVeterinario());
            
            if (controlSanidad.getFechaControl() != null) {
                cs.setTimestamp(9, Timestamp.valueOf(controlSanidad.getFechaControl()));
            } else {
                cs.setNull(9, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(10, controlSanidad.getCantidadBajas());
            cs.setString(11, controlSanidad.getObservacionesVeterinarias());
            
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
        String sql = "{CALL Buscar_por_id(NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(3, id);
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
