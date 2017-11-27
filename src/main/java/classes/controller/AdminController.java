package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.Student;
import classes.data.entity.User;
import classes.data.service.CompanyService;
import classes.data.service.FacultyService;
import classes.data.service.StudentService;
import classes.data.service.UniversityService;
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
        model.addAttribute("searchCriteria", new SearchCriteria());
        return "students";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView searchUser(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", searchStudent(searchCriteria));
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id) {

        User student = studentService.findOne(id);

        ModelAndView model = new ModelAndView("student-info");
        model.addObject("student", student);

        return model;
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {

        studentService.delete(id);

        return new ModelAndView("redirect:/admin");

    }

    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("test");
    }

    private Student searchStudent(SearchCriteria searchCriteria) {
        return studentService.getByFirstName(searchCriteria.getFirstName());
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }


}
