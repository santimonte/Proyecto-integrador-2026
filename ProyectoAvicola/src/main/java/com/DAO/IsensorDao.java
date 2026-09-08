package com.DAO;

import com.model.Sensor;
import java.util.List;

public interface IsensorDao {
    List<Sensor> listAll();
    boolean add(Sensor sensor);
    boolean update(Sensor sensor);
    boolean delete(int id);
    Sensor findById(int id);
}
