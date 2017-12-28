package classes.data.service;

import classes.data.dto.UniversityDto;
import classes.data.entity.University;
import classes.data.validation.exception.UniversityAlreadyExists;

import java.util.List;

public interface UniversityService {
    University findOne(long id);
    University getByName(String name);
    University registerNewUniversity(UniversityDto universityDto) throws UniversityAlreadyExists;
    List<University> getAll();
}
