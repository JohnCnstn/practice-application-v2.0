package classes.data.service;

import classes.data.entity.Company;
import classes.data.entity.Faculty;

import java.util.List;

public interface CompanyService {
    Company findOne(long id);
    List<Company> getAll();
}
