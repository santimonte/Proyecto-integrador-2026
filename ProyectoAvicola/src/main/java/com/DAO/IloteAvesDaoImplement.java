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
        
        try (Connection con = Conexion.getInstancia().getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    LoteAves l = new LoteAves();
                    l.setIdLote(rs.getInt("id_lote"));
                    l.setIdGalpon(rs.getInt("id_galpon"));
                    
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
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");                 
            cs.setInt(2, loteAves.getIdGalpon());          
            cs.setInt(3, loteAves.getCantidadInicial());    
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(loteAves.getFechaIngreso())); 
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(5, loteAves.getRazaGenetica());  
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LoteAves loteAves) {
        
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "lotes_aves");                  
            cs.setInt(2, loteAves.getIdLote());          
            cs.setInt(3, loteAves.getIdGalpon());         
            cs.setInt(4, loteAves.getCantidadInicial());    
            
            if (loteAves.getFechaIngreso() != null) {
                cs.setTimestamp(5, Timestamp.valueOf(loteAves.getFechaIngreso())); 
            } else {
                cs.setNull(5, java.sql.Types.TIMESTAMP);
            }
            
            cs.setString(6, loteAves.getRazaGenetica());   
            
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
        
        try (Connection con = Conexion.getInstancia().getConexion();
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
        
        try (Connection con = Conexion.getInstancia().getConexion();
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
