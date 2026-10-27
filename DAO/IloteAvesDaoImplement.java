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
                    
                    // Corregido con los nombres reales de las columnas en tu script SQL
                    l.setCantidadInicial(rs.getInt("cantidad_inicial"));
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
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");                  // 1º '?'
            cs.setInt(2, loteAves.getIdGalpon());          // 2º '?' (Antes tenías 26)
            cs.setInt(3, loteAves.getCantidadInicial());    // 3º '?' (Antes tenías 27)
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(loteAves.getFechaIngreso())); // 4º '?' (Antes tenías 28)
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(5, loteAves.getRazaGenetica());   // 5º '?' (Antes tenías 29)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LoteAves loteAves) {
        // Esta cadena contiene exactamente 6 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");                  // 1º '?'
            cs.setInt(2, loteAves.getIdLote());            // 2º '?'
            cs.setInt(3, loteAves.getIdGalpon());          // 3º '?' (Antes tenías 26)
            cs.setInt(4, loteAves.getCantidadInicial());    // 4º '?' (Antes tenías 27)
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(5, Timestamp.valueOf(loteAves.getFechaIngreso())); // 5º '?' (Antes tenías 28)
            } else {
                cs.setNull(5, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(6, loteAves.getRazaGenetica());   // 6º '?' (Antes tenías 29)
            
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
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    l = new LoteAves();
                    l.setIdLote(rs.getInt("id_lote"));
                    l.setIdGalpon(rs.getInt("id_galpon"));
                    l.setCantidadInicial(rs.getInt("cantidad_inicial"));
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
