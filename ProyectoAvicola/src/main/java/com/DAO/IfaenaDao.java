package com.DAO;

import com.model.Faena;
import java.util.List;

public interface IfaenaDao {
    List<Faena> listAll();
    boolean add(Faena faena);
    boolean update(Faena faena);
    boolean delete(int id);
    Faena findById(int id);
}
