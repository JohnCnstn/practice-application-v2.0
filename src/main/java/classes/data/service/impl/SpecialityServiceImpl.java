package classes.data.service.impl;

import classes.data.dto.SpecialityDto;
import classes.data.entity.Faculty;
import classes.data.entity.Speciality;
import classes.data.repository.SpecialityRepository;
import classes.data.service.FacultyService;
import classes.data.service.SpecialityService;
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
    public Speciality registerNewSpeciality(SpecialityDto specialityDto) {

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
}
