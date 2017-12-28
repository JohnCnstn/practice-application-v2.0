package classes.data.service;

import classes.data.dto.FacultyDto;
import classes.data.entity.Faculty;
import classes.data.validation.exception.FacultyAlreadyExists;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();
    Faculty registerNewFaculty(FacultyDto facultyDto) throws FacultyAlreadyExists;
    Faculty findOne(long id);
}
