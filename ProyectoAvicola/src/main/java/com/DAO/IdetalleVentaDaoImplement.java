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
                    
                    // Corregido: lee el decimal de la base como double
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
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");
            cs.setInt(12, detalleVenta.getIdVenta());
            cs.setInt(13, detalleVenta.getIdProducto());
            cs.setInt(14, detalleVenta.getCantidad());
            
            // Corregido: setea el parámetro usando double
            cs.setDouble(15, detalleVenta.getPrecioUnitario());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(DetalleVenta detalleVenta) {
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "detalles_ventas");
            cs.setInt(2, detalleVenta.getIdDetalleVenta());
            cs.setInt(12, detalleVenta.getIdVenta());
            cs.setInt(13, detalleVenta.getIdProducto());
            cs.setInt(14, detalleVenta.getCantidad());
            
            // Corregido: cambia setBigDecimal por setDouble
            cs.setDouble(15, detalleVenta.getPrecioUnitario());
            
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
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(4, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    d = new DetalleVenta();
                    d.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    d.setIdVenta(rs.getInt("id_venta"));
                    d.setIdProducto(rs.getInt("id_producto"));
                    d.setCantidad(rs.getInt("cantidad"));
                    
                    // Corregido: lee como double
                    d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}
