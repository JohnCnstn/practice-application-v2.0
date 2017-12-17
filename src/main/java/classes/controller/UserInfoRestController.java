package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.StudentDto;
import classes.data.entity.HeadMaster;
import classes.data.entity.Practice;
import classes.data.entity.User;
import classes.data.service.HeadMasterService;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoRestController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private HeadMasterService headMasterService;

    @RequestMapping(value = "/{id}/removeFromPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> removeFromPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        List practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        studentDto.setPracticesId(practicesIds);

        deleteFromPractice(studentDto);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/assignOnPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> assignOnPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        List practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        studentDto.setPracticesId(practicesIds);

        setStudentOnPractice(studentDto);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/headMasterAssignOnPractice", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<StudentDto> headMasterAssignOnPractice(@ModelAttribute StudentDto studentDto) {

        User headMaster = getPrincipal();

        List practicesIds = new ArrayList();

        Practice practice = getHeadMasterPractice(headMaster.getId());

        practicesIds.add(practice.getId());

        studentDto.setPracticesId(practicesIds);

        setStudentOnPractice(studentDto);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    private void deleteFromPractice(StudentDto studentDto) {
        studentService.deleteStudentFromPractice(studentDto);
    }

    private void setStudentOnPractice(StudentDto studentDto) {
        studentService.setStudentOnPractice(studentDto);
    }

    private Practice getHeadMasterPractice (long id) {

        HeadMaster headMaster = headMasterService.findOne(id);

        return headMaster.getPractice();
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
