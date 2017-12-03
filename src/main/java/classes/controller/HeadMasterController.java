package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.PracticeDto;
import classes.data.entity.Student;
import classes.data.entity.User;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeadMasterController {

    @Autowired
    private StudentService userService;

    @RequestMapping(value = "/head-master", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("listOfStudents", userService.getAll());
        model.addAttribute("practiceDto", new PracticeDto());
        return "students";
    }

    @RequestMapping(value = "/head-master/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id) {

        Student student = userService.findOne(id);

        ModelAndView model = new ModelAndView("student-info");
        model.addObject("student", student);

        return model;
    }

//    @RequestMapping(value = "/head-master/userInfo/{id}/setPractice", method = RequestMethod.GET)
//    public ModelAndView setPractice(@PathVariable("id") int id) {
//
////        createPractice(student);
//
//        ModelAndView model = new ModelAndView();
////        model.addObject("student", student);
//
//        User user = userService.findOne(id);
//
//        PracticeDto practiceDto = new PracticeDto();
//
//        createPractice(practiceDto);
//
//        registerPracticeWithHeadMaster(practiceDto);
//
//        setStudentOnPractice(user, practiceDto);
//
//        model.setViewName("test");
//
//        return model;
//    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }

//    private void createPractice(PracticeDto practiceDto) {
//        Date date = new Date();
//        practiceDto.setStartDate(date);
//    }

    private void setStudentOnPractice(Student student, PracticeDto practiceDto) {
        userService.setStudentOnPractice(student, practiceDto);
    }

//    private void registerPracticeWithHeadMaster(PracticeDto practiceDto) {
//        practiceService.registerPracticeWithHeadMaster(practiceDto);
//    }
}
