package classes.data.service;

import classes.data.dto.FacultyDto;
import classes.data.entity.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();
    Faculty registerNewFaculty(FacultyDto facultyDto);
    Faculty findOne(long id);
}
