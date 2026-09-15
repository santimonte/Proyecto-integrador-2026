package com.DAO;

import com.model.InsumoCompra; // Tu modelo real tal como está en tu proyecto
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IinsumoDaoImplement implements IinsumoDao {

    @Override
    public List<InsumoCompra> listAll() {
        List<InsumoCompra> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    InsumoCompra i = new InsumoCompra();
                    i.setIdCompra(rs.getInt("id_compra"));
                    i.setIdProveedor(rs.getInt("id_proveedor"));
                    i.setDescripcionInsumo(rs.getString("descripcion_insumo"));
                    i.setCantidad(rs.getInt("cantidad"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_compra");
                    if (ts != null) {
                        i.setFechaCompra(ts.toLocalDateTime());
                    }
                    lista.add(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(InsumoCompra insumo) {
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras");
            cs.setInt(22, insumo.getIdProveedor());
            cs.setString(23, insumo.getDescripcionInsumo());
            cs.setInt(24, insumo.getCantidad());
            
            if (insumo.getFechaCompra() != null) {
                cs.setTimestamp(25, Timestamp.valueOf(insumo.getFechaCompra()));
            } else {
                cs.setNull(25, java.sql.Types.TIMESTAMP);
            }
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(InsumoCompra insumo) {
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras");
            cs.setInt(2, insumo.getIdCompra());
            cs.setInt(22, insumo.getIdProveedor());
            cs.setString(23, insumo.getDescripcionInsumo());
            cs.setInt(24, insumo.getCantidad());
            
            if (insumo.getFechaCompra() != null) {
                cs.setTimestamp(25, Timestamp.valueOf(insumo.getFechaCompra()));
            } else {
                cs.setNull(25, java.sql.Types.TIMESTAMP);
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
            
            cs.setString(1, "insumos_compras");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public InsumoCompra findById(int id) {
        InsumoCompra i = null;
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(7, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    i = new InsumoCompra();
                    i.setIdCompra(rs.getInt("id_compra"));
                    i.setIdProveedor(rs.getInt("id_proveedor"));
                    i.setDescripcionInsumo(rs.getString("descripcion_insumo"));
                    i.setCantidad(rs.getInt("cantidad"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_compra");
                    if (ts != null) {
                        i.setFechaCompra(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
