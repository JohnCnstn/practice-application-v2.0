package classes.data.service.impl;

import classes.data.dto.PracticeDto;
import classes.data.entity.Practice;
import classes.data.entity.User;
import classes.data.repository.PracticeRepository;
import classes.data.service.HeadMasterService;
import classes.data.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private PracticeRepository practiceRepository;

    @Override
    public List<Practice> findAllByEnabled() {
        return practiceRepository.findAllByEnabled(true);
    }

    @Override
    public List<Practice> getAll() {
        return practiceRepository.findAll();
    }

    @Override
    public Practice findOne(long id) {
        return practiceRepository.findOne(id);
    }

    @Override
    @Transactional
    public Practice registerPracticeWithHeadMaster(PracticeDto practiceDto, User user) {
        Practice practice = new Practice();
        practice.setStartDate(practiceDto.getStartDate());
        practice.setEndDate(practiceDto.getEndDate());
        practice.setHeadMaster(headMasterService.findOne(user.getId()));
        return practiceRepository.save(practice);
    }

    @Override
    @Transactional
    public Practice registerNewPractice(PracticeDto practiceDto) {
        Practice practice = new Practice();
        practice.setStartDate(practiceDto.getStartDate());
        practice.setEndDate(practiceDto.getEndDate());
        practice.setQuantity(practiceDto.getQuantity());
        practice.setHeadMaster(headMasterService.findOne(practiceDto.getHeadMasterId()));
        return practiceRepository.save(practice);
    }
}
