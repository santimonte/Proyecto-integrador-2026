package com.DAO;

import com.model.ProductoStock;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IproductoStockDaoImplement implements IproductoStockDao {

    @Override
    public List<ProductoStock> listAll() {
        List<ProductoStock> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "productos_stock");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ProductoStock p = new ProductoStock();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setIdCamara(rs.getInt("id_camara"));
                    p.setNombreProducto(rs.getString("nombre_producto"));
                    p.setStockDisponible(rs.getInt("stock_disponible"));
                    
                    // Mapeo limpio de Timestamp a LocalDateTime
                    Timestamp ts = rs.getTimestamp("fecha_envasado");
                    if (ts != null) {
                        p.setFechaEnvasado(ts.toLocalDateTime());
                    }
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(ProductoStock productoStock) {
        // En insertar: p_id_camara_producto = 34, p_nombre_producto = 35, p_stock_disponible = 36, p_fecha_envasado = 37
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "productos_stock");
            cs.setInt(34, productoStock.getIdCamara());
            cs.setString(35, productoStock.getNombreProducto());
            cs.setInt(36, productoStock.getStockDisponible());
            
            if (productoStock.getFechaEnvasado() != null) {
                cs.setTimestamp(37, Timestamp.valueOf(productoStock.getFechaEnvasado()));
            } else {
                cs.setNull(37, java.sql.Types.TIMESTAMP);
            }
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ProductoStock productoStock) {
        // En actualizar: p_id = 2, p_id_camara_producto = 34, p_nombre_producto = 35, p_stock_disponible = 36, p_fecha_envasado = 37
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "productos_stock");
            cs.setInt(2, productoStock.getIdProducto());
            cs.setInt(34, productoStock.getIdCamara());
            cs.setString(35, productoStock.getNombreProducto());
            cs.setInt(36, productoStock.getStockDisponible());
            
            if (productoStock.getFechaEnvasado() != null) {
                cs.setTimestamp(37, Timestamp.valueOf(productoStock.getFechaEnvasado()));
            } else {
                cs.setNull(37, java.sql.Types.TIMESTAMP);
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
            
            cs.setString(1, "productos_stock");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductoStock findById(int id) {
        ProductoStock p = null;
        // En Buscar_por_id: p_id_producto_stock es el décimo parámetro
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(10, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    p = new ProductoStock();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setIdCamara(rs.getInt("id_camara"));
                    p.setNombreProducto(rs.getString("nombre_producto"));
                    p.setStockDisponible(rs.getInt("stock_disponible"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_envasado");
                    if (ts != null) {
                        p.setFechaEnvasado(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}

