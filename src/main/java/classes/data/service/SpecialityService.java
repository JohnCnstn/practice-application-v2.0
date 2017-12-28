package classes.data.service;

import classes.data.dto.SpecialityDto;
import classes.data.entity.Speciality;
import classes.data.validation.exception.SpecialityAlreadyExists;

import java.util.List;

public interface SpecialityService {
    Speciality findOne(long id);
    Speciality registerNewSpeciality(SpecialityDto specialityDto) throws SpecialityAlreadyExists;
    List<Speciality> getAll();
}
