package classes.data.service.impl;

import classes.data.entity.Company;
import classes.data.repository.CompanyRepository;
import classes.data.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findOne(long id) {
        return companyRepository.findOne(id);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
