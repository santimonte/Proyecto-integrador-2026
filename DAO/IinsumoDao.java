package com.DAO;

import com.model.InsumoCompra;
import java.util.List;

public interface IinsumoDao {
    List<InsumoCompra> listAll();
    boolean add(InsumoCompra insumo);
    boolean update(InsumoCompra insumo);
    boolean delete(int id);
    InsumoCompra findById(int id);
}
