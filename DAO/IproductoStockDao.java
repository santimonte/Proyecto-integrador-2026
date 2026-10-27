package com.DAO;

import com.model.ProductoStock;
import java.util.List;

public interface IproductoStockDao {
    List<ProductoStock> listAll();
    boolean add(ProductoStock productoStock);
    boolean update(ProductoStock productoStock);
    boolean delete(int id);
    ProductoStock findById(int id);
}
