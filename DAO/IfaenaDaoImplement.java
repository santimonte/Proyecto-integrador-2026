package com.DAO;

import com.model.Faena;
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IfaenaDaoImplement implements IfaenaDao {

    @Override
    public List<Faena> listAll() {
        List<Faena> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}"; // 1 '?'
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faena f = new Faena();
                    f.setIdFaena(rs.getInt("id_faena"));
                    f.setIdLote(rs.getInt("id_lote"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_faena");
                    if (ts != null) {
                        f.setFechaFaena(ts.toLocalDateTime());
                    }
                    
                    f.setCantidadAvesFaenadas(rs.getInt("cantidad_aves_faenadas"));
                    f.setPesoTotalObtenido(rs.getDouble("peso_total_obtenido"));
                    lista.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Faena faena) {
        // Esta cadena contiene exactamente 5 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas"); // 1º '?'
            cs.setInt(2, faena.getIdLote()); // 2º '?'
            
            if (faena.getFechaFaena() != null) {
                cs.setTimestamp(3, Timestamp.valueOf(faena.getFechaFaena())); // 3º '?'
            } else {
                cs.setNull(3, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(4, faena.getCantidadAvesFaenadas()); // 4º '?'
            cs.setDouble(5, faena.getPesoTotalObtenido()); // 5º '?'
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Faena faena) {
        // Esta cadena contiene exactamente 6 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas"); // 1º '?'
            cs.setInt(2, faena.getIdFaena()); // 2º '?'
            cs.setInt(3, faena.getIdLote()); // 3º '?'
            
            if (faena.getFechaFaena() != null) {
                cs.setTimestamp(4, Timestamp.valueOf(faena.getFechaFaena())); // 4º '?'
            } else {
                cs.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(5, faena.getCantidadAvesFaenadas()); // 5º '?'
            cs.setDouble(6, faena.getPesoTotalObtenido()); // 6º '?'
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "{CALL eliminar(?, ?)}"; // 2 '?'
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Faena findById(int id) {
        Faena f = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    f = new Faena();
                    f.setIdFaena(rs.getInt("id_faena"));
                    f.setIdLote(rs.getInt("id_lote"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_faena");
                    if (ts != null) {
                        f.setFechaFaena(ts.toLocalDateTime());
                    }
                    
                    f.setCantidadAvesFaenadas(rs.getInt("cantidad_aves_faenadas"));
                    f.setPesoTotalObtenido(rs.getDouble("peso_total_obtenido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

}
