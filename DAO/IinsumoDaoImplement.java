package com.DAO;

import com.model.InsumoCompra;
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
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras"); // 1º '?'
            cs.setInt(2, insumo.getIdProveedor()); // 2º '?' (Antes tenías 22)
            cs.setString(3, insumo.getDescripcionInsumo()); // 3º '?' (Antes tenías 23)
            cs.setInt(4, insumo.getCantidad()); // 4º '?' (Antes tenías 24)
            
            if (insumo.getFechaCompra() != null) {
                cs.setTimestamp(5, Timestamp.valueOf(insumo.getFechaCompra())); // 5º '?' (Antes tenías 25)
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
    public boolean update(InsumoCompra insumo) {
        // Esta cadena contiene exactamente 6 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras"); // 1º '?'
            cs.setInt(2, insumo.getIdCompra()); // 2º '?'
            cs.setInt(3, insumo.getIdProveedor()); // 3º '?' (Antes tenías 22)
            cs.setString(4, insumo.getDescripcionInsumo()); // 4º '?' (Antes tenías 23)
            cs.setInt(5, insumo.getCantidad()); // 5º '?' (Antes tenías 24)
            
            if (insumo.getFechaCompra() != null) {
                cs.setTimestamp(6, Timestamp.valueOf(insumo.getFechaCompra())); // 6º '?' (Antes tenías 25)
            } else {
                cs.setNull(6, java.sql.Types.TIMESTAMP);
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
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "insumos_compras");
            cs.setInt(2, id);
            
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
