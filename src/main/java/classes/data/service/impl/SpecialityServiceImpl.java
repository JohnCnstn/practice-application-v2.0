package classes.data.service.impl;

import classes.data.entity.Speciality;
import classes.data.repository.SpecialityRepository;
import classes.data.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }
}
