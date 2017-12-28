package classes.data.service.impl;

import classes.data.dto.HeadMasterDto;
import classes.data.dto.PracticeDto;
import classes.data.entity.*;
import classes.data.repository.HeadMasterRepository;
import classes.data.repository.UserRepository;
import classes.data.service.HeadMasterService;
import classes.data.validation.exception.signUp.EmailExistsException;
import classes.data.validation.exception.signUp.UserNameExistsException;
import org.hibernate.Hibernate;
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
    private UserRepository userRepository;

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
    @Transactional
    public Company getCompany(long id) {
        HeadMaster headMaster = headMasterRepository.findOne(id);
        Hibernate.initialize(headMaster.getCompany());
        return headMaster.getCompany();
    }

    @Override
    @Transactional
    public Practice getPractice(long id) {
        HeadMaster headMaster = headMasterRepository.findOne(id);
        Hibernate.initialize(headMaster.getPractice());
        return headMaster.getPractice();
    }

    @Override
    public void setPracticeForHeadMaster(Practice practice, long id) {
        HeadMaster headMaster = headMasterRepository.findOne(id);
        headMaster.setPractice(practice);
        headMasterRepository.save(headMaster);
    }

    @Transactional
    @Override
    public HeadMaster registerNewHeadMasterAccount(HeadMasterDto headMasterDto) throws EmailExistsException, UserNameExistsException {

        if (userNameExists(headMasterDto.getUserName())) {
            throw new UserNameExistsException("There is an account with that Username: "  + headMasterDto.getUserName());
        }

        if (emailExist(headMasterDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "  + headMasterDto.getEmail());
        }

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

    @Transactional
    @Override
    public List<HeadMaster> getAll() {
        return headMasterRepository.findAll();
    }

    private boolean userNameExists(String userName) {
        User headMaster = userRepository.findByUserName(userName);
        return headMaster != null;
    }

    private boolean emailExist(String email) {
        User headMaster = userRepository.findByEmail(email);
        return headMaster != null;
    }
}
