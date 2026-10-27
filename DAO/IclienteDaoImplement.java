package com.DAO;

import com.model.Cliente;
import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IclienteDaoImplement implements IclienteDao {

    @Override
    public List<Cliente> listAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "{CALL listar_tabla(?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setTelefono(rs.getString("telefono"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean add(Cliente cliente) {
        // Esta cadena contiene exactamente 3 signos de interrogación '?'
        String sql = "{CALL insertar(?, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");            // 1º '?'
            cs.setInt(2, cliente.getIdUsuario());    // 2º '?' (Antes tenías 5)
            cs.setString(3, cliente.getTelefono());   // 3º '?' (Antes tenías 6)
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        // Esta cadena contiene exactamente 4 signos de interrogación '?'
        String sql = "{CALL actualizar(?, ?, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");            // 1º '?'
            cs.setInt(2, cliente.getIdCliente());    // 2º '?'
            cs.setInt(3, cliente.getIdUsuario());    // 3º '?' (Antes tenías 5)
            cs.setString(4, cliente.getTelefono());   // 4º '?' (Antes tenías 6)
            
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
            
            cs.setString(1, "clientes");
            cs.setInt(2, id);
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cliente findById(int id) {
        Cliente c = null;
        String sql = "{CALL Buscar_por_id(?, ?)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");
            cs.setInt(2, id);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


}
