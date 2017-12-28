package classes.data.service.impl;

import classes.data.dto.FacultyDto;
import classes.data.entity.Faculty;
import classes.data.entity.University;
import classes.data.repository.FacultyRepository;
import classes.data.service.FacultyService;
import classes.data.service.UniversityService;
import classes.data.validation.exception.FacultyAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UniversityService universityService;

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Transactional
    @Override
    public Faculty registerNewFaculty(FacultyDto facultyDto) throws FacultyAlreadyExists {

        if (facultyExist(facultyDto.getName())) {
            throw new FacultyAlreadyExists("There is a faculty with such name: "  + facultyDto.getName());
        }

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());

        University university = universityService.findOne(facultyDto.getUniversityId());

        faculty.setUniversity(university);

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findOne(long id) {
        return facultyRepository.findOne(id);
    }

    private boolean facultyExist(String name) {
        Faculty faculty = facultyRepository.findByName(name);
        return faculty != null;
    }
}
