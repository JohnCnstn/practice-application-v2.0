package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.PracticeDto;
import classes.data.entity.Student;
import classes.data.entity.User;
import classes.data.service.PracticeService;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/head-master")
public class HeadMasterController {

    @Autowired
    private StudentService userService;

    @Autowired
    private PracticeService practiceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("listOfStudents", userService.getAll());
        model.addAttribute("practiceDto", new PracticeDto());
        return "students";
    }

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id) {

        Student student = userService.findOne(id);

        ModelAndView model = new ModelAndView("student-info");
        model.addObject("listOfPractice", practiceService.getAll());
        model.addObject("student", student);

        return model;
    }

//    @RequestMapping(value = "/userInfo/logout", method = RequestMethod.POST)
//    public ModelAndView logoutRedirect() {
//        return new ModelAndView("redirect:/login");
//    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }

//    private void setStudentOnPractice(Student student, PracticeDto practiceDto) {
//        userService.setStudentOnPractice(student, practiceDto);
//    }
}
