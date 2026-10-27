package com.DAO;

import com.model.CamaraFrigorifica;
import java.util.List;

public interface IcamaraFrigorificaDao {
    List<CamaraFrigorifica> listAll();
    boolean add(CamaraFrigorifica camaraFrigorifica);
    boolean update(CamaraFrigorifica camaraFrigorifica);
    boolean delete(int id);
    CamaraFrigorifica findById(int id);
}
