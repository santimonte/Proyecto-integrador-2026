package com.DAO;

import com.model.Rol;
import java.util.List;

public interface IrolDao {
    List<Rol> listAll();
    boolean add(Rol rol);
    boolean update(Rol rol);
    boolean delete(int id);
    Rol findById(int id);
}
