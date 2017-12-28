package classes.data.service.impl;

import classes.data.dto.SpecialityDto;
import classes.data.entity.Faculty;
import classes.data.entity.Speciality;
import classes.data.repository.SpecialityRepository;
import classes.data.service.FacultyService;
import classes.data.service.SpecialityService;
import classes.data.validation.exception.SpecialityAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private FacultyService facultyService;

    @Override
    public Speciality findOne(long id) {
        return specialityRepository.findOne(id);
    }

    @Override
    @Transactional
    public Speciality registerNewSpeciality(SpecialityDto specialityDto) throws SpecialityAlreadyExists {

        if (specialityExist(specialityDto.getName())) {
            throw new SpecialityAlreadyExists("There is a speciality with such name: "  + specialityDto.getName());
        }

        Speciality speciality = new Speciality();

        speciality.setName(specialityDto.getName());

        Faculty faculty = facultyService.findOne(specialityDto.getFacultyId());

        speciality.setFaculty(faculty);

        return specialityRepository.save(speciality);
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    private boolean specialityExist(String name) {
        Speciality speciality = specialityRepository.findByName(name);
        return speciality != null;
    }
}
