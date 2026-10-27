package com.DAO;

import com.model.DetalleVenta;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdetalleVentaDaoImplement implements IdetalleVentaDao {

    @Override
    public List<DetalleVenta> listAll() {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta d = new DetalleVenta();
                    d.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    d.setIdVenta(rs.getInt("id_venta"));
                    d.setIdProducto(rs.getInt("id_producto"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    lista.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(DetalleVenta detalleVenta) {
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");          // 1º '?'
            cs.setInt(2, detalleVenta.getIdVenta());       // 2º '?' (Antes tenías 12)
            cs.setInt(3, detalleVenta.getIdProducto());    // 3º '?' (Antes tenías 13)
            cs.setInt(4, detalleVenta.getCantidad());      // 4º '?' (Antes tenías 14)
            cs.setDouble(5, detalleVenta.getPrecioUnitario()); // 5º '?' (Antes tenías 15)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(DetalleVenta detalleVenta) {
        // Esta cadena contiene exactamente 6 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");               // 1º '?'
            cs.setInt(2, detalleVenta.getIdDetalleVenta());     // 2º '?'
            cs.setInt(3, detalleVenta.getIdVenta());            // 3º '?' (Antes tenías 12)
            cs.setInt(4, detalleVenta.getIdProducto());         // 4º '?' (Antes tenías 13)
            cs.setInt(5, detalleVenta.getCantidad());           // 5º '?' (Antes tenías 14)
            cs.setDouble(6, detalleVenta.getPrecioUnitario());  // 6º '?' (Antes tenías 15)
            
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
            
            cs.setString(1, "detalles_ventas");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DetalleVenta findById(int id) {
        DetalleVenta d = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    d = new DetalleVenta();
                    d.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    d.setIdVenta(rs.getInt("id_venta"));
                    d.setIdProducto(rs.getInt("id_producto"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}