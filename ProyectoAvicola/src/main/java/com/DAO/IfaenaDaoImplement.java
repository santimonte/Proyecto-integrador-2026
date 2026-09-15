package com.DAO;

import com.model.Faena; // Asegurate de que tu modelo se llame Faena
import com.conexion.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IfaenaDaoImplement implements IfaenaDao {

    @Override
    public List<Faena> listAll() {
        List<Faena> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faena f = new Faena();
                    f.setIdFaena(rs.getInt("id_faena"));
                    f.setIdLote(rs.getInt("id_lote"));
                    
                    // Conversión limpia para LocalDateTime
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
        // En insertar: 
        // p_id_lote_faena = 16, p_fecha_faena = 17, p_cantidad_aves_faenadas = 18, p_peso_total_obtenido = 19
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            cs.setInt(16, faena.getIdLote());
            
            if (faena.getFechaFaena() != null) {
                cs.setTimestamp(17, Timestamp.valueOf(faena.getFechaFaena()));
            } else {
                cs.setNull(17, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(18, faena.getCantidadAvesFaenadas());
            cs.setDouble(19, faena.getPesoTotalObtenido());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Faena faena) {
        // En actualizar: 
        // p_id = 2, p_id_lote_faena = 16, p_fecha_faena = 17, p_cantidad_aves_faenadas = 18, p_peso_total_obtenido = 19
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "faenas");
            cs.setInt(2, faena.getIdFaena());
            cs.setInt(16, faena.getIdLote());
            
            if (faena.getFechaFaena() != null) {
                cs.setTimestamp(17, Timestamp.valueOf(faena.getFechaFaena()));
            } else {
                cs.setNull(17, java.sql.Types.TIMESTAMP);
            }
            
            cs.setInt(18, faena.getCantidadAvesFaenadas());
            cs.setDouble(19, faena.getPesoTotalObtenido());
            
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
        // En Buscar_por_id: p_id_faena es el quinto parámetro
        String sql = "{CALL Buscar_por_id(NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(5, id);
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
