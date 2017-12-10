package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.Student;
import classes.data.entity.User;
import classes.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("listOfStudents", studentService.getAll());
        model.addAttribute("universityList", universityService.getAll());
        model.addAttribute("universityDto", new UniversityDto());
        model.addAttribute("facultyDto", new FacultyDto());
        model.addAttribute("headMasterDto", new HeadMasterDto());
        model.addAttribute("listOfCompanies", companyService.getAll());
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("listOfFaculties", facultyService.getAll());
        model.addAttribute("practiceDto", new PracticeDto());
        model.addAttribute("specialityDto", new SpecialityDto());
        model.addAttribute("listOfHeadMasters", headMasterService.getAll());
        model.addAttribute("specialityList", specialityService.getAll());

        model.addAttribute("listOfStudentsIds", new ListOfStudentsIdsDto());

        return "students";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String showSelectedUsers(@ModelAttribute("listOfStudentsIds") ListOfStudentsIdsDto listOfStudentsIds) {

        for (String student : listOfStudentsIds.getStudents()) {
            System.out.println(student);
        }

        return "selectedUsers";
    }

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    public String showStudentInfo(@PathVariable("id") int id, @ModelAttribute("studentDto") StudentDto studentDto, Model model) {

        Student student = studentService.findOne(id);

        model.addAttribute("student", student);
        model.addAttribute("listOfPractice", practiceService.getAll());
        model.addAttribute("studentDto", studentDto);
        model.addAttribute("arrayParam",  new ArrayList<Long>());

        return "student-info";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testAdmin() {
        return "test";
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
