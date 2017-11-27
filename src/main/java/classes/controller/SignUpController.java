package classes.controller;

import classes.data.dto.FacultyDto;
import classes.data.dto.UserDto;
import classes.data.service.FacultyService;
import classes.data.service.StudentService;
import classes.data.service.impl.FacultyServiceImpl;
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
@SessionAttributes("list")
public class SignUpController {

    @Autowired
    private StudentService service;

    @Autowired
    private FacultyService facultyServiceImpl;

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView showSignUpForm() {

        ModelAndView model = new ModelAndView();

        model.setViewName("sign-up");
        model.addObject("user", new UserDto());
        model.addObject("faculty", new FacultyDto());
        model.addObject("list", facultyServiceImpl.getAll());

        return model;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("faculty") FacultyDto facultyDto,
            @Valid @ModelAttribute("user") UserDto accountDto,
            BindingResult result) {

        ModelAndView model = new ModelAndView();

        if (!result.hasErrors()) {
            createUserAccount(accountDto, facultyDto, result);
        }

        if (result.hasErrors()) {
            model.addObject("list", facultyServiceImpl.getAll());
            model.setViewName("sign-up");
        } else {
            model.setViewName("redirect:/login");
        }
        return model;
    }

    private void createUserAccount(UserDto accountDto, FacultyDto facultyDto, BindingResult result) {
        try {
            service.registerNewUserAccount(accountDto, facultyDto);
        } catch (UserNameExistsException e) {
            result.rejectValue("userName", "message", "Username already exists");
        } catch (EmailExistsException e) {
            result.rejectValue("email", "message", "Email already exists");
        }
    }
}
