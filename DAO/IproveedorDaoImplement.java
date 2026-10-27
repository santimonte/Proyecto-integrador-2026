package com.DAO;

import com.model.Proveedor;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IproveedorDaoImplement implements IproveedorDao {

    @Override
    public List<Proveedor> listAll() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "proveedores");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Proveedor p = new Proveedor();
                    p.setIdProveedor(rs.getInt("id_proveedor"));
                    p.setTelefono(rs.getString("telefono"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Proveedor proveedor) {
        // Esta cadena contiene exactamente 2 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "proveedores");          // 1º '?'
            cs.setString(2, proveedor.getTelefono()); // 2º '?' (Antes tenías 38)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Proveedor proveedor) {
        // Esta cadena contiene exactamente 3 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "proveedores");           // 1º '?'
            cs.setInt(2, proveedor.getIdProveedor()); // 2º '?'
            cs.setString(3, proveedor.getTelefono());  // 3º '?' (Antes tenías 38)
            
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
            
            cs.setString(1, "proveedores");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Proveedor findById(int id) {
        Proveedor p = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "proveedores");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    p = new Proveedor();
                    p.setIdProveedor(rs.getInt("id_proveedor"));
                    p.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}