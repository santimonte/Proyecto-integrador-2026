package com.DAO;

import com.model.LoteAves;
import java.util.List;

public interface IloteAvesDao {
    List<LoteAves> listAll();
    boolean add(LoteAves loteAves);
    boolean update(LoteAves loteAves);
    boolean delete(int id);
    LoteAves findById(int id);
}
