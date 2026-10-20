package com.DAO;

import com.model.Usuario;
import java.util.List;

public interface IusuarioDao {
    List<Usuario> listAll();
    boolean add(Usuario usuario);
    boolean update(Usuario usuario);
    boolean delete(int id);
    Usuario findById(int id);
}
