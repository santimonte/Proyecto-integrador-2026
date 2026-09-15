package com.DAO;

import com.model.CamaraFrigorifica;
import com.conexion.Conexion; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IcamaraFrigorificaDaoImplement implements IcamaraFrigorificaDao {

    @Override
    public List<CamaraFrigorifica> listAll() {
        List<CamaraFrigorifica> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "camaras_frigorificas");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    CamaraFrigorifica c = new CamaraFrigorifica();
                    c.setIdCamara(rs.getInt("id_camara"));
                    c.setCapacidad(rs.getInt("capacidad"));
                    c.setEstadoCadenaFrio(rs.getString("estado_cadena_frio"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(CamaraFrigorifica camara) {
        String sql = "{CALL insertar(?, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "camaras_frigorificas");
            cs.setInt(3, camara.getCapacidad());
            cs.setString(4, camara.getEstadoCadenaFrio());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(CamaraFrigorifica camara) {
        String sql = "{CALL actualizar(?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "camaras_frigorificas");
            cs.setInt(2, camara.getIdCamara());
            cs.setInt(3, camara.getCapacidad());
            cs.setString(4, camara.getEstadoCadenaFrio());
            
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
            
            cs.setString(1, "camaras_frigorificas");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CamaraFrigorifica findById(int id) {
        CamaraFrigorifica c = null;
        String sql = "{CALL Buscar_por_id(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    c = new CamaraFrigorifica();
                    c.setIdCamara(rs.getInt("id_camara"));
                    c.setCapacidad(rs.getInt("capacidad"));
                    c.setEstadoCadenaFrio(rs.getString("estado_cadena_frio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
