package classes.data.service;

import classes.data.dto.PracticeDto;
import classes.data.entity.Practice;
import classes.data.entity.User;
import classes.data.validation.exception.HeadMasterAlreadyHavePractice;

import java.util.List;

public interface PracticeService {
    List<Practice> findAllByEnabled();
    List<Practice> getAll();
    Practice findOne(long id);
    Practice registerPracticeWithHeadMaster(PracticeDto practiceDto, User user) throws HeadMasterAlreadyHavePractice;
    Practice registerNewPractice(PracticeDto practiceDto) throws HeadMasterAlreadyHavePractice;
}
