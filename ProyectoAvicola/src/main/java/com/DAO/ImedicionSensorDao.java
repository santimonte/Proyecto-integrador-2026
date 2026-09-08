package com.DAO;

import com.model.MedicionSensor;
import java.util.List;

public interface ImedicionSensorDao {
    List<MedicionSensor> listAll();
    boolean add(MedicionSensor medicionSensor);
    boolean update(MedicionSensor medicionSensor);
    boolean delete(int id);
    MedicionSensor findById(int id);
}