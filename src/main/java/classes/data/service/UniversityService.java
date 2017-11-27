package classes.data.service;

import classes.data.dto.UniversityDto;
import classes.data.entity.University;

import java.util.List;

public interface UniversityService {
    University findOne(long id);
    University getByName(String name);
    University registerNewUniversity(UniversityDto universityDto);
    List<University> getAll();
}
