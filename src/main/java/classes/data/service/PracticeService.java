package classes.data.service;

import classes.data.dto.PracticeDto;
import classes.data.entity.Practice;
import classes.data.entity.User;

import java.util.List;

public interface PracticeService {
    List<Practice> getAll();
    Practice registerNewPractice(PracticeDto practiceDto, User user);
}
