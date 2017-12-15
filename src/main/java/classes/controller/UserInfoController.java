package classes.controller;

import classes.data.dto.PracticeDto;
import classes.data.dto.StudentDto;
import classes.data.entity.Company;
import classes.data.entity.HeadMaster;
import classes.data.entity.Practice;
import classes.data.entity.Student;
import classes.data.service.HeadMasterService;
import classes.data.service.PracticeService;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserInfoController {

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id, @ModelAttribute("studentDto") StudentDto studentDto) {

        Student student = studentService.findOne(id);

        List<Practice> practiceList = practiceService.findAllByEnabled();

        ModelAndView modelAndView = new ModelAndView("student-info");

        modelAndView.addObject("student", student);
        modelAndView.addObject("practiceDtoList", setListOfPracticeDto(practiceList));
        modelAndView.addObject("studentDto", studentDto);
        modelAndView.addObject("arrayParam",  new ArrayList<Long>());

        List studentPractices = studentService.getStudentPractices(id);

        modelAndView.addObject("studentPractices", studentPractices);

        return modelAndView;
    }

    private List<PracticeDto> setListOfPracticeDto (List<Practice> practiceList) {
        List<PracticeDto> practiceDtoList = new ArrayList<>();
        for (Practice practice : practiceList) {
            PracticeDto practiceDto = new PracticeDto();
            practiceDto.setId(practice.getId());

            HeadMaster headMaster = practice.getHeadMaster();

            Company company = headMasterService.getCompany(headMaster.getId());

            practiceDto.setCompanyName(company.getName());

            practiceDto.setHeadMasterName(practice.getHeadMaster().getUserName());
            practiceDto.setStartDate(practice.getStartDate());
            practiceDto.setEndDate(practice.getEndDate());
            practiceDtoList.add(practiceDto);
        }
        return practiceDtoList;
    }
}
