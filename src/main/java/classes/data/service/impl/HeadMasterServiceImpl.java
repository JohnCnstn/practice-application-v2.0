package classes.data.service.impl;

import classes.data.dto.CompanyDto;
import classes.data.dto.HeadMasterDto;
import classes.data.dto.PracticeDto;
import classes.data.dto.UserDto;
import classes.data.entity.Company;
import classes.data.entity.HeadMaster;
import classes.data.entity.Student;
import classes.data.repository.HeadMasterRepository;
import classes.data.service.HeadMasterService;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HeadMasterServiceImpl implements HeadMasterService {

    @Autowired
    private HeadMasterRepository headMasterRepository;

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public HeadMaster findOne(long id) {
        return headMasterRepository.findOne(id);
    }

    @Override
    public HeadMaster getByUserName(String userName) {
        return headMasterRepository.findByUserName(userName);
    }

    @Override
    public void setStudentOnPractice(Student student, PracticeDto practiceDto) {

    }

    @Transactional
    @Override
    public HeadMaster registerNewHeadMasterAccount(HeadMasterDto headMasterDto) {

        HeadMaster headMaster = new HeadMaster();

        Company company = companyService.findOne(headMasterDto.getCompanyId());

        headMaster.setFirstName(headMasterDto.getFirstName());
        headMaster.setLastName(headMasterDto.getLastName());
        headMaster.setEmail(headMasterDto.getEmail());
        headMaster.setUserName(headMasterDto.getUserName());

        headMaster.setPassword(bCryptPasswordEncoder.encode(headMasterDto.getPassword()));

        headMaster.setCompany(company);

        headMaster.setUserProfile(userProfileService.getByType("HEAD_MASTER"));

        return headMasterRepository.save(headMaster);
    }

    @Override
    public void delete(long id) {
        headMasterRepository.delete(id);
    }

    @Override
    public List<HeadMaster> getAll() {
        return headMasterRepository.findAll();
    }

    private boolean userNameExists(String userName) {
        HeadMaster headMaster = headMasterRepository.findByUserName(userName);
        return headMaster != null;
    }

    private boolean emailExist(String email) {
        HeadMaster headMaster = headMasterRepository.findByEmail(email);
        return headMaster != null;
    }
}
