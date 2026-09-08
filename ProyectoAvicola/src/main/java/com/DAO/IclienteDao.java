package com.DAO;

import com.model.Cliente;
import java.util.List;

public interface IclienteDao {
    List<Cliente> listAll();
    boolean add(Cliente cliente);
    boolean update(Cliente cliente);
    boolean delete(int id);
    Cliente findById(int id);
}
