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
                    
                    // Corregido: Mapeo de totalFacturado desde la BD
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
        // p_id_cliente_venta = 45, p_total_facturado = 46, p_fecha_venta = 47
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");
            cs.setInt(45, venta.getIdCliente());
            
            // Corregido con tus getters reales
            cs.setDouble(46, venta.getTotalFacturado());
            
            if (venta.getFechaVenta() != null) {
                cs.setTimestamp(47, Timestamp.valueOf(venta.getFechaVenta()));
            } else {
                cs.setNull(47, java.sql.Types.TIMESTAMP);
            }
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Venta venta) {
        // p_id = 2, p_id_cliente_venta = 45, p_total_facturado = 46, p_fecha_venta = 47
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "ventas");
            cs.setInt(2, venta.getIdVenta());
            cs.setInt(45, venta.getIdCliente());
            
            // Corregido con tus getters reales
            cs.setDouble(46, venta.getTotalFacturado());
            
            if (venta.getFechaVenta() != null) {
                cs.setTimestamp(47, Timestamp.valueOf(venta.getFechaVenta()));
            } else {
                cs.setNull(47, java.sql.Types.TIMESTAMP);
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
        // En Buscar_por_id: p_id_venta es el decimoquinto parámetro (posición 15)
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(15, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    v = new Venta();
                    v.setIdVenta(rs.getInt("id_venta"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    
                    // Corregido: Mapeo de totalFacturado desde la BD
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

