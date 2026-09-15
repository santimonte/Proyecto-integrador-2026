package com.DAO;

import com.model.LoteAves;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IloteAvesDaoImplement implements IloteAvesDao {

    @Override
    public List<LoteAves> listAll() {
        List<LoteAves> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    LoteAves l = new LoteAves();
                    l.setIdLote(rs.getInt("id_lote"));
                    l.setIdGalpon(rs.getInt("id_galpon"));
                    
                    // Corregido con los métodos reales de tu clase LoteAves
                    l.setCantidadInicial(rs.getInt("cantidad_aves"));
                    l.setRazaGenetica(rs.getString("raza_genetica"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_ingreso");
                    if (ts != null) {
                        l.setFechaIngreso(ts.toLocalDateTime());
                    }
                    lista.add(l);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(LoteAves loteAves) {
        // p_id_galpon_lote = 26, p_cantidad_aves_lote = 27, p_fecha_ingreso_lote = 28, p_raza_genetica = 29
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");
            cs.setInt(26, loteAves.getIdGalpon());
            cs.setInt(27, loteAves.getCantidadInicial());
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(28, Timestamp.valueOf(loteAves.getFechaIngreso()));
            } else {
                cs.setNull(28, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(29, loteAves.getRazaGenetica());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LoteAves loteAves) {
        // p_id = 2, p_id_galpon_lote = 26, p_cantidad_aves_lote = 27, p_fecha_ingreso_lote = 28, p_raza_genetica = 29
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");
            cs.setInt(2, loteAves.getIdLote());
            cs.setInt(26, loteAves.getIdGalpon());
            cs.setInt(27, loteAves.getCantidadInicial());
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(28, Timestamp.valueOf(loteAves.getFechaIngreso()));
            } else {
                cs.setNull(28, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(29, loteAves.getRazaGenetica());
            
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
            
            cs.setString(1, "lotes_aves");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LoteAves findById(int id) {
        LoteAves l = null;
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(8, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    l = new LoteAves();
                    l.setIdLote(rs.getInt("id_lote"));
                    l.setIdGalpon(rs.getInt("id_galpon"));
                    
                    l.setCantidadInicial(rs.getInt("cantidad_aves"));
                    l.setRazaGenetica(rs.getString("raza_genetica"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_ingreso");
                    if (ts != null) {
                        l.setFechaIngreso(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
}
