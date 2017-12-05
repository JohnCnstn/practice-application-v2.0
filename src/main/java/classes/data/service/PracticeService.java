package classes.data.service;

import classes.data.dto.PracticeDto;
import classes.data.entity.Practice;
import classes.data.entity.User;

import java.util.List;

public interface PracticeService {
    List<Practice> getAll();
    Practice findOne(long id);
    Practice registerPracticeWithHeadMaster(PracticeDto practiceDto, User user);
    Practice registerNewPractice(PracticeDto practiceDto);
}
