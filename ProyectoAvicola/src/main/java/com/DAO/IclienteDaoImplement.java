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
        // En insertar: p_tabla_destino es 1, p_id_usuario es 5, p_telefono_cliente es 6
        String sql = "{CALL insertar(?, NULL, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");
            cs.setInt(5, cliente.getIdUsuario());
            cs.setString(6, cliente.getTelefono());
            
            cs.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        // En actualizar: p_tabla_destino es 1, p_id es 2, p_id_usuario es 5, p_telefono_cliente es 6
        String sql = "{CALL actualizar(?, ?, NULL, NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, "clientes");
            cs.setInt(2, cliente.getIdCliente());
            cs.setInt(5, cliente.getIdUsuario());
            cs.setString(6, cliente.getTelefono());
            
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
        // En Buscar_por_id: p_id_cliente es el segundo parámetro
        String sql = "{CALL Buscar_por_id(NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";
        
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
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
