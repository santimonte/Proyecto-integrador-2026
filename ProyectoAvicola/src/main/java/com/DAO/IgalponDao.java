package com.DAO;

import com.model.Galpon;
import java.util.List;

public interface IgalponDao {
    List<Galpon> listAll();
    boolean add(Galpon galpon);
    boolean update(Galpon galpon);
    boolean delete(int id);
    Galpon findById(int id);
}
