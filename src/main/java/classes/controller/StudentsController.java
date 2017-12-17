package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.*;
import classes.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StudentsController {


    @Autowired
    private PracticeService practiceService;

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("listOfStudents", studentService.getAll());
        model.addAttribute("universityDto", new UniversityDto());
        model.addAttribute("facultyDto", new FacultyDto());
        model.addAttribute("headMasterDto", new HeadMasterDto());
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("practiceDto", new PracticeDto());
        model.addAttribute("specialityDto", new SpecialityDto());

        List<Practice> practiceList = practiceService.findAllByEnabled();

        model.addAttribute("practiceDtoList", setListOfPracticeDto(practiceList));

        model.addAttribute("arrayParam",  new ArrayList<Long>());

        return "students";
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

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
