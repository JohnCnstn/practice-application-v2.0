package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.PracticeDto;
import classes.data.entity.Company;
import classes.data.entity.HeadMaster;
import classes.data.entity.Practice;
import classes.data.entity.User;
import classes.data.service.HeadMasterService;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {


    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("student", studentService.getByUserName(getPrincipal().getUserName()));
        model.addAttribute("studentPracticeList", getStudentsPractices(getPrincipal().getId()));
        return "student-info";
    }

    private List<PracticeDto> getStudentsPractices (long id) {
        List<Practice> practiceList = studentService.getStudentPractices(id);

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
