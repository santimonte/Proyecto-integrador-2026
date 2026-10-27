package com.DAO;

import com.model.Venta;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IventaDaoImplement implements IventaDao {

    @Override
    public List<Venta> listAll() {
        List<Venta> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Venta v = new Venta();
                    v.setIdVenta(rs.getInt("id_venta"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setTotalFacturado(rs.getDouble("total_facturado"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_venta");
                    if (ts != null) {
                        v.setFechaVenta(ts.toLocalDateTime());
                    }
                    lista.add(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Venta venta) {
        // Esta cadena contiene exactamente 4 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");              // 1º '?'
            cs.setInt(2, venta.getIdCliente());       // 2º '?' (Antes tenías 45)
            
            if (venta.getFechaVenta() != null) {
                cs.setTimestamp(3, Timestamp.valueOf(venta.getFechaVenta())); // 3º '?' (Antes p_fecha_venta en pos 47)
            } else {
                cs.setNull(3, java.sql.Types.TIMESTAMP);
            }
            
            cs.setDouble(4, venta.getTotalFacturado()); // 4º '?' (Antes p_total_facturado en pos 46)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Venta venta) {
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");               // 1º '?'
            cs.setInt(2, venta.getIdVenta());         // 2º '?'
            cs.setInt(3, venta.getIdCliente());       // 3º '?' (Antes tenías 45)
            
            if (venta.getFechaVenta() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(venta.getFechaVenta())); // 4º '?' (Antes p_fecha_venta en pos 47)
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.setDouble(5, venta.getTotalFacturado()); // 5º '?' (Antes p_total_facturado en pos 46)
            
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
            
            cs.setString(1, "ventas");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Venta findById(int id) {
        Venta v = null;
        // CORREGIDO: Mapeado al nuevo estándar optimizado de la SP unificada Buscar_por_id(?, ?)
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    v = new Venta();
                    v.setIdVenta(rs.getInt("id_venta"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setTotalFacturado(rs.getDouble("total_facturado"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_venta");
                    if (ts != null) {
                        v.setFechaVenta(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
}

