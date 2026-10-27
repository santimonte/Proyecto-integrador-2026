package com.DAO;

import com.model.Proveedor;
import java.util.List;

public interface IproveedorDao {
    List<Proveedor> listAll();
    boolean add(Proveedor proveedor);
    boolean update(Proveedor proveedor);
    boolean delete(int id);
    Proveedor findById(int id);
}
