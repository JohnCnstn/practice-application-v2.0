package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.Student;
import classes.data.entity.User;
import classes.data.service.*;
import classes.objects.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
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

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("listOfStudents", studentService.getAll());
        model.addAttribute("list", universityService.getAll());
        model.addAttribute("universityDto", new UniversityDto());
        model.addAttribute("facultyDto", new FacultyDto());
        model.addAttribute("headMasterDto", new HeadMasterDto());
        model.addAttribute("listOfCompanies", companyService.getAll());
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("listOfFaculties", facultyService.getAll());
        model.addAttribute("practiceDto", new PracticeDto());
        model.addAttribute("listOfHeadMasters", headMasterService.getAll());
        return "students";
    }

    @RequestMapping(value = "/admin/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id) {

        Student student = studentService.findOne(id);

        ModelAndView model = new ModelAndView("student-info");
        model.addObject("student", student);

        return model;
    }

    @RequestMapping(value = "/admin/userInfo/logout", method = RequestMethod.GET)
    public String redirectToLogout() {
        return "redirect:/login?logout=true";
    }

    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public String testAdmin() {
        return "test";
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
