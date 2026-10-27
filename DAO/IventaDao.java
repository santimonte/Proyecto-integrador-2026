package com.DAO;

import com.model.Venta;
import java.util.List;

public interface IventaDao {
    List<Venta> listAll();
    boolean add(Venta venta);
    boolean update(Venta venta);
    boolean delete(int id);
    Venta findById(int id);
}
