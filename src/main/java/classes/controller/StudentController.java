package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.entity.User;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("student", studentService.getByUserName(getPrincipal().getUserName()));
        return "student-info";
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
