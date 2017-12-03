package classes.data.service;

import classes.data.entity.Speciality;

import java.util.List;

public interface SpecialityService {
    Speciality findOne(long id);
    List<Speciality> getAll();
}
