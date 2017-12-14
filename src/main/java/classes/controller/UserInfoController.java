package classes.controller;

import classes.data.dto.StudentDto;
import classes.data.entity.Student;
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
    private StudentService studentService;

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showStudentInfo(@PathVariable("id") int id, @ModelAttribute("studentDto") StudentDto studentDto) {

        Student student = studentService.findOne(id);

        ModelAndView modelAndView = new ModelAndView("student-info");

        modelAndView.addObject("student", student);
        modelAndView.addObject("listOfPractice", practiceService.findAllByEnabled());
        modelAndView.addObject("studentDto", studentDto);
        modelAndView.addObject("arrayParam",  new ArrayList<Long>());

        List studentPractices = studentService.getStudentPractices(id);

        modelAndView.addObject("studentPractices", studentPractices);

        return modelAndView;
    }
}
