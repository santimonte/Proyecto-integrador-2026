package com.DAO;

import com.model.ControlSanidad;
import java.util.List;

public interface IcontrolSanidadDao {
    List<ControlSanidad> listAll();
    boolean add(ControlSanidad controlSanidad);
    boolean update(ControlSanidad controlSanidad);
    boolean delete(int id);
    ControlSanidad findById(int id);
}
