package classes.controller;

import classes.data.dto.FacultyDto;
import classes.data.dto.StudentDto;
import classes.data.service.FacultyService;
import classes.data.service.SpecialityService;
import classes.data.service.StudentService;
import classes.data.service.UniversityService;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private StudentService service;

    @Autowired
    private FacultyService facultyServiceImpl;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView showSignUpForm() {

        ModelAndView model = new ModelAndView();

        model.setViewName("sign-up");
        model.addObject("user", new StudentDto());
        model.addObject("faculty", new FacultyDto());
        model.addObject("universityList", universityService.getAll());
        model.addObject("facultyList", facultyServiceImpl.getAll());
        model.addObject("specialityList", specialityService.getAll());

        return model;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("faculty") FacultyDto facultyDto,
            @Valid @ModelAttribute("user") StudentDto accountDto,
            BindingResult result) {

        ModelAndView model = new ModelAndView();

        if (!result.hasErrors()) {
            createUserAccount(accountDto, result);
        }

        if (result.hasErrors()) {
            model.addObject("universityList", universityService.getAll());
            model.addObject("facultyList", facultyServiceImpl.getAll());
            model.addObject("specialityList", specialityService.getAll());
            model.setViewName("sign-up");
        } else {
            model.setViewName("redirect:/login");
        }
        return model;
    }

    private void createUserAccount(StudentDto accountDto, BindingResult result) {
        try {
            service.registerNewUserAccount(accountDto);
        } catch (UserNameExistsException e) {
            result.rejectValue("userName", "message", "Username already exists");
        } catch (EmailExistsException e) {
            result.rejectValue("email", "message", "Email already exists");
        }
    }
}
