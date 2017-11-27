package classes.data.service.impl;

import classes.data.dto.UniversityDto;
import classes.data.entity.University;
import classes.data.repository.UniversityRepository;
import classes.data.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    @Override
    public University findOne(long id) {
        return universityRepository.findOne(id);
    }

    @Override
    public University getByName(String name) {
        return universityRepository.findByName(name);
    }

    @Transactional
    @Override
    public University registerNewUniversity(UniversityDto universityDto) {
        University university = new University();
        university.setName(universityDto.getName());
        return universityRepository.save(university);
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }
}
