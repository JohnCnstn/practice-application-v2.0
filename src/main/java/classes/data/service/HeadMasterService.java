package classes.data.service;

import classes.data.dto.HeadMasterDto;
import classes.data.dto.PracticeDto;
import classes.data.entity.HeadMaster;
import classes.data.entity.Student;

import java.util.List;

public interface HeadMasterService {
    HeadMaster findOne(long id);
    HeadMaster getByUserName(String userName);
    void setStudentOnPractice(Student student, PracticeDto practiceDto);
    HeadMaster registerNewHeadMasterAccount(HeadMasterDto headMasterDto);
    void delete(long id);
    List<HeadMaster> getAll();
}
