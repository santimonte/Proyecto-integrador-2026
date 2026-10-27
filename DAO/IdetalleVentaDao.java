package com.DAO;

import com.model.DetalleVenta;
import java.util.List;

public interface IdetalleVentaDao {
    List<DetalleVenta> listAll();
    boolean add(DetalleVenta detalleVenta);
    boolean update(DetalleVenta detalleVenta);
    boolean delete(int id);
    DetalleVenta findById(int id);
}
