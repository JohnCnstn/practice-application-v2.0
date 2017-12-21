package classes.data.service;

import classes.data.dto.HeadMasterDto;
import classes.data.dto.PracticeDto;
import classes.data.entity.Company;
import classes.data.entity.HeadMaster;
import classes.data.entity.Practice;
import classes.data.entity.Student;

import java.util.List;

public interface HeadMasterService {
    HeadMaster findOne(long id);
    HeadMaster getByUserName(String userName);

    Company getCompany(long id);

    Practice getPractice(long id);

    void setPracticeForHeadMaster(Practice practice, long id);

    HeadMaster registerNewHeadMasterAccount(HeadMasterDto headMasterDto);
    void delete(long id);
    List<HeadMaster> getAll();
}
